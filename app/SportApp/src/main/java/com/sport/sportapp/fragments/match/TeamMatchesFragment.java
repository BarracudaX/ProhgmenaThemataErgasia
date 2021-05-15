package com.sport.sportapp.fragments.match;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.sport.sportapp.adapters.TeamMatchesAdapter;
import com.sport.sportapp.databinding.FragmentTeamMatchesBinding;
import com.sport.sportapp.fragments.BaseFragment;

import org.jetbrains.annotations.NotNull;

import viewmodels.MatchViewModel;
import viewmodels.TeamViewModel;

public class TeamMatchesFragment extends BaseFragment {

    private MatchViewModel matchViewModel;
    private TeamViewModel teamViewModel;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        matchViewModel = new ViewModelProvider(getActivity()).get(MatchViewModel.class);
        teamViewModel = new ViewModelProvider(getActivity()).get(TeamViewModel.class);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        FragmentTeamMatchesBinding binding = FragmentTeamMatchesBinding.inflate(inflater, container, false);
        configureBottomNavigation(binding.bottomNavigationView);
        matchViewModel.teamMatches().observe(getViewLifecycleOwner(),teamMatches -> {
            binding.teamMatches.setAdapter(new TeamMatchesAdapter(mainViewModel,matchViewModel,teamViewModel,teamMatches));
        });

        return binding.getRoot();
    }
}
