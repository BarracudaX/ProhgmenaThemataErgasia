package com.sport.sportapp.fragments;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.sport.sportapp.R;
import com.sport.sportapp.databinding.FragmentInsertTeamBinding;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.TeamDao;
import domain.Athlete;
import domain.Sport;
import domain.SportIdNameModel;
import domain.Team;
import viewmodels.MainActivityViewModel;

public class InsertTeamFragment extends Fragment {

    private LocalDate lastDatePicked;
    private FragmentInsertTeamBinding binding;
    private Spinner spinner;
    private MainActivityViewModel viewModel;
    private final Set<String> spinnerData = new HashSet<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentInsertTeamBinding.inflate(getLayoutInflater());

        viewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);

        createSpinner();
        viewModel.getPickedDate().observe(this,localDate -> {
            lastDatePicked = localDate;
            binding.createTeamFoundationInput.setText(localDate.toString());
        });

        binding.createTeamFoundationInput.setOnClickListener(v -> {
            DialogFragment dialogFragment = new DatePicker();
            dialogFragment.show(getChildFragmentManager(),"datePicker");
        });

        binding.createTeamButton.setOnClickListener(this::onCreateTeamButtonClick);
    }

    private void createSpinner() {
        spinner = binding.createTeamSportInput;
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

    private void onCreateTeamButtonClick(View v) {
        String sportIdStr = (String) spinner.getSelectedItem();
        long sportId = Long.parseLong(sportIdStr.substring(0, sportIdStr.indexOf("-")));
        Team team = new Team(
                binding.createTeamNameInput.getText().toString(),
                binding.createStadiumNameInput.getText().toString(),
                binding.createTeamCityInput.getText().toString(),
                binding.createTeamCountryInput.getText().toString(),
                lastDatePicked,
                sportId
        );
        viewModel.insertTeam(team);
        NavController navController = ((NavHostFragment) getParentFragment()).getNavController();
        navController.navigateUp();
        Toast.makeText(getActivity(),R.string.added_team_success_message,Toast.LENGTH_LONG).show();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return binding.getRoot();
    }
}
