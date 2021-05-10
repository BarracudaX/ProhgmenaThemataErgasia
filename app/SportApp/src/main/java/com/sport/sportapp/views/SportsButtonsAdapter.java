package com.sport.sportapp.views;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sport.sportapp.R;

import java.util.ArrayList;
import java.util.List;

import domain.Sport;
import viewmodels.MainActivityViewModel;

public class SportsButtonsAdapter extends RecyclerView.Adapter<SportButtonViewHolder> {

    private final MainActivityViewModel viewModel;
    private final List<Sport> sports = new ArrayList<>();

    public SportsButtonsAdapter(MainActivityViewModel viewModel, List<Sport> sports) {
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
