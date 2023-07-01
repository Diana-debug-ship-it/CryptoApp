package com.example.cryptoapp.utils;


import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class TimeUtils {

    public static String convertTimestampToTime(Long timestamp) {
        if (timestamp==null) return "";

        Timestamp stamp = new Timestamp(timestamp*1000);
        Date date = new Date(stamp.getTime());
        String pattern = "HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(date);
    }
}