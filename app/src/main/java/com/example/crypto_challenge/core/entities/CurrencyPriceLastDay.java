package com.example.crypto_challenge.core.entities;
import com.google.gson.annotations.SerializedName;

public class CurrencyPriceLastDay {
    @SerializedName("ars")
    private Double ars;

    public CurrencyPriceLastDay(Double ars) {
        this.ars = ars;
    }

    public Double getArs() {
        return ars;
    }

    public void setArs(Double ars) {
        this.ars = ars;
    }
}
