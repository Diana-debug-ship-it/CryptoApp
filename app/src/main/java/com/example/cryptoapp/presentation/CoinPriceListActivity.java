package com.example.cryptoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.cryptoapp.adapters.CoinInfoAdapter;
import com.example.cryptoapp.adapters.OnCoinClickListener;
import com.example.cryptoapp.pojo.CoinPriceInfo;

import java.util.List;

public class CoinPriceListActivity extends AppCompatActivity {

    private static final String TAG = "TEST_LOADING_DATA";

    private CoinViewModel viewModel;
    CoinInfoAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(CoinViewModel.class);

        recyclerView = findViewById(R.id.rvCoinPriceList);
        progressBar = findViewById(R.id.pbLoading);

        adapter = new CoinInfoAdapter();
        adapter.setOnCoinClickListener(new OnCoinClickListener() {
            @Override
            public void onCoinClick(CoinPriceInfo coinPriceInfo) {
                Intent intent = CoinDetailActivity.newIntent(
                        CoinPriceListActivity.this,
                        coinPriceInfo.getFromsymbol());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CoinPriceListActivity.this));

        viewModel.getPriceList().observe(this, new Observer<List<CoinPriceInfo>>() {
            @Override
            public void onChanged(List<CoinPriceInfo> coinPriceInfos) {
                if (coinPriceInfos != null) {
                    Log.d(TAG, "onChanged: " + coinPriceInfos);
                    adapter.setCoinInfoList(coinPriceInfos);
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }
}