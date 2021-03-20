package com.example.crypto_challenge.core.service;
import com.example.crypto_challenge.core.entities.CoinDataEntity;
import com.example.crypto_challenge.core.entities.CoinEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface RemoteService {
    @Headers({"Accept: application/json"})

    @GET("coins/list")
    Call<List<CoinEntity>> getCryptoCoins();

    @GET("coins/{id}")
    Call<CoinDataEntity> getCoin(
            @Path("id") String id);
}
