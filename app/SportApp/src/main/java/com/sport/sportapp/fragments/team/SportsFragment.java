package com.sport.sportapp.fragments.team;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sport.sportapp.databinding.FragmentSportTeamBinding;
import com.sport.sportapp.databinding.FragmentTeamFormBinding;
import com.sport.sportapp.databinding.FragmentTeamMainBinding;
import com.sport.sportapp.fragments.BaseFragment;
import com.sport.sportapp.views.SportsAdapter;
import com.sport.sportapp.views.SportsButtonsAdapter;

public class SportsFragment extends BaseFragment {

    private FragmentSportTeamBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSportTeamBinding.inflate(getLayoutInflater(),container,false);

        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.sports.setLayoutManager(new LinearLayoutManager(getContext()));
        }else{
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
            gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
            binding.sports.setLayoutManager(gridLayoutManager);
        }
        activityViewModel.getSports().observe(this,sports -> {
            binding.sports.setAdapter(new SportsButtonsAdapter(activityViewModel,sports));
        });
        return binding.getRoot();
    }
}
