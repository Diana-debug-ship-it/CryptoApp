package com.example.cryptoapp.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CoinInfoDao {

    @Query("SELECT * FROM full_price_list ORDER BY lastupdate DESC")
    LiveData<List<CoinInfoDbModel>> getPriceList();

    @Query("SELECT * FROM full_price_list WHERE fromsymbol==:fsym LIMIT 1")
    LiveData<CoinInfoDbModel> getPriceInfoAboutCoin(String fsym);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPriceList(List<CoinInfoDbModel> priceList);
}
