package com.sport.sportapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.sport.sportapp.R;
import com.sport.sportapp.databinding.FragmentInsertTeamBinding;

import dao.TeamDao;

public class InsertTeamFragment extends Fragment {

    private final TeamDao teamDao;
    private FragmentInsertTeamBinding binding;

    public InsertTeamFragment(TeamDao teamDao) {
        this.teamDao = teamDao;

    }

    public InsertTeamFragment(){
        super(R.layout.fragment_insert_team);
        teamDao = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentInsertTeamBinding.inflate(inflater);
        
        return binding.getRoot();
    }
}
