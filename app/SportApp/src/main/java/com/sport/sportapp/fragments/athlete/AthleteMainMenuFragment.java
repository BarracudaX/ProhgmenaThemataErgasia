package com.sport.sportapp.fragments.athlete;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sport.sportapp.R;
import com.sport.sportapp.databinding.FragmentAthleteMainMenuBinding;
import com.sport.sportapp.databinding.FragmentTeamMainBinding;
import com.sport.sportapp.fragments.BaseFragment;
import com.sport.sportapp.views.AthletesAdapter;
import com.sport.sportapp.views.TeamsAdapter;

import viewmodels.MainActivityViewModel;

public class AthleteMainMenuFragment extends BaseFragment {

    private FragmentAthleteMainMenuBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAthleteMainMenuBinding.inflate(inflater);
        viewModel.getAthletes().observe(this, athletes -> {
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