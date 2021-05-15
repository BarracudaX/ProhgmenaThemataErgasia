package com.sport.sportapp.fragments.sport;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.sport.sportapp.databinding.FragmentUpdateTeamSportBinding;
import com.sport.sportapp.fragments.BaseFragment;
import com.sport.sportapp.spinner.GenderAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import domain.Gender;
import domain.sport.TeamSport;
import viewmodels.SportViewModel;


public class UpdateTeamSportFragment extends BaseFragment {

    private static final String ID_KEY = "SPORT_ID";
    private SportViewModel sportViewModel;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sportViewModel = new ViewModelProvider(getActivity()).get(SportViewModel.class);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        FragmentUpdateTeamSportBinding binding = FragmentUpdateTeamSportBinding.inflate(inflater,container,false);
        long sportId = getArguments().getLong(ID_KEY);
        binding.genderSpinner.setAdapter(new GenderAdapter(getContext(), Arrays.asList(Gender.values().clone())));
        sportViewModel.findSportTeamById(sportId).observe(getViewLifecycleOwner(),teamSport -> {
            for (int i = 0; i < binding.genderSpinner.getAdapter().getCount(); i++) {
                Gender gender = (Gender) binding.genderSpinner.getItemAtPosition(i);
                if (gender.equals(teamSport.getGender())) {
                    binding.genderSpinner.setSelection(i);
                    break;
                }
            }
            binding.updateSportNameInput.setText(teamSport.getSportName());
        });
        binding.updateSportButton.setOnClickListener(v -> {
            TeamSport teamSport = new TeamSport(getArguments().getLong(ID_KEY),
                    binding.updateSportNameInput.getText().toString(),
                    (Gender) binding.genderSpinner.getSelectedItem());
            sportViewModel.updateSportTeam(teamSport);
            mainViewModel.navigateBack();
            Toast.makeText(getContext(), "Sport updated successfully", Toast.LENGTH_LONG).show();
        });

        return binding.getRoot();
    }

    public static Bundle getBundleRequest(long sportId) {
        Bundle bundle = new Bundle();
        bundle.putLong(ID_KEY,sportId);
        return bundle;
    }
}