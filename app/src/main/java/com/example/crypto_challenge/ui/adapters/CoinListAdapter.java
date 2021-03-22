package com.example.crypto_challenge.ui.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.crypto_challenge.R;
import com.example.crypto_challenge.core.entities.CoinEntity;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * Adapter for coin list
 */
public class CoinListAdapter extends RecyclerView.Adapter {

    private List<CoinEntity> coinList;
    private CoinListener coinListener;

    public CoinListAdapter(CoinListener gifListener) {
        this.coinList = new ArrayList<>();
        this.coinListener = gifListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View cellView = layoutInflater.inflate(
                R.layout.coin_cell,
                parent,
                false);
        ViewHolder viewHolder = new ViewHolder(cellView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(
            @NonNull RecyclerView.ViewHolder holder,
            int position) {
        CoinEntity coin = coinList.get(position);
        ViewHolder vh = (ViewHolder) holder;
        vh.loadAllCoins(coin);
    }

    @Override
    public int getItemCount() {
        return coinList.size();
    }

    public void loadList(List<CoinEntity> list) {
        if(list != null){
            coinList.clear();
            coinList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewCoinName)
        TextView textViewCoinName;

        @BindView(R.id.textViewTicker)
        TextView textViewTicker;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    coinListener.goToDetail(coinList.get(getAdapterPosition()));
                }
            } );
        }

        public void loadAllCoins(CoinEntity coin) {
            if (coin != null) {
                textViewTicker.setText(coin.getSymbol());
                textViewCoinName.setText(coin.getName());
            }
        }
    }

    public interface CoinListener {
        void goToDetail(CoinEntity coinEntity);
    }
}
