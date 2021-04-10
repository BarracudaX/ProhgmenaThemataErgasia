package com.sport.sportapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sport.sportapp.R;
import com.sport.sportapp.databinding.FragmentInsertAthleteBinding;

import dao.AthleteDao;


public class InsertAthleteFragment extends Fragment {
    private final AthleteDao athleteDao;
    private FragmentInsertAthleteBinding binding;
    public InsertAthleteFragment(AthleteDao athleteDao){
        this.athleteDao = athleteDao;
    }
    public InsertAthleteFragment() {
        super(R.layout.fragment_insert_athlete);
        athleteDao = null;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentInsertAthleteBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}