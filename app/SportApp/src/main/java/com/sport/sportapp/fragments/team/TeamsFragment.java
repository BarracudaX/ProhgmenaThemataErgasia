package com.sport.sportapp.fragments.team;

import android.content.res.Configuration;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sport.sportapp.MainActivity;
import com.sport.sportapp.R;
import com.sport.sportapp.databinding.FragmentTeamMainBinding;
import com.sport.sportapp.databinding.FragmentWelcomeBinding;
import com.sport.sportapp.fragments.BaseFragment;
import com.sport.sportapp.views.TeamsAdapter;

import viewmodels.MainActivityViewModel;

public class TeamsFragment extends BaseFragment {

    private  FragmentTeamMainBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTeamMainBinding.inflate(inflater);
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.teams.setLayoutManager(new LinearLayoutManager(getContext()));
        }else{
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
            gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
            binding.teams.setLayoutManager(gridLayoutManager);
        }
        activityViewModel.getTeams().observe(this,teams -> {
            binding.teams.setAdapter(new TeamsAdapter(teams,activityViewModel));
        });
        configureBottomNavigation(binding.bottomNavigationView);
        return binding.getRoot();
    }

}
