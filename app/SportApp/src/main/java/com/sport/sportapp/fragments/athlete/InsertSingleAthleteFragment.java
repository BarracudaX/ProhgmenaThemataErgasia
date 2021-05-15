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

import com.sport.sportapp.databinding.FragmentInsertSingleAthleteBinding;
import com.sport.sportapp.fragments.BaseFragment;
import com.sport.sportapp.fragments.DatePicker;
import com.sport.sportapp.spinner.AthleteSportAdapter;

import java.time.LocalDate;

import domain.athlete.AthleteSingle;
import domain.sport.AthleteSport;
import viewmodels.AthleteViewModel;
import viewmodels.SportViewModel;


public class InsertSingleAthleteFragment extends BaseFragment {
    protected FragmentInsertSingleAthleteBinding binding;
    private SportViewModel sportViewModel;
    private AthleteViewModel athleteViewModel;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sportViewModel = new ViewModelProvider(getActivity()).get(SportViewModel.class);
        athleteViewModel = new ViewModelProvider(getActivity()).get(AthleteViewModel.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentInsertSingleAthleteBinding.inflate(inflater, container, false);
        sportViewModel.athleteSports().observe(getViewLifecycleOwner(),sports -> {
            binding.athleteSportInput.setAdapter(new AthleteSportAdapter(getContext(),sports));
        });

        DatePicker datePicker = new DatePicker(localDate -> binding.athleteDateOfBirthInput.setText(localDate.toString()),LocalDate.now());

        binding.athleteDateOfBirthInput.setOnClickListener(v -> {
            datePicker.show(getChildFragmentManager(),"datePicker");
        });

        binding.createAthleteButton.setOnClickListener(v -> {
            AthleteSingle athlete = new AthleteSingle(
                    binding.athleteNameInput.getText().toString(),binding.athleteSurnameInput.getText().toString(),
                    binding.athleteCityInput.getText().toString(),binding.athleteCountryInput.getText().toString(),
                    datePicker.getLocalDatePicked(),
                    ((AthleteSport) binding.athleteSportInput.getSelectedItem()).getSportId()
            );
            athleteViewModel.insertAthleteSingle(athlete);
            mainViewModel.navigateBack();
            Toast.makeText(getContext(), "Single athlete added successfully", Toast.LENGTH_LONG).show();
        });

        return binding.getRoot();
    }


}
