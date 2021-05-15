package com.sport.sportapp.fragments.athlete;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;

import com.sport.sportapp.databinding.FragmentInsertTeamAthleteBinding;
import com.sport.sportapp.fragments.BaseFragment;
import com.sport.sportapp.fragments.DatePicker;
import com.sport.sportapp.spinner.TeamAdapter;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

import domain.athlete.AthleteTeam;
import domain.team.Team;
import viewmodels.AthleteViewModel;
import viewmodels.TeamViewModel;

public class InsertTeamAthleteFragment extends BaseFragment {

    private TeamViewModel teamViewModel;
    private AthleteViewModel athleteViewModel;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        teamViewModel = new ViewModelProvider(getActivity()).get(TeamViewModel.class);
        athleteViewModel = new ViewModelProvider(getActivity()).get(AthleteViewModel.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        FragmentInsertTeamAthleteBinding binding = FragmentInsertTeamAthleteBinding.inflate(inflater,container,false);
        DatePicker datePicker = new DatePicker(localDate -> binding.athleteDateOfBirthInput.setText(localDate.toString()), LocalDate.now());

        teamViewModel.teams().observe(getViewLifecycleOwner(),teams -> {
            binding.athleteTeamInput.setAdapter(new TeamAdapter(getContext(),teams));
        });

        binding.athleteDateOfBirthInput.setOnClickListener(v -> {
            datePicker.show(getChildFragmentManager(),"datePicker");
        });

        binding.createAthleteButton.setOnClickListener(v -> {
            AthleteTeam athleteTeam = new AthleteTeam(
                    binding.athleteNameInput.getText().toString(),
                    binding.athleteSurnameInput.getText().toString(),
                    binding.athleteCityInput.getText().toString(),
                    binding.athleteCountryInput.getText().toString(),
                    datePicker.getLocalDatePicked(),
                    ((Team)binding.athleteTeamInput.getSelectedItem()).getTeamId()
            );
            athleteViewModel.insertAthleteTeam(athleteTeam);
            mainViewModel.navigateBack();
            Toast.makeText(getContext(), "Athlete added successfully", Toast.LENGTH_LONG).show();
        });


        return binding.getRoot();
    }
}
