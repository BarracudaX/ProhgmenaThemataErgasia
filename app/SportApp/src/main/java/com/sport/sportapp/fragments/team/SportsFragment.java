package com.sport.sportapp.fragments.team;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sport.sportapp.databinding.FragmentSportTeamBinding;
import com.sport.sportapp.fragments.BaseFragment;
import com.sport.sportapp.adapters.SportsButtonsAdapter;

import viewmodels.SportViewModel;

public class SportsFragment extends BaseFragment {

    private FragmentSportTeamBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSportTeamBinding.inflate(getLayoutInflater(),container,false);
        SportViewModel sportViewModel = new ViewModelProvider(getActivity()).get(SportViewModel.class);

        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.sports.setLayoutManager(new LinearLayoutManager(getContext()));
        }else{
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
            gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
            binding.sports.setLayoutManager(gridLayoutManager);
        }
        sportViewModel.teamSports().observe(getViewLifecycleOwner(), sports -> {
            binding.sports.setAdapter(new SportsButtonsAdapter(mainViewModel,sports));
        });
        return binding.getRoot();
    }
}
