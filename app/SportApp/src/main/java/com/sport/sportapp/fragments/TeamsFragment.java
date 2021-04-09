package com.sport.sportapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sport.sportapp.MainActivity;
import com.sport.sportapp.R;
import com.sport.sportapp.databinding.FragmentTeamMainBinding;
import com.sport.sportapp.databinding.FragmentWelcomeBinding;
import com.sport.sportapp.views.TeamsAdapter;

import viewmodels.MainActivityViewModel;

public class TeamsFragment extends BaseFragment {

    private  FragmentTeamMainBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTeamMainBinding.inflate(inflater);

        viewModel.getTeams().observe(this,teams -> {
            binding.teams.setAdapter(new TeamsAdapter(teams,viewModel));
        });

        configureBottomNavigation(binding.bottomNavigationView);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
