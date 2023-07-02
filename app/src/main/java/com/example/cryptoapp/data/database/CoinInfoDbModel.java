package com.example.cryptoapp.data.database;

import static com.example.cryptoapp.data.network.ApiFactory.BASE_IMAGE_URl;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.cryptoapp.utils.TimeUtils;

import java.util.Objects;

@Entity(tableName = "full_price_list")
public class CoinInfoDbModel {
    @PrimaryKey
    @NonNull
    private String fromsymbol;
    private String tosymbol;
    private String price;

    private Long lastupdate;
    private String highday;
    private String lowday;

    private String lastmarket;
    private String imageurl;

    public CoinInfoDbModel(@NonNull String fromsymbol, String tosymbol, String price, Long lastupdate, String highday, String lowday, String lastmarket, String imageurl) {
        this.fromsymbol = fromsymbol;
        this.tosymbol = tosymbol;
        this.price = price;
        this.lastupdate = lastupdate;
        this.highday = highday;
        this.lowday = lowday;
        this.lastmarket = lastmarket;
        this.imageurl = imageurl;
    }

    public String getFromsymbol() {
        return fromsymbol;
    }

    public void setFromsymbol(String fromsymbol) {
        this.fromsymbol = fromsymbol;
    }

    public String getTosymbol() {
        return tosymbol;
    }

    public void setTosymbol(String tosymbol) {
        this.tosymbol = tosymbol;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Long getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Long lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getHighday() {
        return highday;
    }

    public void setHighday(String highday) {
        this.highday = highday;
    }

    public String getLowday() {
        return lowday;
    }

    public void setLowday(String lowday) {
        this.lowday = lowday;
    }

    public String getLastmarket() {
        return lastmarket;
    }

    public void setLastmarket(String lastmarket) {
        this.lastmarket = lastmarket;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoinInfoDbModel that = (CoinInfoDbModel) o;
        return Objects.equals(fromsymbol, that.fromsymbol) && Objects.equals(tosymbol, that.tosymbol) && Objects.equals(price, that.price) && Objects.equals(lastupdate, that.lastupdate) && Objects.equals(highday, that.highday) && Objects.equals(lowday, that.lowday) && Objects.equals(lastmarket, that.lastmarket) && Objects.equals(imageurl, that.imageurl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromsymbol, tosymbol, price, lastupdate, highday, lowday, lastmarket, imageurl);
    }

    @Override
    public String toString() {
        return "CoinPriceInfo{" +
                "fromsymbol='" + fromsymbol + '\'' +
                ", tosymbol='" + tosymbol + '\'' +
                ", price='" + price + '\'' +
                ", lastupdate=" + lastupdate +
                ", highday='" + highday + '\'' +
                ", lowday='" + lowday + '\'' +
                ", lastmarket='" + lastmarket + '\'' +
                ", imageurl='" + imageurl + '\'' +
                '}';
    }

    public String getFormattedTime() {
        return TimeUtils.convertTimestampToTime(lastupdate);
    }

    public String getFullImageUrl() {
        return BASE_IMAGE_URl + imageurl;
    }
}
