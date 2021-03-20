package com.example.crypto_challenge.ui.fragments.detailfragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.crypto_challenge.R;
import com.example.crypto_challenge.core.entities.CoinDataEntity;
import com.example.crypto_challenge.core.entities.CoinEntity;
import com.example.crypto_challenge.core.service.ServiceResult;
import com.example.crypto_challenge.ui.adapters.CoinListAdapter;
import com.example.crypto_challenge.ui.fragments.listfragment.CoinListFragment;
import com.example.crypto_challenge.viewmodels.CoinListViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoinDetailFragment extends Fragment {

    private CoinListViewModel coinListViewModel;
    private String idCoin;

    @BindView(R.id.progressBarDetail)
    ProgressBar progressBar;

    @BindView(R.id.textViewCoinId)
    TextView textViewCoinId;

    public CoinDetailFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_coin_detail, container, false);
        ButterKnife.bind(this, root);
        getData();
        
        return root;
    }

    private void getData() {
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            idCoin = bundle.getString(CoinListFragment.COIN_ID);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        observeViewmodelData();
    }

    private void observeViewmodelData() {
        coinListViewModel =
                new ViewModelProvider(this).get(CoinListViewModel.class);

        final Observer<ServiceResult<CoinDataEntity>> dataContainerObserver =
                new Observer<ServiceResult<CoinDataEntity>>() {
                    @Override
                    public void onChanged(ServiceResult<CoinDataEntity> dataContainer) {
                        switch(dataContainer.getStatus()) {
                            case SUCCESS:
                                if (dataContainer.getData() != null) {
                                    progressBar.setVisibility(View.GONE);
                                    textViewCoinId.setText(coinListViewModel.getCoin().getValue().getData().getSymbol());
                                }
                                break;
                            case ERROR:
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(
                                        getContext(),
                                        getString(R.string.unexpected_error),
                                        Toast.LENGTH_SHORT).show();
                                break;
                            case LOADING:
                                progressBar.setVisibility(View.VISIBLE);
                                break;
                        }
                    }
                };
        coinListViewModel.getCoin().observe(requireActivity(), dataContainerObserver);
    }
}