package com.sport.sportapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sport.sportapp.databinding.FragmentEventsBinding;

public class EventsFragment extends BaseFragment {

    private FragmentEventsBinding binding;

    public EventsFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEventsBinding.inflate(inflater);
        configureBottomNavigation(binding.bottomNavigationView);
        return  binding.getRoot();

    }
    @Override
    public void onStart() {
        super.onStart();
    }
}