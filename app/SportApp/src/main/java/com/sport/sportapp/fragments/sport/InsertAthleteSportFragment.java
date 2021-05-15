package com.sport.sportapp.fragments.sport;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.sport.sportapp.R;
import com.sport.sportapp.databinding.FragmentInsertAthleteSportBinding;
import com.sport.sportapp.fragments.BaseFragment;
import com.sport.sportapp.spinner.AthleteSportTypeAdapter;
import com.sport.sportapp.spinner.GenderAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import domain.Gender;
import domain.sport.AthleteSport;
import domain.sport.AthleteSportType;
import viewmodels.SportViewModel;

public class InsertAthleteSportFragment extends BaseFragment {

    private FragmentInsertAthleteSportBinding binding;
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
        binding = FragmentInsertAthleteSportBinding.inflate(inflater,container,false);
        List<AthleteSportType> athleteSportTypes = new ArrayList<>(Arrays.asList(AthleteSportType.values()));
        List<Gender> genders = new ArrayList<>(Arrays.asList(Gender.values()));

        binding.sportGenderSpinner.setAdapter(new GenderAdapter(getContext(),genders));
        binding.athleteSportTypeSpinner.setAdapter(new AthleteSportTypeAdapter(getContext(),athleteSportTypes));

        binding.createSportButton.setOnClickListener((v) -> {
            sportViewModel.insertAthleteSport(
                    new AthleteSport(
                            binding.sportNameInput.getText().toString(),
                            (Gender) binding.sportGenderSpinner.getSelectedItem(),
                            (AthleteSportType) binding.athleteSportTypeSpinner.getSelectedItem()
                    )
            );
            mainViewModel.navigateBack();
            Toast.makeText(getActivity(), R.string.added_sport_success_message, Toast.LENGTH_LONG).show();
        });

        return binding.getRoot();
    }
}
