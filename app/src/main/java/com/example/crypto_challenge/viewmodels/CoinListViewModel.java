package com.example.crypto_challenge.viewmodels;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.crypto_challenge.core.entities.CoinEntity;
import com.example.crypto_challenge.repository.Repository;
import com.example.crypto_challenge.core.service.ServiceResult;

import java.util.List;

/*
 * Coin List Viewmodel
 */
public class CoinListViewModel extends ViewModel {

    private MutableLiveData<ServiceResult<List<CoinEntity>>> coinEntityList;
    private Repository repository;

    public CoinListViewModel() {
        coinEntityList = new MutableLiveData<>();
        repository = new Repository();
        getCoinList();
    }

    private void getCoinList() {
        coinEntityList = repository.getCoinList();
    }

    public LiveData<ServiceResult<List<CoinEntity>>> coinList() {
        return coinEntityList;
    }
}
