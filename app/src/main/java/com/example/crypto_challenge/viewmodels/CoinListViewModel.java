package com.example.crypto_challenge.viewmodels;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.crypto_challenge.core.entities.CoinDataEntity;
import com.example.crypto_challenge.core.entities.CoinEntity;
import com.example.crypto_challenge.repository.Repository;
import com.example.crypto_challenge.core.service.ServiceResult;

import java.util.List;

/*
 * Coin List Viewmodel
 */
public class CoinListViewModel extends ViewModel {

    private MutableLiveData<ServiceResult<List<CoinEntity>>> coinEntityList;
    private MutableLiveData<ServiceResult<CoinDataEntity>> coinById;
    private String id;
    private Repository repository;

    public CoinListViewModel() {
        coinEntityList = new MutableLiveData<>();
        coinById = new MutableLiveData<>();
        repository = new Repository();
        getCoinList();
        getCoinById(id);
    }

    private void getCoinList() {
        coinEntityList = repository.getCoinList();
    }

    public LiveData<ServiceResult<List<CoinEntity>>> coinList() {
        return coinEntityList;
    }

    public void getCoinById(String id) {
        coinById = repository.getCoinById(id);
    }

    public LiveData<ServiceResult<CoinDataEntity>> getCoin() {
        return coinById;
    }
}
