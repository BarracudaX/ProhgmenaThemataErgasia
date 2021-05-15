package com.sport.sportapp.fragments.sport;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sport.sportapp.databinding.FragmentSportMainBinding;
import com.sport.sportapp.fragments.BaseFragment;
import com.sport.sportapp.adapters.SportsAdapter;

import java.util.ArrayList;
import java.util.List;

import domain.sport.AthleteSport;
import domain.sport.Sport;
import domain.sport.TeamSport;
import viewmodels.SportViewModel;


public class SportMainFragment extends BaseFragment {

    private FragmentSportMainBinding binding;
    private SportViewModel sportViewModel;
    private List<Sport> sports = new ArrayList<>();
    private List<AthleteSport> athleteSports = new ArrayList<>();
    private List<TeamSport> teamSports = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sportViewModel = new ViewModelProvider(getActivity()).get(SportViewModel.class);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSportMainBinding.inflate(inflater);
        sportViewModel.athleteSports().observe(getViewLifecycleOwner(), athleteSports -> {
            this.athleteSports.clear();
            this.athleteSports.addAll(athleteSports);
            sports.clear();
            sports.addAll(teamSports);
            sports.addAll(athleteSports);
            binding.sports.setAdapter(new SportsAdapter(sports,sportViewModel,mainViewModel));
        });
        sportViewModel.teamSports().observe(getViewLifecycleOwner(), teamSports ->{
            this.teamSports.clear();
            this.teamSports.addAll(teamSports);
            sports.clear();
            sports.addAll(athleteSports);
            sports.addAll(teamSports);
            binding.sports.setAdapter(new SportsAdapter(sports,sportViewModel,mainViewModel));
        });

        configureBottomNavigation(binding.bottomNavigationView);
        return  binding.getRoot();

    }

    @Override
    public void onStart() {
        super.onStart();
    }
}