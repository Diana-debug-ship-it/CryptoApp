package com.example.cryptoapp.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cryptoapp.pojo.CoinPriceInfo;

@Database(entities = {CoinPriceInfo.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance = null;
    private static final String DB_NAME = "main.db";

    public static AppDatabase getInstance(Context context) {
        if (instance==null) {
            instance = Room.databaseBuilder(
                    context,
                    AppDatabase.class,
                    DB_NAME
            )
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }



    public abstract CoinPriceInfoDao coinPriceInfoDao();
}
