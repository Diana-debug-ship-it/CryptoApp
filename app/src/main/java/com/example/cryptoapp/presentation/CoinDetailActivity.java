package com.example.cryptoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cryptoapp.pojo.CoinPriceInfo;
import com.squareup.picasso.Picasso;

public class CoinDetailActivity extends AppCompatActivity {

    public static final String EXTRA_FROM_SYMBOL = "fSym";
    private CoinViewModel viewModel;

    private TextView tvPrice;
    private TextView tvMinPrice;
    private TextView tvMaxPrice;
    private TextView tvLastMarket;
    private TextView tvLastUpdate;
    private TextView tvFromSymbol;
    private TextView tvToSymbol;
    private ImageView ivLogoCoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_detail);
        viewModel = new ViewModelProvider(this).get(CoinViewModel.class);

        if (!getIntent().hasExtra(EXTRA_FROM_SYMBOL)){
            finish();
            return;
        }

        initViews();

        String fromSymbol = getIntent().getStringExtra(EXTRA_FROM_SYMBOL);
        viewModel.getDetailInfo(fromSymbol).observe(this, new Observer<CoinPriceInfo>() {
            @Override
            public void onChanged(CoinPriceInfo coinPriceInfo) {
                tvPrice.setText(coinPriceInfo.getPrice());
                tvMinPrice.setText(coinPriceInfo.getLowday());
                tvMaxPrice.setText(coinPriceInfo.getHighday());
                tvLastMarket.setText(coinPriceInfo.getLastmarket());
                tvLastUpdate.setText(coinPriceInfo.getFormattedTime());
                tvFromSymbol.setText(coinPriceInfo.getFromsymbol());
                tvToSymbol.setText(coinPriceInfo.getTosymbol());
                Picasso.get().load(coinPriceInfo.getFullImageUrl()).into(ivLogoCoin);
            }
        });
    }

    public static Intent newIntent(Context context, String fromSymbol) {
        Intent intent = new Intent(context, CoinDetailActivity.class);
        intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol);
        return intent;
    }

    private void initViews(){
        tvPrice = findViewById(R.id.tvPrice);
        tvMinPrice = findViewById(R.id.tvMinPrice);
        tvMaxPrice = findViewById(R.id.tvMaxPrice);
        tvLastMarket = findViewById(R.id.tvLastMarket);
        tvLastUpdate = findViewById(R.id.tvLastUpdate);
        tvFromSymbol = findViewById(R.id.tvFromSymbol);
        tvToSymbol = findViewById(R.id.tvToSymbol);
        ivLogoCoin = findViewById(R.id.ivLogoCoin);

    }
}