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
import android.widget.Spinner;
import android.widget.Toast;

import com.sport.sportapp.R;
import com.sport.sportapp.fragments.InsertAthleteFragment;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import domain.Athlete;
import domain.Team;
import viewmodels.MainActivityViewModel;


public class UpdateAthleteFragment extends InsertAthleteFragment {
    private static final String ID_KEY = "ATHLETE_ID";

    private MainActivityViewModel viewModel;

    private Spinner spinner;
    private Spinner teamSpinner;
    private final Set<String> spinnerData = new HashSet<>();
    private final Set<String> teamSpinnerData = new HashSet<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
    }

    public int getButtonStringResourceId() {
        return R.string.athlete_update_button;
    }

    public int getTitleStringResourceId() {
        return R.string.update_athlete_title;
    }

    public void buttonClickedEvent(Athlete athlete) {
        Athlete updateRequest = new Athlete(
                getArguments().getLong(ID_KEY),athlete.getAthleteName(),athlete.getSurname(),
                athlete.getCity(),athlete.getCountry(),athlete.getDateOfBirth(),athlete.getSportId(), athlete.getTeamId()
        );
        viewModel.updateAthlete(updateRequest);
        viewModel.navigateBack();
        Toast.makeText(getActivity(), R.string.update_athlete_success_message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getAthleteById(getArguments().getLong(ID_KEY)).observe(this,athlete -> {
            binding.createAthleteButton.setText("Update Athlete");
            binding.athleteNameInput.setText(athlete.getAthleteName());
            binding.athleteSurnameInput.setText(athlete.getSurname());
            binding.athleteCityInput.setText(athlete.getCity());
            binding.athleteCountryInput.setText(athlete.getCountry());
            binding.athleteDateOfBirthInput.setText(athlete.getDateOfBirth().toString());
            int pos = 0;
            for (int i = 0; i < binding.athleteSportInput.getAdapter().getCount(); i++) {
                String sportIdName = (String) binding.athleteSportInput.getItemAtPosition(i);
                if (athlete.getSportId() == Long.parseLong(sportIdName.substring(0, sportIdName.indexOf("-")))) {
                    pos = i;
                    break;
                }
            }
            binding.athleteSportInput.setSelection(pos);
            int teamPos = 0;
            for (int i = 0; i < binding.athleteTeamInput.getAdapter().getCount(); i++) {
                String teamIdName = (String) binding.athleteTeamInput.getItemAtPosition(i);
                if (athlete.getTeamId() == Long.parseLong(teamIdName.substring(0, teamIdName.indexOf("-")))) {
                    teamPos = i;
                    break;
                }
            }
            binding.athleteTeamInput.setSelection(teamPos);
        });
        binding.createAthleteButton.setOnClickListener((v) -> {
            String sportIdAsString = ((String) binding.athleteSportInput.getSelectedItem());
            long sportId = Long.parseLong(sportIdAsString.substring(0, sportIdAsString.indexOf("-")));

            String teamIdAsString = ((String) binding.athleteTeamInput.getSelectedItem());
            long teamId = Long.parseLong(teamIdAsString.substring(0, teamIdAsString.indexOf("-")));

            Athlete athUpdate = new Athlete(getArguments().getLong(ID_KEY),binding.athleteNameInput.getText().toString(),binding.athleteSurnameInput.getText().toString(),
                    binding.athleteCityInput.getText().toString(), binding.athleteCountryInput.getText().toString(), LocalDate.parse("2018-11-01"),
                    sportId,teamId);
            viewModel.updateAthlete(athUpdate);
            viewModel.navigateBack();
            Toast.makeText(getActivity(), R.string.update_athlete_success_message, Toast.LENGTH_LONG).show();
        });
    }

    public static Bundle getBundleRequest(long athleteId) {
        Bundle bundle = new Bundle();
        bundle.putLong(ID_KEY,athleteId);
        return bundle;
    }
}