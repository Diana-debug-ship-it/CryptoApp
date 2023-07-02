package com.example.cryptoapp.data.api;

import com.example.cryptoapp.pojo.CoinInfoListOfData;
import com.example.cryptoapp.pojo.CoinPriceInfoRawData;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    public static final String QUERY_PARAM_LIMIT ="limit";
    public static final String QUERY_PARAM_FROM_SYMBOLS ="fsyms";

    @GET("top/totalvolfull?tsym=USD")
    Single<CoinInfoListOfData> getTopCoinsInfo(@Query(QUERY_PARAM_LIMIT) int limit);


    @GET("pricemultifull?tsyms=USD,EUR")
    Single<CoinPriceInfoRawData> getFullPriceList(@Query(QUERY_PARAM_FROM_SYMBOLS) String fsyms);

}
