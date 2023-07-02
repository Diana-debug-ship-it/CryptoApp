package com.example.cryptoapp.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.cryptoapp.pojo.CoinPriceInfo;

public class CoinInfoDiffCallBack extends DiffUtil.ItemCallback<CoinPriceInfo> {

    public CoinInfoDiffCallBack() {
    }

    @Override
    public boolean areItemsTheSame(@NonNull CoinPriceInfo oldItem, @NonNull CoinPriceInfo newItem) {
        return oldItem.getFromsymbol().equals(newItem.getFromsymbol());
    }

    @Override
    public boolean areContentsTheSame(@NonNull CoinPriceInfo oldItem, @NonNull CoinPriceInfo newItem) {
        return oldItem.equals(newItem);
    }
}
