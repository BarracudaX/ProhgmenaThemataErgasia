package com.sport.sportapp.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import viewmodels.MainActivityViewModel;

public abstract class BaseFragment extends Fragment {

    private MainActivityViewModel viewModel;
    private NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
        navController = ((NavHostFragment)getParentFragment()).getNavController();
        viewModel.navigationToEvent().observe(this, request -> {
            navController.navigate(request.getDestinationId(),request.getBundle());
        });
        viewModel.navigateBackEvent().observe(this,ignored -> navController.navigateUp());
    }

    protected void configureBottomNavigation(BottomNavigationView bottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            navController.navigate(item.getItemId());
            return true;
        });
    }
}
