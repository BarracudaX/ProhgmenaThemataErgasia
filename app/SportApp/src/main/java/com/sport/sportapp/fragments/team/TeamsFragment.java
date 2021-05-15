package com.sport.sportapp.fragments.team;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sport.sportapp.databinding.FragmentTeamMainBinding;
import com.sport.sportapp.fragments.BaseFragment;
import com.sport.sportapp.adapters.TeamsAdapter;

import viewmodels.TeamViewModel;

public class TeamsFragment extends BaseFragment {

    private  FragmentTeamMainBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTeamMainBinding.inflate(inflater);
        TeamViewModel teamViewModel = new ViewModelProvider(getActivity()).get(TeamViewModel.class);
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.teams.setLayoutManager(new LinearLayoutManager(getContext()));
        }else{
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
            gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
            binding.teams.setLayoutManager(gridLayoutManager);
        }
        teamViewModel.teams().observe(getViewLifecycleOwner(),teams -> {
            binding.teams.setAdapter(new TeamsAdapter(teams,teamViewModel, mainViewModel));
        });
        configureBottomNavigation(binding.bottomNavigationView);
        return binding.getRoot();
    }

}
