package com.sport.sportapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sport.sportapp.R;

import java.util.ArrayList;
import java.util.List;

import domain.sport.TeamSport;
import viewmodels.MainViewModel;

public class SportsButtonsAdapter extends RecyclerView.Adapter<SportButtonViewHolder> {

    private final MainViewModel viewModel;
    private final List<TeamSport> sports = new ArrayList<>();

    public SportsButtonsAdapter(MainViewModel viewModel, List<TeamSport> sports) {
        this.viewModel = viewModel;
        this.sports.addAll(sports);
    }

    @NonNull
    @Override
    public SportButtonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SportButtonViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.sport_button_layout,parent,false),
                viewModel);
    }

    @Override
    public void onBindViewHolder(@NonNull SportButtonViewHolder holder, int position) {
        holder.bindSport(sports.get(position));
    }

    @Override
    public int getItemCount() {
        return sports.size();
    }
}
