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
import com.sport.sportapp.databinding.FragmentAthleteMainMenuBinding;
import com.sport.sportapp.databinding.FragmentEventsBinding;
import com.sport.sportapp.views.AthletesAdapter;
import com.sport.sportapp.views.SingleMatchAdapter;

import viewmodels.MainActivityViewModel;

public class EventsFragment extends BaseFragment {

    private FragmentEventsBinding binding;
    private MainActivityViewModel viewModel;

    public EventsFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEventsBinding.inflate(inflater);
        viewModel.getSingleMatches().observe(this, events -> {
            binding.events.setAdapter(new SingleMatchAdapter(events,viewModel));
        });
        configureBottomNavigation(binding.bottomNavigationView);
        return  binding.getRoot();
    }
    @Override
    public void onStart() {
        super.onStart();
    }
}