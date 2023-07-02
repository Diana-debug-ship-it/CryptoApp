package com.example.cryptoapp.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptoapp.R;
import com.example.cryptoapp.data.network.model.CoinInfoDto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CoinInfoAdapter extends RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder> {

    private List<CoinInfoDto> coinInfoList = new ArrayList<>();
    private OnCoinClickListener onCoinClickListener;


    public OnCoinClickListener getOnCoinClickListener() {
        return onCoinClickListener;
    }

    public void setOnCoinClickListener(OnCoinClickListener onCoinClickListener) {
        this.onCoinClickListener = onCoinClickListener;
    }


    public void setCoinInfoList(List<CoinInfoDto> coinInfoList) {
        this.coinInfoList = coinInfoList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CoinInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
          R.layout.item_coin_info,
          parent,
          false
        );
        return new CoinInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoinInfoViewHolder holder, int position) {
        CoinInfoDto coin = coinInfoList.get(position);
        String symbolsTemplate = holder.itemView.getContext().getResources()
                .getString(R.string.symbols_template);
        String lastUpdateTemplate = holder.itemView.getContext().getResources()
                .getString(R.string.last_update_template);
        holder.tvSymbols.setText(String.format(Locale.getDefault(),
                symbolsTemplate,
                coin.getFromsymbol(),
                coin.getTosymbol()));
        holder.tvPrice.setText(coin.getPrice());
        holder.tvLastUpdate.setText(String.format(lastUpdateTemplate, coin.getFormattedTime()));
        Picasso.get().load(coin.getFullImageUrl()).into(holder.ivLogoCoin);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCoinClickListener.onCoinClick(coin);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (coinInfoList==null) return 0;
        return coinInfoList.size();
    }

    public static class CoinInfoViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivLogoCoin;
        private TextView tvSymbols;
        private TextView tvPrice;
        private TextView tvLastUpdate;

        public CoinInfoViewHolder(@NonNull View itemView) {
            super(itemView);

            ivLogoCoin = itemView.findViewById(R.id.ivLogoCoin);
            tvSymbols = itemView.findViewById(R.id.tvSymbols);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvLastUpdate = itemView.findViewById(R.id.tvLastUpdate);
        }
    }
}
