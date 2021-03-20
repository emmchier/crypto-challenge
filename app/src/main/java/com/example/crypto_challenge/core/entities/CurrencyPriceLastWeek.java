package com.example.crypto_challenge.core.entities;

import com.google.gson.annotations.SerializedName;

public class CurrencyPriceLastWeek {
    @SerializedName("ars")
    private String ars;

    public CurrencyPriceLastWeek(String ars) {
        this.ars = ars;
    }

    public String getArs() {
        return ars;
    }

    public void setArs(String ars) {
        this.ars = ars;
    }
}
