package com.sport.sportapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sport.sportapp.R;


public class UpdateAthleteFragment extends Fragment {
    private static final String ID_KEY = "ATHLETE_ID";

    public UpdateAthleteFragment() {
        // Required empty public constructor
    }

    public static UpdateAthleteFragment newInstance(String param1, String param2) {
        UpdateAthleteFragment fragment = new UpdateAthleteFragment();

        return fragment;
    }

    public static Bundle getBundleRequest(long athleteId) {
        Bundle bundle = new Bundle();
        bundle.putLong(ID_KEY,athleteId);
        return bundle;
    }
}