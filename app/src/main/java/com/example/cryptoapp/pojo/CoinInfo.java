package com.example.cryptoapp.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class CoinInfo {
    @SerializedName("Name")
    private String name;

    public CoinInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoinInfo coinInfo = (CoinInfo) o;
        return Objects.equals(name, coinInfo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "CoinInfo{" +
                "name='" + name + '\'' +
                '}';
    }
}
