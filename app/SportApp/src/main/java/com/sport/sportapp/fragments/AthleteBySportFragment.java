package com.sport.sportapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.sport.sportapp.databinding.FragmentAthleteMainMenuBinding;
import com.sport.sportapp.views.AthletesAdapter;

import viewmodels.MainActivityViewModel;

public class AthleteBySportFragment extends BaseFragment {

    private FragmentAthleteMainMenuBinding binding;
    private MainActivityViewModel viewModel;
    LiveData<Integer> sportCount;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAthleteMainMenuBinding.inflate(inflater);
        sportCount=viewModel.getSportCount();
        viewModel.getAthletesBySport().observe(this, athletes -> {
            binding.athletes.setAdapter(new AthletesAdapter(athletes,viewModel));
        });

        configureBottomNavigation(binding.bottomNavigationView);
        return  binding.getRoot();

    }

    @Override
    public void onStart() {
        super.onStart();
    }
}