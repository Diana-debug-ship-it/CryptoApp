package com.example.cryptoapp.domain;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface CoinRepository {

    LiveData<List<CoinInfo>> getCoinInfoList();

    LiveData<CoinInfo> getCoinInfo(String fromSymbol);

    void loadData();
}
