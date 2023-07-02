package com.example.cryptoapp.data.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class CoinNamesListDto {

    @SerializedName("Data")
    private List<CoinNameContainerDto> names;

    public CoinNamesListDto(List<CoinNameContainerDto> names) {
        this.names = names;
    }

    public List<CoinNameContainerDto> getNames() {
        return names;
    }

    public void setNames(List<CoinNameContainerDto> names) {
        this.names = names;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoinNamesListDto that = (CoinNamesListDto) o;
        return Objects.equals(names, that.names);
    }

    @Override
    public int hashCode() {
        return Objects.hash(names);
    }

    @Override
    public String toString() {
        return "CoinInfoListOfData{" +
                "data=" + names +
                '}';
    }
}
