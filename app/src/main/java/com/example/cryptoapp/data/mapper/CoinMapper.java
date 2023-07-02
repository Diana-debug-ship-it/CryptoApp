package com.example.cryptoapp.data.mapper;

import com.example.cryptoapp.data.database.CoinInfoDbModel;
import com.example.cryptoapp.data.network.model.CoinInfoDto;
import com.example.cryptoapp.data.network.model.CoinInfoJsonContainerDto;
import com.example.cryptoapp.data.network.model.CoinNamesListDto;
import com.example.cryptoapp.domain.CoinInfo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CoinMapper {

    public CoinInfoDbModel mapDtoToDbModel(CoinInfoDto dto) {
        return new CoinInfoDbModel(
                dto.getFromsymbol(),
                dto.getTosymbol(),
                dto.getPrice(),
                dto.getLastupdate(),
                dto.getHighday(),
                dto.getLowday(),
                dto.getLastmarket(),
                dto.getImageurl()
        );
    }

    public List<CoinInfoDto> mapJsonContainerToListCoinInfo(CoinInfoJsonContainerDto json) {
        List<CoinInfoDto> result = new ArrayList<>();
        JsonObject jsonObject = json.getJson();
        if (jsonObject == null) {
            return result;
        }
        Set<String> coinKeySet = jsonObject.keySet();
        for (String coinKey : coinKeySet) {
            JsonObject currencyJson = jsonObject.getAsJsonObject(coinKey);
            Set<String> currencyKeySet = currencyJson.keySet();
            for (String currencyKey : currencyKeySet) {
                CoinInfoDto priceInfo = new Gson().fromJson(
                        currencyJson.getAsJsonObject(currencyKey),
                        CoinInfoDto.class
                );
                result.add(priceInfo);
            }
        }
        return result;
    }

    public String mapNamesListToString(CoinNamesListDto names) {
        return names.getNames().stream()
                .map(data -> data.getCoinInfo().getName()).collect(Collectors.joining(","));
    }

    public CoinInfo mapDbModelToEntity(CoinInfoDbModel dbModel) {
        return new CoinInfo(
                dbModel.getFromsymbol(),
                dbModel.getTosymbol(),
                dbModel.getPrice(),
                dbModel.getLastupdate(),
                dbModel.getHighday(),
                dbModel.getLowday(),
                dbModel.getLastmarket(),
                dbModel.getImageurl()
        );
    }
}
