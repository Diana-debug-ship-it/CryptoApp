package com.example.cryptoapp.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Datum {
    @SerializedName("CoinInfo")
    private CoinInfo coinInfo;

    public Datum(CoinInfo coinInfo) {
        this.coinInfo = coinInfo;
    }

    public CoinInfo getCoinInfo() {
        return coinInfo;
    }

    public void setCoinInfo(CoinInfo coinInfo) {
        this.coinInfo = coinInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Datum datum = (Datum) o;
        return Objects.equals(coinInfo, datum.coinInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coinInfo);
    }

    @Override
    public String toString() {
        return "Datum{" +
                "coinInfo=" + coinInfo +
                '}';
    }
}
