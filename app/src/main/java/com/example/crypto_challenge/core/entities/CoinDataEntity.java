package com.example.crypto_challenge.core.entities;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CoinDataEntity {

    @SerializedName("symbol")
    private String symbol;
    @SerializedName("image")
    private Image image;
    @SerializedName("market_data")
    private MarketData market_data;

    public CoinDataEntity(String symbol, Image image, MarketData market_data) {
        this.symbol = symbol;
        this.image = image;
        this.market_data = market_data;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public MarketData getMarket_data() {
        return market_data;
    }

    public void setMarket_data(MarketData market_data) {
        this.market_data = market_data;
    }
}
