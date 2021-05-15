package com.sport.sportapp.fragments.sport;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sport.sportapp.R;
import com.sport.sportapp.databinding.FragmentInsertTeamSportBinding;
import com.sport.sportapp.fragments.BaseFragment;
import com.sport.sportapp.spinner.GenderAdapter;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import domain.Gender;
import domain.sport.TeamSport;
import viewmodels.SportViewModel;


public class InsertSportTeamFragment extends BaseFragment {

    private FragmentInsertTeamSportBinding binding;
    private SportViewModel sportViewModel;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sportViewModel = new ViewModelProvider(getActivity()).get(SportViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentInsertTeamSportBinding.inflate(inflater, container, false);
        List<Gender> genders = new ArrayList<>(Arrays.asList(Gender.values()));
        binding.sportGenderSpinner.setAdapter(new GenderAdapter(getContext(),genders));
        binding.createSportButton.setOnClickListener((v) -> {
            sportViewModel.insertTeamSport(
                    new TeamSport(binding.sportNameInput.getText().toString(), (Gender) binding.sportGenderSpinner.getSelectedItem())
            );
            mainViewModel.navigateBack();
            Toast.makeText(getActivity(), R.string.added_sport_success_message, Toast.LENGTH_LONG).show();
        });
        return binding.getRoot();
    }
}