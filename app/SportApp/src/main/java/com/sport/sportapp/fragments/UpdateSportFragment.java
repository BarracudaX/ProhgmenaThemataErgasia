package com.sport.sportapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sport.sportapp.R;


public class UpdateSportFragment extends Fragment {

    private static final String ID_KEY = "SPORT_ID";
    public UpdateSportFragment() {
        // Required empty public constructor
    }

    public static UpdateSportFragment newInstance(String param1, String param2) {
        UpdateSportFragment fragment = new UpdateSportFragment();

        return fragment;
    }

    public static Bundle getBundleRequest(long sportId) {
        Bundle bundle = new Bundle();
        bundle.putLong(ID_KEY,sportId);
        return bundle;
    }
}