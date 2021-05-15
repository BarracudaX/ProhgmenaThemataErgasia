package com.sport.sportapp.fragments.athlete;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sport.sportapp.adapters.AthletesAdapter;
import com.sport.sportapp.databinding.FragmentAthleteMainMenuBinding;
import com.sport.sportapp.fragments.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import domain.athlete.Athlete;
import domain.athlete.AthleteSingle;
import domain.athlete.AthleteTeam;
import viewmodels.AthleteViewModel;

public class AthleteMainMenuFragment extends BaseFragment {

    private FragmentAthleteMainMenuBinding binding;
    private AthleteViewModel athleteViewModel;
    private List<AthleteSingle> athletesSingle = new ArrayList<>();
    private List<AthleteTeam> athletesTeam = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.athleteViewModel = new ViewModelProvider(this).get(AthleteViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAthleteMainMenuBinding.inflate(inflater);
        athleteViewModel.athletesSingle().observe(getViewLifecycleOwner(), athletesSingle -> {
            this.athletesSingle.clear();
            this.athletesSingle.addAll(athletesSingle);

            List<Athlete> athletes = new ArrayList<>();
            athletes.addAll(athletesTeam);
            athletes.addAll(athletesSingle);

            binding.athletes.setAdapter(new AthletesAdapter(athletes, mainViewModel, athleteViewModel));
        });

        athleteViewModel.athletesTeam().observe(getViewLifecycleOwner(), athletesTeam -> {
            this.athletesTeam.clear();
            this.athletesTeam.addAll(athletesTeam);

            List<Athlete> athletes = new ArrayList<>();
            athletes.addAll(athletesTeam);
            athletes.addAll(athletesSingle);

            binding.athletes.setAdapter(new AthletesAdapter(athletes, mainViewModel, athleteViewModel));
        });

        configureBottomNavigation(binding.bottomNavigationView);
        return binding.getRoot();

    }

    @Override
    public void onStart() {
        super.onStart();
    }
}