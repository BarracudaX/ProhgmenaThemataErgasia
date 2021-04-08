package com.sport.sportapp.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sport.sportapp.R;
import com.sport.sportapp.databinding.TeamLayoutBinding;

import java.util.List;

import domain.Team;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.TeamViewHolder> {

    private final List<Team> teams;

    public TeamsAdapter(List<Team> teams) {
        this.teams = teams;
    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TeamViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.team_layout,parent,false)
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

        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = TeamLayoutBinding.bind(itemView);
        }

        private void bindData(Team team) {
            binding.teamIteamIdInput.setText(String.valueOf(team.getTeamId()));
            binding.teamIteamNameInput.setText(team.getTeamName());
        }
    }
}
