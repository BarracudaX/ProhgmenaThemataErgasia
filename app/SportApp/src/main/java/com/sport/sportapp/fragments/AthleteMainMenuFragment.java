package com.sport.sportapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sport.sportapp.R;
import com.sport.sportapp.databinding.FragmentAthleteMainMenuBinding;

public class AthleteMainMenuFragment extends Fragment {
    private FragmentAthleteMainMenuBinding binding;


    public AthleteMainMenuFragment() {
        super(R.layout.fragment_athlete_main_menu);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_athlete_main_menu, container, false);
    }
}