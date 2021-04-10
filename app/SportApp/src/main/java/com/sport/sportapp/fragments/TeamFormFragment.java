package com.sport.sportapp.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sport.sportapp.R;
import com.sport.sportapp.databinding.FragmentTeamFormBinding;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import domain.SportIdNameModel;
import domain.Team;
import viewmodels.MainActivityViewModel;

public abstract class TeamFormFragment extends BaseFragment {

    protected FragmentTeamFormBinding binding;
    protected MainActivityViewModel viewModel;
    private LocalDate lastDatePicked;
    private Spinner spinner;
    private final Set<String> spinnerData = new HashSet<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTeamFormBinding.inflate(getLayoutInflater(),container,false);

        binding.teamFoundationInput.setOnClickListener(v -> {
            DialogFragment dialogFragment = new DatePicker();
            dialogFragment.show(getChildFragmentManager(),"datePicker");
        });

        createSpinner();

        binding.title.setText(getTitleStringResourceId());
        binding.teamButton.setText(getButtonStringResourceId());
        binding.teamButton.setOnClickListener((v)->{
            String sportIdAsString = ((String) spinner.getSelectedItem());
            long sportId = Long.parseLong(sportIdAsString.substring(0, sportIdAsString.indexOf("-")));
            buttonClickedEvent(new Team(
                    binding.teamNameInput.getText().toString(),
                    binding.stadiumNameInput.getText().toString(),
                    binding.teamCityInput.getText().toString(),
                    binding.teamCountryInput.getText().toString(),
                    lastDatePicked,
                    sportId
            ));
        });
        viewModel.getPickedDate().observe(this,localDate -> {
            lastDatePicked = localDate;
            binding.teamFoundationInput.setText(localDate.toString());
        });
        return binding.getRoot();
    }

    private void createSpinner() {
        spinner = binding.teamSportInput;
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

    public abstract int getButtonStringResourceId();

    public abstract int getTitleStringResourceId();

    public abstract void buttonClickedEvent(Team team);

}
