package com.sport.sportapp;

import android.os.Build;
import android.os.Bundle;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.sport.sportapp.databinding.FragmentInsertSingleMatchBinding;
import com.sport.sportapp.databinding.FragmentInsertTeamMatchBinding;
import com.sport.sportapp.fragments.BaseFragment;
import com.sport.sportapp.fragments.DatePicker;

import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import domain.AthleteIdNameModel;
import domain.SingleMatch;
import domain.TeamIdNameModel;
import domain.TeamMatch;
import domain.SportIdNameModel;
import domain.SportType;
import viewmodels.MainActivityViewModel;

public class InsertTeamMatchFragment extends BaseFragment {

    protected FragmentInsertTeamMatchBinding binding;
    protected MainActivityViewModel viewModel;
    private Spinner sportSpinner;
    private Spinner team1Spinner;
    private Spinner team2Spinner;
    private final Set<String> spinnerData = new HashSet<>();
    private final Set<String> team1SpinnerData = new HashSet<>();
    private final Set<String> team2SpinnerData = new HashSet<>();
    private Date lastDatePicked;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentInsertTeamMatchBinding.inflate(inflater, container, false);

        binding.matchDateInput.setOnClickListener(v -> {
            DialogFragment dialogFragment = new DatePicker();
            dialogFragment.show(getChildFragmentManager(),"datePicker");
        });

        createSportSpinner();
        createTeam1Spinner();
        createTeam2Spinner();

        binding.createTeamEventButton.setOnClickListener((v) -> {
            String sportIdAsString = ((String) sportSpinner.getSelectedItem());
            long sportId = Long.parseLong(sportIdAsString.substring(0, sportIdAsString.indexOf("-")));
            viewModel.insertTeamMatch(new TeamMatch(lastDatePicked,binding.matchCityInput.getText().toString(),binding.matchCountryInput.getText().toString(),viewModel.getSportById(sportId)));
        });

        activityViewModel.getPickedDate().observe(this,localDate -> {
            lastDatePicked = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            binding.matchDateInput.setText(localDate.toString());
        });
        return binding.getRoot();
    }
    private void createSportSpinner() {
        sportSpinner = binding.matchSportInput;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item);
        sportSpinner.setAdapter(adapter);
        viewModel.getSportIdsAndNamesByType(SportType.TEAM).observe(this, sportIdNameModels -> {
            for (SportIdNameModel sportIdNameModel : sportIdNameModels) {
                String sportIdName = sportIdNameModel.getId() + "-" + sportIdNameModel.getSportName();
                if (spinnerData.add(sportIdName)) {
                    adapter.add(sportIdName);
                }
            }
        });
    }
    private void createTeam1Spinner() {
        team1Spinner = binding.matchTeam1;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item);
        team1Spinner.setAdapter(adapter);
        viewModel.getTeamIdsAndNames().observe(this, teamIdNameModels -> {
            for (TeamIdNameModel teamIdNameModel : teamIdNameModels) {
                String teamIdName = teamIdNameModel.getId() + "-" + teamIdNameModel.getTeamName();
                if (team1SpinnerData.add(teamIdName)) {
                    adapter.add(teamIdName);
                }
            }
        });
    }

    private void createTeam2Spinner() {
        team2Spinner = binding.matchTeam2;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item);
        team2Spinner.setAdapter(adapter);
        viewModel.getTeamIdsAndNames().observe(this, teamIdNameModels -> {
            for (TeamIdNameModel teamIdNameModel : teamIdNameModels) {
                String teamIdName = teamIdNameModel.getId() + "-" + teamIdNameModel.getTeamName();
                if (team2SpinnerData.add(teamIdName)) {
                    adapter.add(teamIdName);
                }
            }
        });
    }

}