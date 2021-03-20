package com.example.crypto_challenge.core.entities;

import com.google.gson.annotations.SerializedName;

public class CurrencyPriceLastMonth {
    @SerializedName("ars")
    private Double ars;

    public CurrencyPriceLastMonth(Double ars) {
        this.ars = ars;
    }

    public Double getArs() {
        return ars;
    }

    public void setArs(Double ars) {
        this.ars = ars;
    }
}
