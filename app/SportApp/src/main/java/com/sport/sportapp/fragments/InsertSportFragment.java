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
import com.sport.sportapp.databinding.FragmentInsertSportBinding;


import converters.GenderConverter;
import dao.SportDao;
import domain.Gender;
import domain.Sport;
import domain.SportType;
import viewmodels.MainActivityViewModel;


public class InsertSportFragment extends BaseFragment {

    private FragmentInsertSportBinding binding;
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
        binding = FragmentInsertSportBinding.inflate(inflater, container, false);
        binding = FragmentInsertSportBinding.inflate(inflater);
        binding.createSportButton.setOnClickListener((v) -> {
            viewModel.insertSport(new Sport(
                                binding.sportNameInput.getText().toString(),
                    Gender.valueOf(binding.genderTypeInput.getText().toString()),
                    SportType.valueOf(binding.sportTypeInput.getText().toString())

                        ));
            viewModel.navigateBack();
            Toast.makeText(getActivity(), R.string.added_sport_success_message, Toast.LENGTH_LONG).show();
        });
        return binding.getRoot();
    }
}