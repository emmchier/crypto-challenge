package com.example.crypto_challenge.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.crypto_challenge.core.entities.CoinEntity;
import com.example.crypto_challenge.core.service.RemoteService;
import com.example.crypto_challenge.core.service.RetrofitService;
import com.example.crypto_challenge.core.service.ServiceResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * Repository Pattern
 */
public class Repository {

    private final MutableLiveData<ServiceResult<List<CoinEntity>>> results;

    public Repository() {
        this.results = new MutableLiveData<>();
    }

    public MutableLiveData<ServiceResult<List<CoinEntity>>> getCoinList() {

        results.setValue(new ServiceResult<>(
                ServiceResult.Status.LOADING,
                null,
                null));

        RetrofitService.getInstance().create(RemoteService.class)
            .getCryptoCoins()
            .enqueue(new Callback<List<CoinEntity>>() {
                @Override
                public void onResponse(Call<List<CoinEntity>> call,
                                       Response<List<CoinEntity>> response) {
                    if (response.isSuccessful()) {
                        results.setValue(new ServiceResult(
                                ServiceResult.Status.SUCCESS,
                                response.body(),
                                null));
                    } else {
                        results.setValue(new ServiceResult(
                                ServiceResult.Status.ERROR,
                                null,
                                "An error has occurred. Please try again"));
                    }
                }
                @Override
                public void onFailure(Call<List<CoinEntity>> call, Throwable t) {
                    results.setValue(new ServiceResult(
                            ServiceResult.Status.ERROR,
                            null,
                            "An error has occurred. Please try again"));
                }
            });
        return results;
    }
}
