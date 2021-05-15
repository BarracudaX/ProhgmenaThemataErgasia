package com.sport.sportapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sport.sportapp.R;
import com.sport.sportapp.databinding.TeamMatchLayoutBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import domain.match.TeamMatch;
import domain.match.TeamScore;
import domain.team.Team;
import viewmodels.MainViewModel;
import viewmodels.MatchViewModel;
import viewmodels.TeamViewModel;

public class TeamMatchesAdapter extends RecyclerView.Adapter<TeamMatchesAdapter.TeamMatchViewHolder>{

    private final MainViewModel mainViewModel;
    private final MatchViewModel matchViewModel;
    private final List<TeamMatch> teamMatches;
    private final TeamViewModel teamViewModel;

    public TeamMatchesAdapter(MainViewModel mainViewModel, MatchViewModel matchViewModel,TeamViewModel teamViewModel, List<TeamMatch> teamMatches) {
        this.mainViewModel = mainViewModel;
        this.matchViewModel = matchViewModel;
        this.teamMatches = teamMatches;
        this.teamViewModel = teamViewModel;
    }

    @NonNull
    @NotNull
    @Override
    public TeamMatchViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new TeamMatchViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.team_match_layout,parent,false),
                teamViewModel,
                matchViewModel);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TeamMatchViewHolder holder, int position) {
        holder.bindData(teamMatches.get(position));
    }

    @Override
    public int getItemCount() {
        return teamMatches.size();
    }

    public static class TeamMatchViewHolder extends RecyclerView.ViewHolder{

        private final TeamViewModel teamViewModel;
        private final MatchViewModel matchViewModel;
        private final TeamMatchLayoutBinding binding;

        public TeamMatchViewHolder(@NonNull @NotNull View itemView, TeamViewModel teamViewModel, MatchViewModel matchViewModel) {
            super(itemView);
            binding = TeamMatchLayoutBinding.bind(itemView);
            this.teamViewModel = teamViewModel;
            this.matchViewModel = matchViewModel;
        }

        public void bindData(TeamMatch teamMatch) {
            TeamScore firstTeamScore = teamMatch.getFirstTeamScore();
            TeamScore secondTeamScore = teamMatch.getSecondTeamScore();
            binding.firstTeamIdInput.setText(String.valueOf(firstTeamScore.getTeamId()));
            binding.secondTeamIdInput.setText(String.valueOf(secondTeamScore.getTeamId()));
            binding.firstTeamScoreInput.setText(String.valueOf(firstTeamScore.getScore()));
            binding.secondTeamScoreInput.setText(String.valueOf(secondTeamScore.getScore()));
            binding.updateTeamMatchButton.setOnClickListener(v -> {
                matchViewModel.updateTeamMatch(teamMatch);
            });

            binding.deleteTeamMatchButton.setOnClickListener(v -> {
                matchViewModel.deleteTeamMatch(teamMatch);
            });

            Team firstTeam = teamViewModel.getTeam(firstTeamScore.getTeamId());
            Team secondTeam = teamViewModel.getTeam(secondTeamScore.getTeamId());

            binding.firstTeamNameInput.setText(firstTeam.getTeamName());
            binding.secondTeamNameInput.setText(secondTeam.getTeamName());

            binding.teamMatchTitle.setText(firstTeam.getTeamName()+" VS "+secondTeam.getTeamName());
        }
    }

}
