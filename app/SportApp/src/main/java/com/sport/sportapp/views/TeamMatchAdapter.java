package com.sport.sportapp.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sport.sportapp.R;
import com.sport.sportapp.databinding.SingleMatchLayoutBinding;
import com.sport.sportapp.databinding.TeamMatchLayoutBinding;

import java.util.List;

import domain.Athlete;
import domain.SingleMatch;
import domain.Team;
import domain.TeamMatch;
import viewmodels.MainActivityViewModel;

public class TeamMatchAdapter extends RecyclerView.Adapter<TeamMatchAdapter.TeamMatchViewHolder> {

    private final List<TeamMatch> matches;
    private final MainActivityViewModel viewModel;

    public TeamMatchAdapter(List<TeamMatch> matches, MainActivityViewModel viewModel) {
        this.matches = matches;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public TeamMatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TeamMatchViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.team_match_layout,parent,false),
                viewModel
        );
    }

    @Override
    public void onBindViewHolder(@NonNull TeamMatchViewHolder holder, int position) {
        holder.bindData(matches.get(position),matches.get(position).getFirstTeam(),matches.get(position).getSecondTeam());
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public static class TeamMatchViewHolder extends RecyclerView.ViewHolder{

        private final @NonNull TeamMatchLayoutBinding binding;
        private final MainActivityViewModel viewModel;

        public TeamMatchViewHolder(@NonNull View itemView, MainActivityViewModel viewModel) {
            super(itemView);
            this.viewModel = viewModel;
            binding = TeamMatchLayoutBinding.bind(itemView);
            binding.eventUpdateButton.setOnClickListener(this::onUpdateButtonClicked);
            binding.eventDeleteButton.setOnClickListener(this::onDeleteButtonClicked);
        }

        private void bindData(TeamMatch match,Team team1, Team team2) {
            binding.team1IdInput.setText(String.valueOf(team1.getTeamId()));
            binding.team1NameInput.setText(String.valueOf(team1.getTeamName()));
            binding.team2IdInput.setText(String.valueOf(team2.getTeamId()));
            binding.team2NameInput.setText(String.valueOf(team2.getTeamName()));
            binding.teamNamesInput.setText(String.valueOf(team1.getTeamName()) + " vs " + String.valueOf(team2.getTeamName()) );
            binding.eventIdInput.setText(match.getId());
            binding.team1ScoreInput.setText(String.valueOf(match.getFirstTeamScore()));
            binding.team2ScoreInput.setText(String.valueOf(match.getSecondTeamScore()));
        }

        private void onUpdateButtonClicked(View view) {
            //long id = Long.parseLong(binding.eventIdInput.getText().toString());
           // viewModel.navigateTo(R.id.updateEventFragment, UpdateAthleteFragment.getBundleRequest(id));
        }

        private void onDeleteButtonClicked(View view) {
            //long id =
           // viewModel.deleteMatchById(id);
        }
    }

}
