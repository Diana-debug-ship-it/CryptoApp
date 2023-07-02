package com.example.cryptoapp.data.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class CoinNameDto {
    @SerializedName("Name")
    private String name;

    public CoinNameDto(String name) {
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
        CoinNameDto coinName = (CoinNameDto) o;
        return Objects.equals(name, coinName.name);
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
