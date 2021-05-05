package com.sport.sportapp.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.sport.sportapp.R;
import com.sport.sportapp.databinding.FragmentInsertAthleteBinding;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import dao.AthleteDao;
import domain.Athlete;
import domain.SportIdNameModel;
import domain.SportType;
import domain.Team;
import domain.TeamIdNameModel;
import viewmodels.MainActivityViewModel;

import static domain.SportType.SINGLE;
import static domain.SportType.TEAM;


public class InsertAthleteFragment extends BaseFragment {
    private FragmentInsertAthleteBinding binding;
    protected MainActivityViewModel viewModel;
    private Spinner spinner;
    private Spinner teamSpinner;
    private final Set<String> spinnerData = new HashSet<>();
    private final Set<String> teamSpinnerData = new HashSet<>();
    private SportType sportType = TEAM;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentInsertAthleteBinding.inflate(inflater, container, false);
        binding = FragmentInsertAthleteBinding.inflate(inflater);

        createSpinner();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String sportIdAsString = ((String) spinner.getSelectedItem());
                long sportId = Long.parseLong(sportIdAsString.substring(0, sportIdAsString.indexOf("-")));
                createTeamSpinner(sportId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                createTeamSpinner(0);
            }
        });

        //sportType = viewModel.getSportType(sportId);
        //if(sportType.equals(TEAM))
        binding.createAthleteButton.setOnClickListener((v) -> {
            String sportIdAsString = ((String) spinner.getSelectedItem());
            long sportId = Long.parseLong(sportIdAsString.substring(0, sportIdAsString.indexOf("-")));
                viewModel.insertAthlete(new Athlete(
                    binding.athleteNameInput.getText().toString(),
                    binding.athleteSurnameInput.getText().toString(),
                    binding.athleteCityInput.getText().toString(),
                    binding.athleteCountryInput.getText().toString(),
                    LocalDate.parse("2018-11-01"),
                    sportId
                ));

            viewModel.navigateBack();
            Toast.makeText(getActivity(), R.string.added_athlete_success_message, Toast.LENGTH_LONG).show();
        });
        return binding.getRoot();
    }

    private void createSpinner() {
        spinner = binding.athleteSportInput;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        viewModel.getSportIdsAndNames().observe(this, sportIdNameModels -> {
            for (SportIdNameModel sportIdNameModel : sportIdNameModels) {
                String sportIdName = sportIdNameModel.getId() + "-" + sportIdNameModel.getSportName();
                if (spinnerData.add(sportIdName)) {
                    adapter.add(sportIdName);
                }
            }
        });
    }
    private void createTeamSpinner(long sportId) {
        teamSpinner = binding.athleteTeamInput;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item);
        teamSpinner.setAdapter(adapter);
        viewModel.getTeamIdsAndNames(sportId).observe(this, teamIdNameModels -> {
            for (TeamIdNameModel teamIdNameModel : teamIdNameModels) {
                String teamIdName = teamIdNameModel.getId() + "-" + teamIdNameModel.getTeamName();
                if (teamSpinnerData.add(teamIdName)) {
                    adapter.add(teamIdName);
                }
            }
        });
    }
}
