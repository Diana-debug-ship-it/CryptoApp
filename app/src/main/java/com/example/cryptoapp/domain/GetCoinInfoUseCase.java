package com.example.cryptoapp.domain;

import androidx.lifecycle.LiveData;

import java.util.List;

public class GetCoinInfoUseCase {
    private CoinRepository repository;

    public GetCoinInfoUseCase(CoinRepository repository) {
        this.repository = repository;
    }

    public LiveData<CoinInfo> getCoinInfo(String fSym){
        return repository.getCoinInfo(fSym);
    }
}
