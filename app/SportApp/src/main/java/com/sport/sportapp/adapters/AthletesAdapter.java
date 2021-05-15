package com.sport.sportapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sport.sportapp.R;
import com.sport.sportapp.databinding.AthleteLayoutBinding;
import com.sport.sportapp.fragments.athlete.UpdateSingleAthleteFragment;
import com.sport.sportapp.fragments.athlete.UpdateTeamAthleteFragment;

import java.util.List;

import domain.athlete.Athlete;
import domain.athlete.AthleteSingle;
import domain.athlete.AthleteTeam;
import viewmodels.AthleteViewModel;
import viewmodels.MainViewModel;

public class AthletesAdapter extends RecyclerView.Adapter<AthletesAdapter.AthleteViewHolder> {

    private final List<Athlete> athletes;
    private final MainViewModel viewModel;
    private final AthleteViewModel athleteViewModel;

    public AthletesAdapter(List<Athlete> athletes, MainViewModel viewModel, AthleteViewModel athleteViewModel) {
        this.athletes = athletes;
        this.viewModel = viewModel;
        this.athleteViewModel = athleteViewModel;
    }

    @NonNull
    @Override
    public AthleteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AthleteViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.athlete_layout,parent,false),
                viewModel,
                athleteViewModel);
    }

    @Override
    public void onBindViewHolder(@NonNull AthleteViewHolder holder, int position) {
        holder.bindData(athletes.get(position));
    }

    @Override
    public int getItemCount() {
        return athletes.size();
    }

    public static class AthleteViewHolder extends RecyclerView.ViewHolder{

        private final AthleteLayoutBinding binding;
        private final MainViewModel viewModel;
        private final AthleteViewModel athleteViewModel;
        private Athlete athlete;

        public AthleteViewHolder(@NonNull View itemView, MainViewModel viewModel, AthleteViewModel athleteViewModel) {
            super(itemView);
            this.viewModel = viewModel;
            binding = AthleteLayoutBinding.bind(itemView);
            this.athleteViewModel = athleteViewModel;
            binding.athleteUpdateButton.setOnClickListener(this::onUpdateButtonClicked);
            binding.athleteDeleteButton.setOnClickListener(this::onDeleteButtonClicked);
        }

        private void bindData(Athlete athlete) {
            binding.athleteIdInput.setText(String.valueOf(athlete.getAthleteId()));
            binding.athleteNameInput.setText(athlete.getFirstName()+" "+athlete.getLastName());
            if (athlete instanceof AthleteSingle) {
                binding.athleteTypeValue.setText("Single Athlete");
            } else if (athlete instanceof AthleteTeam) {
                binding.athleteTypeValue.setText("Team Athlete");
            }else {
                binding.athleteTypeValue.setText("Unknown Athlete");
            }
            this.athlete = athlete;
        }

        private void onUpdateButtonClicked(View view) {
            if (athlete instanceof AthleteSingle) {
                viewModel.navigateTo(R.id.updateSingleAthleteDestination, UpdateSingleAthleteFragment.getBundleRequest(athlete.getAthleteId()));
            } else if (athlete instanceof AthleteTeam) {
                viewModel.navigateTo(R.id.updateTeamAthleteDestination, UpdateTeamAthleteFragment.getBundleRequest(athlete.getAthleteId()));
            }else {
                throw new IllegalArgumentException("Unknown athlete type");
            }
        }

        private void onDeleteButtonClicked(View view) {
            if (athlete instanceof AthleteSingle) {
                athleteViewModel.deleteAthleteSingle((AthleteSingle) athlete);
            } else if (athlete instanceof AthleteTeam) {
                athleteViewModel.deleteAthleteTeam((AthleteTeam) athlete);
            }else {
                throw new IllegalArgumentException("Unknown athlete type");
            }
        }
    }

}
