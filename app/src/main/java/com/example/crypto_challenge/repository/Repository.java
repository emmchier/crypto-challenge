package com.example.crypto_challenge.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.crypto_challenge.core.entities.CoinDataEntity;
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
    private final MutableLiveData<ServiceResult<CoinDataEntity>> result;

    public Repository() {
        this.results = new MutableLiveData<>();
        this.result = new MutableLiveData<>();
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

    public MutableLiveData<ServiceResult<CoinDataEntity>> getCoinById(String coinId) {

        result.setValue(new ServiceResult<>(
                ServiceResult.Status.LOADING,
                null,
                null));

        RetrofitService.getInstance().create(RemoteService.class)
                .getCoin(coinId)
                .enqueue(new Callback<CoinDataEntity>() {
                    @Override
                    public void onResponse(Call<CoinDataEntity> call,
                                           Response<CoinDataEntity> response) {
                        if (response.isSuccessful()) {
                            result.setValue(new ServiceResult(
                                    ServiceResult.Status.SUCCESS,
                                    response.body(),
                                    null));
                        } else {
                            result.setValue(new ServiceResult(
                                    ServiceResult.Status.ERROR,
                                    null,
                                    "An error has occurred. Please try again"));
                        }
                    }
                    @Override
                    public void onFailure(Call<CoinDataEntity> call, Throwable t) {
                        result.setValue(new ServiceResult(
                                ServiceResult.Status.ERROR,
                                null,
                                "An error has occurred. Please try again"));
                    }
                });
        return result;
    }
}
