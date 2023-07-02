package com.example.cryptoapp.domain;

import androidx.lifecycle.LiveData;

import java.util.List;

public class GetCoinInfoListUseCase {
    private CoinRepository repository;

    public GetCoinInfoListUseCase(CoinRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<CoinInfo>> getCoinInfoList(){
        return repository.getCoinInfoList();
    }
}
