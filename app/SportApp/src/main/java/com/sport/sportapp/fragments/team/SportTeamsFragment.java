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

import com.sport.sportapp.adapters.TeamsAdapter;
import com.sport.sportapp.databinding.FragmentSportTeamsBinding;
import com.sport.sportapp.fragments.BaseFragment;

import viewmodels.MainViewModel;
import viewmodels.TeamViewModel;

public class SportTeamsFragment extends BaseFragment {

    private static String SPORT_ID_KEY = "SPORT_ID";
    private FragmentSportTeamsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSportTeamsBinding.  inflate(inflater);
        TeamViewModel teamViewModel = new ViewModelProvider(getActivity()).get(TeamViewModel.class);
        MainViewModel mainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);

        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.teamSports.setLayoutManager(new LinearLayoutManager(getContext()));
        }else{
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
            gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
            binding.teamSports.setLayoutManager(gridLayoutManager);
        }

        teamViewModel.teamsOfSport(getArguments().getLong(SPORT_ID_KEY)).observe(getViewLifecycleOwner(), teams -> {
            binding.teamSports.setAdapter(new TeamsAdapter(teams, teamViewModel,mainViewModel));
        });

        return binding.getRoot();
    }

    public static Bundle getBundleRequest(long sportId) {
        Bundle bundle = new Bundle();
        bundle.putLong(SPORT_ID_KEY, sportId);

        return bundle;
    }
}
