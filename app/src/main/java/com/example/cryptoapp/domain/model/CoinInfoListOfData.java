package com.example.cryptoapp.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class CoinInfoListOfData {

    @SerializedName("Data")
    private List<Datum> data;

    public CoinInfoListOfData(List<Datum> data) {
        this.data = data;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoinInfoListOfData that = (CoinInfoListOfData) o;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        return "CoinInfoListOfData{" +
                "data=" + data +
                '}';
    }
}
