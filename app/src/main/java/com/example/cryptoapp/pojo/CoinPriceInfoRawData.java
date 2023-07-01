package com.example.cryptoapp.pojo;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class CoinPriceInfoRawData {

    @SerializedName("RAW")
    private JsonObject coinPriceInfoJsonObject;

    public CoinPriceInfoRawData(JsonObject coinPriceInfoJsonObject) {
        this.coinPriceInfoJsonObject = coinPriceInfoJsonObject;
    }

    public JsonObject getCoinPriceInfoJsonObject() {
        return coinPriceInfoJsonObject;
    }

    public void setCoinPriceInfoJsonObject(JsonObject coinPriceInfoJsonObject) {
        this.coinPriceInfoJsonObject = coinPriceInfoJsonObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoinPriceInfoRawData that = (CoinPriceInfoRawData) o;
        return Objects.equals(coinPriceInfoJsonObject, that.coinPriceInfoJsonObject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coinPriceInfoJsonObject);
    }

    @Override
    public String toString() {
        return "CoinPriceInfoRawData{" +
                "coinPriceInfoJsonObject=" + coinPriceInfoJsonObject +
                '}';
    }
}
