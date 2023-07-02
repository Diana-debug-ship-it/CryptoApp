package com.example.cryptoapp.data.network.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class CoinInfoJsonContainerDto {

    @SerializedName("RAW")
    private JsonObject json;

    public CoinInfoJsonContainerDto(JsonObject json) {
        this.json = json;
    }

    public JsonObject getJson() {
        return json;
    }

    public void setJson(JsonObject json) {
        this.json = json;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoinInfoJsonContainerDto that = (CoinInfoJsonContainerDto) o;
        return Objects.equals(json, that.json);
    }

    @Override
    public int hashCode() {
        return Objects.hash(json);
    }

    @Override
    public String toString() {
        return "CoinPriceInfoRawData{" +
                "coinPriceInfoJsonObject=" + json +
                '}';
    }
}
