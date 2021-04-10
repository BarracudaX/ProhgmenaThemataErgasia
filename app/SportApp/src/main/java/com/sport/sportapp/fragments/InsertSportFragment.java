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
import com.sport.sportapp.databinding.FragmentInsertSportBinding;

import dao.SportDao;


public class InsertSportFragment extends Fragment {
    private final SportDao sportDao;
    private FragmentInsertSportBinding binding;
    public InsertSportFragment(SportDao sportDao) {
        this.sportDao=sportDao;
    }
    public InsertSportFragment(){
        super(R.layout.fragment_insert_sport);
        sportDao=null;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=FragmentInsertSportBinding.inflate(inflater);
        return binding.getRoot();
        //inflater.inflate(R.layout.fragment_insert_sport, container, false);
    }
}