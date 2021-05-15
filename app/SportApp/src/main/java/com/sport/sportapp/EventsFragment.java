package com.sport.sportapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sport.sportapp.R;
import com.sport.sportapp.databinding.FragmentEventsBinding;
import com.sport.sportapp.fragments.BaseFragment;
import com.sport.sportapp.fragments.SingleMatchesFragment;
import com.sport.sportapp.fragments.TeamMatchesFragment;

import viewmodels.MainActivityViewModel;

public class EventsFragment extends BaseFragment {
    protected FragmentEventsBinding binding;
    protected MainActivityViewModel viewModel;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEventsBinding.inflate(inflater, container, false);
        binding.SingleButton.setOnClickListener((v) -> {
            viewModel.navigateTo(R.id.singleMatchesFragment, SingleMatchesFragment.getBundleRequest());
            viewModel.navigateTo(R.id.teamMatchesFragment, TeamMatchesFragment.getBundleRequest());
        });
        configureBottomNavigation(binding.bottomNavigationView);
        return  binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}