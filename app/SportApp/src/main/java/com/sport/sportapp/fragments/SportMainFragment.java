package com.sport.sportapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sport.sportapp.R;
import com.sport.sportapp.databinding.FragmentSportMainBinding;
import com.sport.sportapp.views.SportsAdapter;

import domain.SportIdNameModel;
import viewmodels.MainActivityViewModel;


public class SportMainFragment extends BaseFragment {

    private FragmentSportMainBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSportMainBinding.inflate(inflater);
        viewModel.getSports().observe(this, sports -> {
            binding.sports.setAdapter(new SportsAdapter(sports, viewModel));
        });

        configureBottomNavigation(binding.bottomNavigationView);
        return  binding.getRoot();

    }

    @Override
    public void onStart() {
        super.onStart();
    }
}