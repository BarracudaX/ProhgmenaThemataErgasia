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
import android.widget.Toast;

import com.sport.sportapp.R;
import com.sport.sportapp.databinding.FragmentInsertAthleteBinding;

import java.time.LocalDate;

import dao.AthleteDao;
import domain.Athlete;
import domain.Team;
import viewmodels.MainActivityViewModel;


public class InsertAthleteFragment extends BaseFragment {
    private FragmentInsertAthleteBinding binding;
    protected MainActivityViewModel viewModel;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentInsertAthleteBinding.inflate(inflater, container, false);
        binding = FragmentInsertAthleteBinding.inflate(inflater);
        binding.createAthleteButton.setOnClickListener((v) -> {
            viewModel.insertAthlete(new Athlete(
                    binding.athleteNameInput.getText().toString(),
                    binding.athleteSurnameInput.getText().toString(),
                    binding.athleteCityInput.getText().toString(),
                    binding.athleteCountryInput.getText().toString(),
                    LocalDate.parse("2018-11-01")
            ));
            viewModel.navigateBack();
            Toast.makeText(getActivity(), R.string.added_athelet_success_message, Toast.LENGTH_LONG).show();
        });
        return binding.getRoot();
    }
}
