package com.example.crypto_challenge.core.entities;
import com.google.gson.annotations.SerializedName;

public class MarketData {
    @SerializedName("current_price")
    private CurrentPrince current_price;
    @SerializedName("market_cap")
    private MarketCap market_cap;
    @SerializedName("price_change_percentage_24h_in_currency")
    private CurrencyPriceLastDay price_change_percentage_24h_in_currency;
    @SerializedName("price_change_percentage_7d_in_currency")
    private CurrencyPriceLastWeek price_change_percentage_7d_in_currency;
    @SerializedName("price_change_percentage_30d_in_currency")
    private CurrencyPriceLastMonth price_change_percentage_30d_in_currency;
    @SerializedName("circulating_supply")
    private Double circulating_supply;

    public MarketData(
            CurrentPrince current_price,
            MarketCap market_cap,
            CurrencyPriceLastDay price_change_percentage_24h_in_currency,
            CurrencyPriceLastWeek price_change_percentage_7d_in_currency,
            CurrencyPriceLastMonth price_change_percentage_30d_in_currency,
            Double circulating_supply) {
        this.current_price = current_price;
        this.market_cap = market_cap;
        this.price_change_percentage_24h_in_currency =
                price_change_percentage_24h_in_currency;
        this.price_change_percentage_7d_in_currency =
                price_change_percentage_7d_in_currency;
        this.price_change_percentage_30d_in_currency =
                price_change_percentage_30d_in_currency;
        this.circulating_supply = circulating_supply;
    }

    public CurrentPrince getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(CurrentPrince current_price) {
        this.current_price = current_price;
    }

    public MarketCap getMarket_cap() {
        return market_cap;
    }

    public void setMarket_cap(MarketCap market_cap) {
        this.market_cap = market_cap;
    }

    public CurrencyPriceLastDay getPrice_change_percentage_24h_in_currency() {
        return price_change_percentage_24h_in_currency;
    }

    public void setPrice_change_percentage_24h_in_currency(CurrencyPriceLastDay price_change_percentage_24h_in_currency) {
        this.price_change_percentage_24h_in_currency = price_change_percentage_24h_in_currency;
    }

    public CurrencyPriceLastWeek getPrice_change_percentage_7d_in_currency() {
        return price_change_percentage_7d_in_currency;
    }

    public void setPrice_change_percentage_7d_in_currency(CurrencyPriceLastWeek price_change_percentage_7d_in_currency) {
        this.price_change_percentage_7d_in_currency = price_change_percentage_7d_in_currency;
    }

    public CurrencyPriceLastMonth getPrice_change_percentage_30d_in_currency() {
        return price_change_percentage_30d_in_currency;
    }

    public void setPrice_change_percentage_30d_in_currency(CurrencyPriceLastMonth price_change_percentage_30d_in_currency) {
        this.price_change_percentage_30d_in_currency = price_change_percentage_30d_in_currency;
    }

    public Double getCirculating_supply() {
        return circulating_supply;
    }

    public void setCirculating_supply(Double circulating_supply) {
        this.circulating_supply = circulating_supply;
    }
}
