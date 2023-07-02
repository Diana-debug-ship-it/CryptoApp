package com.example.cryptoapp.data.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class CoinNameContainerDto {
    @SerializedName("CoinInfo")
    private CoinNameDto coinName;

    public CoinNameContainerDto(CoinNameDto coinName) {
        this.coinName = coinName;
    }

    public CoinNameDto getCoinInfo() {
        return coinName;
    }

    public void setCoinInfo(CoinNameDto coinName) {
        this.coinName = coinName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoinNameContainerDto coinNameContainerDto = (CoinNameContainerDto) o;
        return Objects.equals(coinName, coinNameContainerDto.coinName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coinName);
    }

    @Override
    public String toString() {
        return "Datum{" +
                "coinInfo=" + coinName +
                '}';
    }
}
