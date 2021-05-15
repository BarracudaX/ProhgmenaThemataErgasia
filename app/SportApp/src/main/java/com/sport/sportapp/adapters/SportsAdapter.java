package com.sport.sportapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sport.sportapp.R;
import com.sport.sportapp.databinding.SportLayoutBinding;
import com.sport.sportapp.fragments.sport.UpdateAthleteSportFragment;
import com.sport.sportapp.fragments.sport.UpdateTeamSportFragment;
import java.util.List;


import domain.sport.AthleteSport;
import domain.sport.Sport;
import domain.sport.TeamSport;
import viewmodels.MainViewModel;
import viewmodels.SportViewModel;

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.SportViewHolder> {

    private final List<Sport> sports;
    private final MainViewModel mainViewModel;
    private final SportViewModel sportViewModel;

    public SportsAdapter(List<Sport> sports, SportViewModel sportViewModel, MainViewModel mainViewModel){
        this.sports = sports;
        this.mainViewModel = mainViewModel;
        this.sportViewModel = sportViewModel;
    }

    @NonNull
    @Override
    public SportsAdapter.SportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SportsAdapter.SportViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.sport_layout,parent,false),
                sportViewModel,
                mainViewModel
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SportsAdapter.SportViewHolder holder, int position) {
        holder.bindData(sports.get(position));
    }

    @Override
    public int getItemCount() {
        return sports.size();
    }

    public static class SportViewHolder extends RecyclerView.ViewHolder{

        private final SportLayoutBinding binding;
        private final MainViewModel mainViewModel;
        private final SportViewModel sportViewModel;
        private Sport sport;

        public SportViewHolder(@NonNull View itemView, SportViewModel sportViewModel, MainViewModel mainViewModel) {
            super(itemView);
            this.mainViewModel = mainViewModel;
            this.sportViewModel = sportViewModel;
            binding = SportLayoutBinding.bind(itemView);
            binding.sportUpdateButton.setOnClickListener(this::onUpdateButtonClicked);
            binding.sportDeleteButton.setOnClickListener(this::onDeleteButtonClicked);
        }

        private void bindData(Sport sport) {
            this.sport = sport;
            binding.sportIdInput.setText(String.valueOf(sport.getSportId()));
            binding.sportNameInput.setText(sport.getSportName());
            if (sport instanceof AthleteSport) {
                binding.sportTypeInput.setText("Athlete Sport");
            } else if (sport instanceof TeamSport) {
                binding.sportTypeInput.setText("Team Sport");
            }else{
                binding.sportTypeInput.setText("Unknown Sport");
            }
        }

        private void onUpdateButtonClicked(View view) {
            if (sport instanceof AthleteSport) {
                mainViewModel.navigateTo(R.id.updateAthleteSportDestination, UpdateAthleteSportFragment.getBundleRequest(sport.getSportId()));
            } else if (sport instanceof TeamSport) {
                mainViewModel.navigateTo(R.id.updateTeamSportDestination, UpdateTeamSportFragment.getBundleRequest(sport.getSportId()));
            }else{
                throw new IllegalArgumentException("Unknown sport type");
            }
        }

        private void onDeleteButtonClicked(View view) {
            if (sport instanceof AthleteSport) {
                sportViewModel.deleteAthleteSport((AthleteSport) sport);
            } else if (sport instanceof TeamSport) {
                sportViewModel.deleteTeamSport((TeamSport) sport);
            }else{
                throw new IllegalArgumentException("Unknown sport type");
            }
        }
    }
}