package com.example.crypto_challenge.core.entities;

import com.google.gson.annotations.SerializedName;

public class Image {
    @SerializedName("small")
    private String small;

    public Image(String small) {
        this.small = small;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }
}
