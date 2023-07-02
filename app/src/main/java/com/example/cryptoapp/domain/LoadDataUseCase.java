package com.example.cryptoapp.domain;

public class LoadDataUseCase {
    private CoinRepository repository;

    public LoadDataUseCase(CoinRepository repository) {
        this.repository = repository;
    }

    void loadDataUseCase(){
        repository.loadData();
    }
}
