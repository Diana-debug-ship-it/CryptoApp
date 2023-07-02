package com.example.cryptoapp.presentation;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cryptoapp.data.database.CoinInfoDao;
import com.example.cryptoapp.data.network.model.CoinInfoDto;
import com.example.cryptoapp.data.network.ApiFactory;
import com.example.cryptoapp.data.database.AppDatabase;
import com.example.cryptoapp.data.network.model.CoinInfoJsonContainerDto;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CoinViewModel extends AndroidViewModel {

    private static final String TAG = "TEST_LOADING_DATA";

    private CoinInfoDao coinInfoDao;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public CoinViewModel(@NonNull Application application) {
        super(application);
        loadData();
        coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao();
        priceList.setValue(coinInfoDao.getPriceList().getValue());
    }

    private MutableLiveData<List<CoinInfoDto>> priceList = new MutableLiveData<>();

    public LiveData<List<CoinInfoDto>> getPriceList() {
        return priceList;
    }

    public LiveData<CoinInfoDto> getDetailInfo(String fSym) {
        //return coinInfoDao.getPriceInfoAboutCoin(fSym);
    }

    private void loadData() {
        Log.d(TAG, "Loading data started");
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
                                   coinInfoDao.insertPriceList(coinPriceInfos);
                                   Log.d(TAG, "Succesfully added to db");
                                   priceList.postValue(coinPriceInfos);
                               }
                           },
                        e -> Log.d(TAG, "Failure: " + e.getMessage()));

        compositeDisposable.add(disposable);

    }


    private List<CoinInfoDto> getPriceListFromRawData(CoinInfoJsonContainerDto rawData) {
        List<CoinInfoDto> result = new ArrayList<>();
        JsonObject jsonObject = rawData.getJson();
        if (jsonObject==null) {
            return result;
        }
        Set<String> coinKeySet = jsonObject.keySet();
        for (String coinKey: coinKeySet) {
            JsonObject currencyJson = jsonObject.getAsJsonObject(coinKey);
            Set<String> currencyKeySet = currencyJson.keySet();
            for (String currencyKey: currencyKeySet) {
                CoinInfoDto priceInfo = new Gson().fromJson(
                        currencyJson.getAsJsonObject(currencyKey),
                        CoinInfoDto.class
                );
                result.add(priceInfo);
            }
        }
        return result;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
