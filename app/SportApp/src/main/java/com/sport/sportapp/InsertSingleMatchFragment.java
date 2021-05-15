package com.sport.sportapp;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.sport.sportapp.databinding.FragmentInsertAthleteBinding;
import com.sport.sportapp.databinding.FragmentInsertSingleMatchBinding;
import com.sport.sportapp.fragments.BaseFragment;
import com.sport.sportapp.fragments.DatePicker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import dao.SportDao;
import domain.AthleteIdNameModel;
import domain.AthleteScore;
import domain.SingleMatch;
import domain.SportIdNameModel;
import domain.SportType;
import viewmodels.MainActivityViewModel;

public class InsertSingleMatchFragment extends BaseFragment {
    protected FragmentInsertSingleMatchBinding binding;
    protected MainActivityViewModel viewModel;
    private Spinner sportSpinner;
    private Spinner athlete1Spinner;
    private Spinner athlete2Spinner;
    private final Set<String> spinnerData = new HashSet<>();
    private final Set<String> athlete1SpinnerData = new HashSet<>();
    private final Set<String> athlete2SpinnerData = new HashSet<>();
    private Date lastDatePicked;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentInsertSingleMatchBinding.inflate(inflater, container, false);

        binding.matchDateInput.setOnClickListener(v -> {
            DialogFragment dialogFragment = new DatePicker();
            dialogFragment.show(getChildFragmentManager(),"datePicker");
        });

        createSportSpinner();
        createAthlete1Spinner();
        createAthlete2Spinner();

        binding.createSingleEventButton.setOnClickListener((v) -> {
            String sportIdAsString = ((String) sportSpinner.getSelectedItem());
            long sportId = Long.parseLong(sportIdAsString.substring(0, sportIdAsString.indexOf("-")));
            String athleteScore1 = ((String)athlete1Spinner.getSelectedItem());
            long athlete1Id = Long.parseLong(athleteScore1.substring(0, sportIdAsString.indexOf("-")));
            AthleteScore athlete1 = new AthleteScore(viewModel.findAthleteById(athlete1Id), new Double(String.valueOf(binding.athlete1ScoreInput.getText().toString())));
            String athleteScore2 = ((String)athlete2Spinner.getSelectedItem());
            long athlete2Id = Long.parseLong(athleteScore2.substring(0, sportIdAsString.indexOf("-")));
            AthleteScore athlete2 = new AthleteScore(viewModel.findAthleteById(athlete2Id), new Double(String.valueOf(binding.athlete2ScoreInput.getText().toString())));
            viewModel.insertSingleMatch(new SingleMatch(lastDatePicked,binding.matchCityInput.getText().toString(),binding.matchCountryInput.getText().toString(),viewModel.getSportById(sportId),athlete1,athlete2));
        });
        activityViewModel.getPickedDate().observe(getViewLifecycleOwner(),localDate -> {
                    lastDatePicked = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    binding.matchDateInput.setText(localDate.toString());
        });
            return binding.getRoot();
    }

    private void createSportSpinner() {
        sportSpinner = binding.matchSportInput;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item);
        sportSpinner.setAdapter(adapter);
        viewModel.getSportIdsAndNamesByType(SportType.SINGLE).observe(this, sportIdNameModels -> {
            for (SportIdNameModel sportIdNameModel : sportIdNameModels) {
                String sportIdName = sportIdNameModel.getId() + "-" + sportIdNameModel.getSportName();
                if (spinnerData.add(sportIdName)) {
                    adapter.add(sportIdName);
                }
            }
        });
    }
    private void createAthlete1Spinner() {
        athlete1Spinner = binding.matchAthlete1;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item);
        athlete1Spinner.setAdapter(adapter);
        viewModel.getAthleteIdAndName().observe(this, athleteIdNameModels -> {
            for (AthleteIdNameModel athleteIdNameModel : athleteIdNameModels) {
                String athleteIdName = athleteIdNameModel.getId() + "-" + athleteIdNameModel.getAthleteFullName();
                if (athlete1SpinnerData.add(athleteIdName)) {
                    adapter.add(athleteIdName);
                }
            }
        });
    }
    private void createAthlete2Spinner() {
        athlete2Spinner = binding.matchAthlete2;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item);
        athlete2Spinner.setAdapter(adapter);
        viewModel.getAthleteIdAndName().observe(this, athleteIdNameModels -> {
            for (AthleteIdNameModel athleteIdNameModel : athleteIdNameModels) {
                String athleteIdName = athleteIdNameModel.getId() + "-" + athleteIdNameModel.getAthleteName()+athleteIdNameModel.getAthleteSurname();
                if (athlete2SpinnerData.add(athleteIdName)) {
                    adapter.add(athleteIdName);
                }
            }
        });
    }
}