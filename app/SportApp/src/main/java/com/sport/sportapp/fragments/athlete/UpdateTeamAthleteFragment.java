package com.sport.sportapp.fragments.athlete;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.sport.sportapp.databinding.FragmentUpdateTeamAthleteBinding;
import com.sport.sportapp.fragments.BaseFragment;
import com.sport.sportapp.fragments.DatePicker;
import com.sport.sportapp.spinner.TeamAdapter;

import org.jetbrains.annotations.NotNull;

import domain.athlete.AthleteTeam;
import domain.team.Team;
import viewmodels.AthleteViewModel;
import viewmodels.TeamViewModel;

public class UpdateTeamAthleteFragment extends BaseFragment {

    private static final String ID_KEY = "ATHLETE_ID";
    private TeamViewModel teamViewModel;
    private AthleteViewModel athleteViewModel;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        teamViewModel = new ViewModelProvider(getActivity()).get(TeamViewModel.class);
        athleteViewModel = new ViewModelProvider(getActivity()).get(AthleteViewModel.class);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        FragmentUpdateTeamAthleteBinding binding = FragmentUpdateTeamAthleteBinding.inflate(inflater, container, false);

        teamViewModel.teams().observe(getViewLifecycleOwner(),teams -> {
            binding.athleteTeamInput.setAdapter(new TeamAdapter(getContext(),teams));
            athleteViewModel.findAthleteTeamById(getArguments().getLong(ID_KEY))
                    .observe(getViewLifecycleOwner(), athleteTeam -> {
                        DatePicker datePicker = new DatePicker(localDate -> binding.athleteDateOfBirthInput.setText(localDate.toString()), athleteTeam.getDateOfBirth());
                        binding.athleteDateOfBirthInput.setOnClickListener(v -> {
                            datePicker.show(getChildFragmentManager(),"datePicker");
                        });
                        binding.athleteNameInput.setText(athleteTeam.getFirstName());
                        binding.athleteSurnameInput.setText(athleteTeam.getLastName());
                        binding.athleteDateOfBirthInput.setText(athleteTeam.getDateOfBirth().toString());
                        binding.athleteCityInput.setText(athleteTeam.getCity());
                        binding.athleteCountryInput.setText(athleteTeam.getCity());
                        binding.updateTeamAthleteButton.setOnClickListener(v -> {
                            AthleteTeam athlete = new AthleteTeam(
                                    binding.athleteNameInput.getText().toString(),
                                    binding.athleteSurnameInput.getText().toString(),
                                    binding.athleteCityInput.getText().toString(),
                                    binding.athleteCountryInput.getText().toString(),
                                    datePicker.getLocalDatePicked(),
                                    getArguments().getLong(ID_KEY),
                                    ((Team)binding.athleteTeamInput.getSelectedItem()).getTeamId()
                            );
                             athleteViewModel.updateAthleteTeam(athlete);
                            mainViewModel.navigateBack();
                            Toast.makeText(getContext(), "Athlete updated successfully", Toast.LENGTH_LONG).show();
                        });
                        for (int i = 0; i < binding.athleteTeamInput.getAdapter().getCount(); i++) {
                            Team team = (Team) binding.athleteTeamInput.getItemAtPosition(i);
                            if (team.getTeamId() == athleteTeam.getTeamId()) {
                                binding.athleteTeamInput.setSelection(i);
                                break;
                            }
                        }

                    });
        });


        return binding.getRoot();
    }

    public static Bundle getBundleRequest(long athleteId) {
        Bundle bundle = new Bundle();
        bundle.putLong(ID_KEY, athleteId);
        return bundle;
    }

}
