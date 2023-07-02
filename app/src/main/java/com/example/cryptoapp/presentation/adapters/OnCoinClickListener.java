package com.example.cryptoapp.presentation.adapters;

import com.example.cryptoapp.data.network.model.CoinInfoDto;

public interface OnCoinClickListener {
    void onCoinClick(CoinInfoDto coinPriceInfo);
}
