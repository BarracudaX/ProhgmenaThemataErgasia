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

    protected MainActivityViewModel activityViewModel;
    protected NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityViewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
        navController = ((NavHostFragment)getParentFragment()).getNavController();
        activityViewModel.navigationToEvent().observe(this, request -> {
            navController.navigate(request.getDestinationId(),request.getBundle());
        });
        activityViewModel.navigateBackEvent().observe(this, ignored -> navController.navigateUp());
    }

    protected void configureBottomNavigation(BottomNavigationView bottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            navController.navigate(item.getItemId());
            return true;
        });
    }
}
