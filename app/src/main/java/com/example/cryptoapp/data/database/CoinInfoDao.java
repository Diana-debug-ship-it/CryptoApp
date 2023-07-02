package com.example.cryptoapp.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.cryptoapp.data.network.model.CoinInfoDto;

import java.util.List;

@Dao
public interface CoinPriceInfoDao {

    @Query("SELECT * FROM CoinInfoDbModel ORDER BY lastupdate DESC")
    LiveData<List<CoinInfoDto>> getPriceList();

    @Query("SELECT * FROM CoinInfoDbModel WHERE fromsymbol==:fsym LIMIT 1")
    LiveData<CoinInfoDto> getPriceInfoAboutCoin(String fsym);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPriceList(List<CoinInfoDto> priceList);
}
