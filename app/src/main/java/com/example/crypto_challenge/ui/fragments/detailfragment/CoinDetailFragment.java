package com.example.crypto_challenge.ui.fragments.detailfragment;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.example.crypto_challenge.R;
import com.example.crypto_challenge.core.entities.CoinDataEntity;
import com.example.crypto_challenge.core.service.ServiceResult;
import com.example.crypto_challenge.ui.fragments.listfragment.CoinListFragment;
import com.example.crypto_challenge.viewmodels.CoinViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoinDetailFragment extends Fragment {

    private CoinViewModel coinViewModel;
    private String idCoin;

    @BindView(R.id.progressBarDetail)
    ProgressBar progressBar;

    @BindView(R.id.imageViewCoinLogo)
    ImageView imageViewCoinLogo;

    @BindView(R.id.textViewCoinDetailName)
    TextView textViewCoinDetailName;

    @BindView(R.id.textViewCurrencyPrice)
    TextView textViewCurrencyPrice;

    @BindView(R.id.textViewChangeDay)
    TextView textViewChangeDay;

    @BindView(R.id.textViewChangeWeek)
    TextView textViewChangeWeek;

    @BindView(R.id.textViewChangeMonth)
    TextView textViewChangeMonth;

    @BindView(R.id.textViewSupply)
    TextView textViewSupply;

    @BindView(R.id.textViewDetailTicker)
    TextView textViewDetailTicker;

    public CoinDetailFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setExitTransition(inflater.inflateTransition(R.transition.slide_left));
        setEnterTransition(inflater.inflateTransition(R.transition.slide_right));
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
        coinViewModel =
                new ViewModelProvider(this).get(CoinViewModel.class);

        final Observer<ServiceResult<CoinDataEntity>> coinContainer =
                new Observer<ServiceResult<CoinDataEntity>>() {
                    @Override
                    public void onChanged(ServiceResult<CoinDataEntity> dataContainer) {
                        switch(dataContainer.getStatus()) {
                            case SUCCESS:
                                if (dataContainer.getData() != null) {
                                    progressBar.setVisibility(View.GONE);
                                    setDetailFields(dataContainer.getData());
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
        coinViewModel.getCoin().observe(requireActivity(), coinContainer);
        coinViewModel.getCoinById(idCoin);
    }

    @SuppressLint("SetTextI18n")
    private void setDetailFields(CoinDataEntity coin) {
        if (coin != null) {
            if (coin.getImage().getSmall() != null || coin.getImage().getSmall() != "") {
                Glide.with(getContext())
                        .load(coin.getImage().getSmall())
                        .placeholder(R.drawable.ic_crypto_app)
                        .error(R.drawable.ic_crypto_app)
                        .circleCrop()
                        .into(imageViewCoinLogo);
            } else {
                imageViewCoinLogo.setImageResource(R.drawable.ic_crypto_app);
            }
            textViewCoinDetailName.setText(coin.getName());
            textViewDetailTicker.setText(coin.getSymbol());
            if (coin.getMarket_data().getCurrent_price().getArs() != null) {
                textViewCurrencyPrice.setText(String.valueOf(coin.getMarket_data().getCurrent_price().getArs()));
            } else {
                textViewCurrencyPrice.setText("0.0");
            }
            if (coin.getMarket_data().getPrice_change_percentage_24h_in_currency().getArs() != null) {
                textViewChangeDay.setText(String.valueOf(coin.getMarket_data().getPrice_change_percentage_24h_in_currency().getArs()));
            } else {
                textViewChangeDay.setText("0.0");
            }
            if (coin.getMarket_data().getPrice_change_percentage_7d_in_currency().getArs() != null) {
                textViewChangeWeek.setText(String.valueOf(coin.getMarket_data().getPrice_change_percentage_7d_in_currency().getArs()));
            } else {
                textViewChangeWeek.setText("0.0");
            }
            if (coin.getMarket_data().getPrice_change_percentage_30d_in_currency().getArs() != null) {
                textViewChangeMonth.setText(String.valueOf(coin.getMarket_data().getPrice_change_percentage_30d_in_currency().getArs()));
            } else {
                textViewChangeMonth.setText("0.0");
            }
            if (coin.getMarket_data().getCirculating_supply() != null) {
                textViewSupply.setText(String.valueOf(coin.getMarket_data().getCirculating_supply()));
            } else {
                textViewSupply.setText("0.0");
            }
        }
    }
}