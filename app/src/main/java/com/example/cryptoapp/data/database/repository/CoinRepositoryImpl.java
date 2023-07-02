package com.example.cryptoapp.data.database.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.cryptoapp.data.database.AppDatabase;
import com.example.cryptoapp.data.database.CoinInfoDao;
import com.example.cryptoapp.data.mapper.CoinMapper;
import com.example.cryptoapp.data.network.ApiFactory;
import com.example.cryptoapp.data.network.ApiService;
import com.example.cryptoapp.data.network.model.CoinInfoDto;
import com.example.cryptoapp.data.network.model.CoinInfoJsonContainerDto;
import com.example.cryptoapp.data.network.model.CoinNamesListDto;
import com.example.cryptoapp.domain.CoinInfo;
import com.example.cryptoapp.domain.CoinRepository;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CoinRepositoryImpl implements CoinRepository {

    private static final String TAG = "TEST_LOADING_DATA";

    private Application application;

    private ApiService apiService = ApiFactory.apiservice;

    public CoinRepositoryImpl(Application application) {
        this.application = application;
    }

    private final CoinInfoDao coinInfoDao = AppDatabase
            .getInstance(application).coinPriceInfoDao();

    private CoinMapper mapper = new CoinMapper();
    @Override
    public LiveData<List<CoinInfo>> getCoinInfoList() {
        return Transformations.map(coinInfoDao.getPriceList(),
                value -> value.stream()
                        .map(val -> mapper.mapDbModelToEntity(val)).collect(Collectors.toList()));
    }

    @Override
    public LiveData<CoinInfo> getCoinInfo(String fromSymbol) {
        return Transformations.map(coinInfoDao.getPriceInfoAboutCoin(fromSymbol),
                value -> mapper.mapDbModelToEntity(value));
    }

    @Override
    public void loadData() {
        Disposable disposable = ApiFactory.apiservice.getTopCoinsInfo(50)
                .map(value -> value.getNames().stream().map(
                        data -> data.getCoinInfo().getName()).collect(Collectors.joining(","))
                )
                .flatMap(value -> ApiFactory.apiservice.getFullPriceList(value))
                .map(rawData -> getPriceListFromRawData(rawData))
                .delaySubscription(10, TimeUnit.SECONDS)
                .repeat()
                .retry()
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<CoinInfoDto>>() {
                               @Override
                               public void accept(List<CoinInfoDto> coinPriceInfos) throws Throwable {
                                   coinInfoDao.insertPriceList(coinPriceInfos.stream().map(value -> mapper.mapDtoToDbModel(value)).collect(Collectors.toList()));
                                   Log.d(TAG, "Succesfully added to db");
                               }
                           },
                        e -> Log.d(TAG, "Failure: " + e.getMessage()));
    }


//    ApiFactory.apiservice.getTopCoinsInfo(50)
//            .map(value -> value.getNames().stream().map(
//            data -> data.getCoinInfo().getName()).collect(Collectors.joining(","))
//            )
//            .flatMap(value -> ApiFactory.apiservice.getFullPriceList(value))
//            .map(rawData -> getPriceListFromRawData(rawData))
//            .delaySubscription(10,TimeUnit.SECONDS)
//                .repeat()
//                .retry()
//                .subscribeOn(Schedulers.io())
//            .subscribe(new Consumer<List<CoinInfoDto>>() {
//        @Override
//        public void accept(List<CoinInfoDto> coinPriceInfos) throws Throwable {
//            coinInfoDao.insertPriceList(coinPriceInfos);
//            Log.d(TAG, "Succesfully added to db");
//            priceList.postValue(coinPriceInfos);
//        }
//    },
//    e -> Log.d(TAG, "Failure: " + e.getMessage()));

}
