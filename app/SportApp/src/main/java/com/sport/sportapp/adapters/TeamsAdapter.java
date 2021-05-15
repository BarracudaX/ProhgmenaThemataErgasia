package com.sport.sportapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sport.sportapp.R;
import com.sport.sportapp.databinding.TeamLayoutBinding;
import com.sport.sportapp.fragments.team.UpdateTeamFragment;

import java.util.List;

import domain.team.Team;
import viewmodels.MainViewModel;
import viewmodels.TeamViewModel;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.TeamViewHolder> {

    private final List<Team> teams;
    private final TeamViewModel teamViewModel;
    private final MainViewModel mainViewModel;

    public TeamsAdapter(List<Team> teams, TeamViewModel teamViewModel, MainViewModel mainViewModel) {
        this.teams = teams;
        this.teamViewModel = teamViewModel;
        this.mainViewModel = mainViewModel;
    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TeamViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.team_layout,parent,false),
                teamViewModel,
                mainViewModel
        );
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {
        holder.bindData(teams.get(position));
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public static class TeamViewHolder extends RecyclerView.ViewHolder{

        private final TeamLayoutBinding binding;
        private final TeamViewModel teamViewModel;
        private final MainViewModel mainViewModel;

        public TeamViewHolder(@NonNull View itemView, TeamViewModel teamViewModel, MainViewModel mainViewModel) {
            super(itemView);
            this.teamViewModel = teamViewModel;
            this.mainViewModel = mainViewModel;
            binding = TeamLayoutBinding.bind(itemView);
        }

        private void bindData(Team team) {
            binding.teamIteamIdInput.setText(String.valueOf(team.getTeamId()));
            binding.teamIteamNameInput.setText(team.getTeamName());
            binding.teamItemUpdateButton.setOnClickListener(v->{
                mainViewModel.navigateTo(R.id.updateTeamDestination,UpdateTeamFragment.getBundleRequest(team.getTeamId()));
            });
            binding.teamItemDeleteButton.setOnClickListener(v ->{
                teamViewModel.deleteTeamById(team.getTeamId());
            });
        }
    }

}
