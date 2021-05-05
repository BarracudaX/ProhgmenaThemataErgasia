package com.sport.sportapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.sport.sportapp.R;
import com.sport.sportapp.databinding.FragmentAthleteBySportBinding;
import com.sport.sportapp.views.AthletesAdapter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import domain.Athlete;
import domain.SportIdNameModel;
import viewmodels.MainActivityViewModel;

public class AthleteBySportFragment extends BaseFragment {

    private FragmentAthleteBySportBinding binding;
    private MainActivityViewModel viewModel;
    private Spinner spinner;
    private final Set<String> spinnerData = new HashSet<>();
    long sportId=1;
    List<Athlete> athletesList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAthleteBySportBinding.inflate(inflater);
        athletesList=viewModel.getAthletesBySportId(sportId);
        createSpinner();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String sportIdAsString = ((String) spinner.getSelectedItem());
                sportId = Long.parseLong(sportIdAsString.substring(0, sportIdAsString.indexOf("-")));
                athletesList=viewModel.getAthletesBySportId(sportId);
                binding.athletesBySport.setAdapter(new AthletesAdapter(athletesList,viewModel));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });


        configureBottomNavigation(binding.bottomNavigationView);
        return  binding.getRoot();

    }

    @Override
    public void onStart() {
        super.onStart();
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
}