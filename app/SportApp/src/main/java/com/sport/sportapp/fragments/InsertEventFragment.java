package com.sport.sportapp.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;

import com.sport.sportapp.R;
import com.sport.sportapp.databinding.FragmentInsertAthleteBinding;
import com.sport.sportapp.databinding.FragmentInsertEventBinding;

import java.time.LocalDate;

import domain.Athlete;
import viewmodels.MainActivityViewModel;


public class InsertEventFragment extends BaseFragment {
    private FragmentInsertEventBinding binding;
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
        binding = FragmentInsertEventBinding.inflate(inflater, container, false);
        binding = FragmentInsertEventBinding.inflate(inflater);
        binding.createEventButton.setOnClickListener((v) -> {
        });
        return binding.getRoot();
    }
}