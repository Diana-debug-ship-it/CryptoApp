package com.example.cryptoapp.data.network;

import com.example.cryptoapp.data.network.model.CoinNamesListDto;
import com.example.cryptoapp.data.network.model.CoinInfoJsonContainerDto;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    public static final String QUERY_PARAM_LIMIT ="limit";
    public static final String QUERY_PARAM_FROM_SYMBOLS ="fsyms";

    @GET("top/totalvolfull?tsym=USD")
    Single<CoinNamesListDto> getTopCoinsInfo(@Query(QUERY_PARAM_LIMIT) int limit);


    @GET("pricemultifull?tsyms=USD,EUR")
    Single<CoinInfoJsonContainerDto> getFullPriceList(@Query(QUERY_PARAM_FROM_SYMBOLS) String fsyms);


}
