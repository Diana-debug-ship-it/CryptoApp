package com.example.cryptoapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.cryptoapp.pojo.CoinPriceInfo;

import java.util.List;

@Dao
public interface CoinPriceInfoDao {

    @Query("SELECT * FROM full_price_list ORDER BY lastupdate DESC")
    LiveData<List<CoinPriceInfo>> getPriceList();

    @Query("SELECT * FROM full_price_list WHERE fromsymbol==:fsym LIMIT 1")
    LiveData<CoinPriceInfo> getPriceInfoAboutCoin(String fsym);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPriceList(List<CoinPriceInfo> priceList);
}
