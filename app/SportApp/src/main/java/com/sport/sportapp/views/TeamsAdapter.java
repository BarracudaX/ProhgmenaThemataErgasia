package com.sport.sportapp.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sport.sportapp.R;
import com.sport.sportapp.databinding.TeamLayoutBinding;
import com.sport.sportapp.fragments.UpdateTeamFragment;

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
            binding.teamItemUpdateButton.setOnClickListener(this::onUpdateButtonClicked);
            binding.teamItemDeleteButton.setOnClickListener(this::onDeleteButtonClicked);
        }

        private void bindData(Team team) {
            binding.teamIteamIdInput.setText(String.valueOf(team.getTeamId()));
            binding.teamIteamNameInput.setText(team.getTeamName());
        }

        private void onUpdateButtonClicked(View view) {
            long id = Long.parseLong(binding.teamIteamIdInput.getText().toString());
            viewModel.navigateTo(R.id.updateTeamDestination, UpdateTeamFragment.getBundleRequest(id));
        }

        private void onDeleteButtonClicked(View view) {
            long id = Long.parseLong(binding.teamIteamIdInput.getText().toString());
            viewModel.deleteTeamById(id);
        }
    }

}
