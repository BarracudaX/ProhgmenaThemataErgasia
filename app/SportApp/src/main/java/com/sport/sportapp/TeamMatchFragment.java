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
import com.sport.sportapp.databinding.FragmentTeamMatchBinding;
import com.sport.sportapp.fragments.BaseFragment;
import com.sport.sportapp.views.TeamMatchAdapter;

import viewmodels.MainActivityViewModel;

public class TeamMatchFragment extends BaseFragment {

    private FragmentTeamMatchBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTeamMatchBinding.inflate(inflater);
        viewModel.getTeamMatches().observe(this, events -> {
            binding.events.setAdapter(new TeamMatchAdapter(events,viewModel));
        });
        // configureBottomNavigation(binding.bottomNavigationView);
        return binding.getRoot();
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    public static Bundle getBundleRequest() {
        Bundle bundle = new Bundle();
        return bundle;
    }
}