package com.sport.sportapp.fragments.sport;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.sport.sportapp.databinding.FragmentUpdateAthleteSportBinding;
import com.sport.sportapp.fragments.BaseFragment;
import com.sport.sportapp.spinner.AthleteSportTypeAdapter;
import com.sport.sportapp.spinner.GenderAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import domain.Gender;
import domain.sport.AthleteSport;
import domain.sport.AthleteSportType;
import viewmodels.SportViewModel;

public class UpdateAthleteSportFragment extends BaseFragment {
    private static String ID_KEY = "SPORT_ID";
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
        FragmentUpdateAthleteSportBinding binding = FragmentUpdateAthleteSportBinding.inflate(inflater, container, false);
        binding.genderSpinner.setAdapter(new GenderAdapter(getContext(), Arrays.asList(Gender.values())));
        binding.athleteSportTypeSpinner.setAdapter(new AthleteSportTypeAdapter(getContext(), Arrays.asList(AthleteSportType.values())));

        sportViewModel.findSportAthleteById(getArguments().getLong(ID_KEY))
                .observe(getViewLifecycleOwner(), sport -> {
                    binding.updateSportNameInput.setText(sport.getSportName());
                    for (int i = 0; i < binding.genderSpinner.getAdapter().getCount(); i++) {
                        Gender gender = (Gender) binding.genderSpinner.getItemAtPosition(i);
                        if (gender.equals(sport.getGender())) {
                            binding.genderSpinner.setSelection(i);
                            break;
                        }
                    }

                    for (int i = 0; i < binding.athleteSportTypeSpinner.getAdapter().getCount(); i++) {
                        AthleteSportType athleteSportType = (AthleteSportType) binding.athleteSportTypeSpinner.getItemAtPosition(i);
                        if (athleteSportType.equals(sport.getAthleteSportType())) {
                            binding.athleteSportTypeSpinner.setSelection(i);
                            break;
                        }
                    }
                });
        binding.updateSportButton.setOnClickListener(v -> {
            AthleteSport athleteSport = new AthleteSport(
                    getArguments().getLong(ID_KEY),
                    binding.updateSportNameInput.getText().toString(),
                    (Gender)binding.genderSpinner.getSelectedItem(),
                    (AthleteSportType)binding.athleteSportTypeSpinner.getSelectedItem()
            );
            sportViewModel.updateAthleteSport(athleteSport);
            mainViewModel.navigateBack();
            Toast.makeText(getContext(), "Athlete sport updates successfully", Toast.LENGTH_LONG).show();
        });

        return binding.getRoot();
    }

    public static Bundle getBundleRequest(long sportId) {
        Bundle bundle = new Bundle();
        bundle.putLong(ID_KEY, sportId);

        return bundle;
    }
}
