package com.sport.sportapp.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sport.sportapp.R;
import com.sport.sportapp.databinding.AthleteLayoutBinding;
import com.sport.sportapp.databinding.TeamLayoutBinding;
import com.sport.sportapp.fragments.UpdateAthleteFragment;
import com.sport.sportapp.fragments.UpdateTeamFragment;

import java.util.List;

import domain.Athlete;
import domain.Team;
import viewmodels.MainActivityViewModel;

public class AthletesAdapter extends RecyclerView.Adapter<AthletesAdapter.AthleteViewHolder> {

    private final List<Athlete> athletes;
    private final MainActivityViewModel viewModel;

    public AthletesAdapter(List<Athlete> athletes, MainActivityViewModel viewModel) {
        this.athletes = athletes;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public AthleteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AthleteViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.athlete_layout,parent,false),
                viewModel
        );
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
        private final MainActivityViewModel viewModel;

        public AthleteViewHolder(@NonNull View itemView, MainActivityViewModel viewModel) {
            super(itemView);
            this.viewModel = viewModel;
            binding = AthleteLayoutBinding.bind(itemView);
            binding.athleteUpdateButton.setOnClickListener(this::onUpdateButtonClicked);
            binding.athleteDeleteButton.setOnClickListener(this::onDeleteButtonClicked);
        }

        private void bindData(Athlete athlete) {
            binding.athleteIdInput.setText(String.valueOf(athlete.getAthleteCode()));
            binding.athleteNameInput.setText(athlete.getAthleteName());
        }

        private void onUpdateButtonClicked(View view) {
            long id = Long.parseLong(binding.athleteIdInput.getText().toString());
            viewModel.navigateTo(R.id.updateAthleteFragment, UpdateAthleteFragment.getBundleRequest(id));
        }

        private void onDeleteButtonClicked(View view) {
            long id = Long.parseLong(binding.athleteIdInput.getText().toString());
            viewModel.deleteAthleteById(id);
        }
    }

}
