package com.sport.sportapp.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sport.sportapp.R;
import com.sport.sportapp.databinding.TeamLayoutBinding;
import com.sport.sportapp.fragments.team.UpdateTeamFragment;

import java.util.List;

import domain.Team;
import viewmodels.MainActivityViewModel;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.TeamViewHolder> {

    private final List<Team> teams;
    private final MainActivityViewModel viewModel;

    public TeamsAdapter(List<Team> teams, MainActivityViewModel viewModel) {
        this.teams = teams;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TeamViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.team_layout,parent,false),
                viewModel
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
        private final MainActivityViewModel viewModel;

        public TeamViewHolder(@NonNull View itemView, MainActivityViewModel viewModel) {
            super(itemView);
            this.viewModel = viewModel;
            binding = TeamLayoutBinding.bind(itemView);
        }

        private void bindData(Team team) {
            binding.teamIteamIdInput.setText(String.valueOf(team.getTeamId()));
            binding.teamIteamNameInput.setText(team.getTeamName());
            binding.teamItemUpdateButton.setOnClickListener(v->{
                viewModel.navigateTo(R.id.updateTeamDestination,UpdateTeamFragment.getBundleRequest(team.getTeamId()));
            });
            binding.teamItemDeleteButton.setOnClickListener(v ->{
                viewModel.deleteTeamById(team.getTeamId());
            });
        }
    }

}
