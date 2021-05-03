package com.sport.sportapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.sport.sportapp.databinding.FragmentAthleteBySportBinding;
import com.sport.sportapp.databinding.FragmentAthleteMainMenuBinding;
import com.sport.sportapp.views.AthletesAdapter;
import com.sport.sportapp.views.AthletesBySportAdapter;

import viewmodels.MainActivityViewModel;

public class AthleteBySportFragment extends BaseFragment {

    private FragmentAthleteBySportBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAthleteBySportBinding.inflate(inflater);
        viewModel.getAthletesBySport().observe(this, athletesBySport -> {
            binding.athletesBySport.setAdapter(new AthletesBySportAdapter(athletesBySport,viewModel));
        });


        configureBottomNavigation(binding.bottomNavigationView);
        return  binding.getRoot();

    }

    @Override
    public void onStart() {
        super.onStart();
    }
}