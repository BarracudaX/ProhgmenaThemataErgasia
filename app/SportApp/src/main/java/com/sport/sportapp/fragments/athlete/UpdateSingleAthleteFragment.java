package com.sport.sportapp.fragments.athlete;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sport.sportapp.databinding.FragmentUpdateSingleAthleteBinding;
import com.sport.sportapp.fragments.BaseFragment;
import com.sport.sportapp.fragments.DatePicker;
import com.sport.sportapp.spinner.AthleteSportAdapter;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

import domain.athlete.AthleteSingle;
import domain.sport.AthleteSport;
import viewmodels.AthleteViewModel;
import viewmodels.SportViewModel;


public class UpdateSingleAthleteFragment extends BaseFragment   {
    private static final String ID_KEY = "ATHLETE_ID";
    private AthleteViewModel athleteViewModel;
    private SportViewModel sportViewModel;
    private LocalDate previousDate;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        athleteViewModel = new ViewModelProvider(this).get(AthleteViewModel.class);
        sportViewModel = new ViewModelProvider(this).get(SportViewModel.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        FragmentUpdateSingleAthleteBinding binding = FragmentUpdateSingleAthleteBinding.inflate(inflater, container, false);
        DatePicker datePicker = new DatePicker(localDate -> binding.athleteDateOfBirthInput.setText(localDate.toString()),LocalDate.now());
        binding.athleteDateOfBirthInput.setOnClickListener(v -> {
            datePicker.show(getChildFragmentManager(),"datePicker");
        });
        sportViewModel.athleteSports().observe(getViewLifecycleOwner(), athleteSports -> {
            binding.athleteSportInput.setAdapter(new AthleteSportAdapter(getContext(),athleteSports));
            athleteViewModel.findAthleteSingleById(getArguments().getLong(ID_KEY)).observe(getViewLifecycleOwner(),athleteSingle -> {
                binding.athleteNameInput.setText(athleteSingle.getFirstName());
                binding.athleteSurnameInput.setText(athleteSingle.getLastName());
                binding.athleteCityInput.setText(athleteSingle.getCity());
                binding.athleteCountryInput.setText(athleteSingle.getCountry());
                binding.athleteDateOfBirthInput.setText(athleteSingle.getDateOfBirth().toString());
                for (int i = 0; i < binding.athleteSportInput.getAdapter().getCount(); i++) {
                    AthleteSport athleteSport = (AthleteSport) binding.athleteSportInput.getItemAtPosition(i);
                    if (athleteSport.getSportId() == athleteSingle.getSportId()) {
                        binding.athleteSportInput.setSelection(i);
                        break;
                    }
                }
            });
        });


        binding.updateAthleteButton.setOnClickListener(v -> {
            AthleteSingle athlete = new AthleteSingle(
                    binding.athleteNameInput.getText().toString(),
                    binding.athleteSurnameInput.getText().toString(),
                    binding.athleteCityInput.getText().toString(),
                    binding.athleteCountryInput.getText().toString(),
                    datePicker.getLocalDatePicked() ,
                    getArguments().getLong(ID_KEY),
                    ((AthleteSport)binding.athleteSportInput.getSelectedItem())
                            .getSportId()
            );
            athleteViewModel.updateAthleteSingle(athlete);
            mainViewModel.navigateBack();
            Toast.makeText(getContext(), "Athlete updated successfully", Toast.LENGTH_LONG).show();
        });

        return binding.getRoot();
    }

    public static Bundle getBundleRequest(long athleteId) {
        Bundle bundle = new Bundle();
        bundle.putLong(ID_KEY,athleteId);
        return bundle;
    }
}