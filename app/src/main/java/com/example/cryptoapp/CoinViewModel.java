package com.example.cryptoapp;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cryptoapp.api.ApiFactory;
import com.example.cryptoapp.database.AppDatabase;
import com.example.cryptoapp.database.CoinPriceInfoDao;
import com.example.cryptoapp.pojo.CoinPriceInfo;
import com.example.cryptoapp.pojo.CoinPriceInfoRawData;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CoinViewModel extends AndroidViewModel {

    private static final String TAG = "TEST_LOADING_DATA";

    private CoinPriceInfoDao coinPriceInfoDao;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public CoinViewModel(@NonNull Application application) {
        super(application);
        loadData();
        coinPriceInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao();
        priceList.setValue(coinPriceInfoDao.getPriceList().getValue());
        List<CoinPriceInfo> temp = priceList.getValue();
    }

    private MutableLiveData<List<CoinPriceInfo>> priceList = new MutableLiveData<>();

    public LiveData<List<CoinPriceInfo>> getPriceList() {
        return priceList;
    }

    public LiveData<CoinPriceInfo> getDetailInfo(String fSym) {
        return coinPriceInfoDao.getPriceInfoAboutCoin(fSym);
    }

    private void loadData() {
        Log.d(TAG, "Loading data started");
        Disposable disposable = ApiFactory.apiservice.getTopCoinsInfo(50)
                .map(value -> value.getData().stream().map(
                        data -> data.getCoinInfo().getName()).collect(Collectors.joining(","))
                )
                .flatMap(value -> ApiFactory.apiservice.getFullPriceList(value))
                .map(rawData -> getPriceListFromRawData(rawData))
                .delaySubscription(10, TimeUnit.SECONDS)
                .repeat()
                .retry()
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<CoinPriceInfo>>() {
                               @Override
                               public void accept(List<CoinPriceInfo> coinPriceInfos) throws Throwable {
                                   coinPriceInfoDao.insertPriceList(coinPriceInfos);
                                   Log.d(TAG, "Succesfully added to db");
                                   priceList.postValue(coinPriceInfos);
                               }
                           },
                        e -> Log.d(TAG, "Failure: " + e.getMessage()));

        compositeDisposable.add(disposable);

    }


    private List<CoinPriceInfo> getPriceListFromRawData(CoinPriceInfoRawData rawData) {
        List<CoinPriceInfo> result = new ArrayList<>();
        JsonObject jsonObject = rawData.getCoinPriceInfoJsonObject();
        if (jsonObject==null) {
            return result;
        }
        Set<String> coinKeySet = jsonObject.keySet();
        for (String coinKey: coinKeySet) {
            JsonObject currencyJson = jsonObject.getAsJsonObject(coinKey);
            Set<String> currencyKeySet = currencyJson.keySet();
            for (String currencyKey: currencyKeySet) {
                CoinPriceInfo priceInfo = new Gson().fromJson(
                        currencyJson.getAsJsonObject(currencyKey),
                        CoinPriceInfo.class
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
