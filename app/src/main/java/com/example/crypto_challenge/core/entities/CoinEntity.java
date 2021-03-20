package com.example.crypto_challenge.core.entities;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class CoinEntity implements Parcelable {

    @SerializedName("id")
    private String id;
    @SerializedName("symbol")
    private String symbol;
    @SerializedName("name")
    private String name;

    public CoinEntity(String id, String symbol, String name) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.symbol);
        dest.writeString(this.name);
    }

    protected CoinEntity(Parcel in) {
        this.id = in.readString();
        this.symbol = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<CoinEntity> CREATOR =
            new Parcelable.Creator<CoinEntity>() {
        @Override
        public CoinEntity createFromParcel(Parcel source) {
            return new CoinEntity(source);
        }

        @Override
        public CoinEntity[] newArray(int size) {
            return new CoinEntity[size];
        }
    };
}
