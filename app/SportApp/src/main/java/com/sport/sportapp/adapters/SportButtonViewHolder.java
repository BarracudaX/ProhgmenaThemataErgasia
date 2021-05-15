package com.sport.sportapp.adapters;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sport.sportapp.R;
import com.sport.sportapp.databinding.SportButtonLayoutBinding;
import com.sport.sportapp.fragments.team.SportTeamsFragment;

import domain.sport.TeamSport;
import viewmodels.MainViewModel;

public class SportButtonViewHolder extends RecyclerView.ViewHolder {

    private final MainViewModel viewModel;
    private SportButtonLayoutBinding binding;

    public SportButtonViewHolder(@NonNull View itemView, MainViewModel viewModel) {
        super(itemView);
        this.viewModel = viewModel;
        binding = SportButtonLayoutBinding.bind(itemView);
    }

    public void bindSport(TeamSport sport) {
        binding.sportNameButton.setText(sport.getSportName());
        binding.sportNameButton.setOnClickListener(v -> {
            viewModel.navigateTo(R.id.sportTeamsDestination, SportTeamsFragment.getBundleRequest(sport.getSportId()));
        });
    }
}
