package com.sport.sportapp.views;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sport.sportapp.MainActivity;
import com.sport.sportapp.R;
import com.sport.sportapp.databinding.SportButtonLayoutBinding;
import com.sport.sportapp.fragments.team.SportTeamsFragment;

import domain.Sport;
import viewmodels.MainActivityViewModel;

public class SportButtonViewHolder extends RecyclerView.ViewHolder {

    private final MainActivityViewModel viewModel;
    private SportButtonLayoutBinding binding;

    public SportButtonViewHolder(@NonNull View itemView, MainActivityViewModel viewModel) {
        super(itemView);
        this.viewModel = viewModel;
        binding = SportButtonLayoutBinding.bind(itemView);
    }

    public void bindSport(Sport sport) {
        binding.sportNameButton.setText(sport.getSportName());
        binding.sportNameButton.setOnClickListener(v -> {
            viewModel.navigateTo(R.id.sportTeamsDestination, SportTeamsFragment.getBundleRequest(sport.getSportId()));
        });
    }
}
