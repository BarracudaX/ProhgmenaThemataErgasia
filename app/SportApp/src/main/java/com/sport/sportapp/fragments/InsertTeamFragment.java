package com.sport.sportapp.fragments;

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
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sport.sportapp.R;
import com.sport.sportapp.databinding.FragmentInsertTeamBinding;

import java.util.List;

import dao.TeamDao;
import domain.Athlete;
import domain.Sport;
import domain.SportIdNameModel;
import viewmodels.MainActivityViewModel;

public class InsertTeamFragment extends Fragment {

    private FragmentInsertTeamBinding binding;
    private Spinner spinner;
    private MainActivityViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentInsertTeamBinding.inflate(getLayoutInflater());
        spinner = binding.createTeamSportInput;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        viewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
        viewModel.getSportIdsAndNames().observe(this, sportIdNameModels -> {
            for (SportIdNameModel sportIdNameModel : sportIdNameModels) {
                adapter.add(sportIdNameModel.getId()+"-"+sportIdNameModel.getSportName());
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return binding.getRoot();
    }
}
