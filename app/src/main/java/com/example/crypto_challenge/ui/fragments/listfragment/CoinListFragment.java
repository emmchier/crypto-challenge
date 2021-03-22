package com.example.crypto_challenge.ui.fragments.listfragment;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.crypto_challenge.R;
import com.example.crypto_challenge.core.entities.CoinDataEntity;
import com.example.crypto_challenge.core.entities.CoinEntity;
import com.example.crypto_challenge.core.service.ServiceResult;
import com.example.crypto_challenge.ui.adapters.CoinListAdapter;
import com.example.crypto_challenge.viewmodels.CoinViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoinListFragment extends Fragment implements CoinListAdapter.CoinListener {

    public static final String COIN_ID = "id";

    private CoinListAdapter adapter;
    private CoinViewModel coinViewModel;

    @BindView(R.id.recyclerViewCoinList)
    RecyclerView recyclerViewCoinList;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    public CoinListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setExitTransition(inflater.inflateTransition(R.transition.fade));
        setEnterTransition(inflater.inflateTransition(R.transition.fade));
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(
                R.layout.fragment_coin_list,
                container,
                false);
        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerViewCoinList();
        observeViewmodelData();
    }

    private void initRecyclerViewCoinList() {
        adapter = new CoinListAdapter(this);
        recyclerViewCoinList.setAdapter(adapter);
        recyclerViewCoinList.setLayoutManager(new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false));
    }

    private void observeViewmodelData() {

        coinViewModel =
                new ViewModelProvider(this).get(CoinViewModel.class);

        final Observer<ServiceResult<List<CoinEntity>>> dataContainerObserver =
                new Observer<ServiceResult<List<CoinEntity>>>() {
                    @Override
                    public void onChanged(ServiceResult<List<CoinEntity>> dataContainer) {
                        switch(dataContainer.getStatus()) {
                            case SUCCESS:
                                if (dataContainer.getData() != null) {
                                    progressBar.setVisibility(View.GONE);
                                    adapter.loadList(dataContainer.getData());
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
        coinViewModel.coinList().observe(requireActivity(), dataContainerObserver);
    }

    @Override
    public void goToDetail(CoinEntity coinEntity) {
        Bundle bundle = new Bundle();
        bundle.putString(COIN_ID, coinEntity.getId());
        NavHostFragment
                .findNavController(CoinListFragment.this)
                .navigate(R.id.action_navigation_coin_list_to_navigation_coin_detail, bundle);
    }
}