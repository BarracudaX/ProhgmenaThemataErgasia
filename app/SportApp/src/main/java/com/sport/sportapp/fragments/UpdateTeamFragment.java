package com.sport.sportapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sport.sportapp.databinding.FragmentUpdateTeamBinding;

public class UpdateTeamFragment extends Fragment {

    private FragmentUpdateTeamBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentUpdateTeamBinding.inflate(inflater);

        return binding.getRoot();
    }
}
