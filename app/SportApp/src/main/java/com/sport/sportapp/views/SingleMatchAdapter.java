package com.sport.sportapp.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sport.sportapp.R;
import com.sport.sportapp.databinding.AthleteLayoutBinding;
import com.sport.sportapp.databinding.EventLayoutBinding;
import com.sport.sportapp.fragments.athlete.UpdateAthleteFragment;

import java.util.ArrayList;
import java.util.List;

import domain.Athlete;
import domain.AthleteScore;
import domain.SingleMatch;
import viewmodels.MainActivityViewModel;

public class SingleMatchAdapter extends RecyclerView.Adapter<SingleMatchAdapter.SingleMatchViewHolder> {

    private final List<SingleMatch> matches;
    private final MainActivityViewModel viewModel;
    private final Athlete athlete1 = null;
    private final Athlete athlete2 = null;
    String id;

    public SingleMatchAdapter(List<SingleMatch> matches, MainActivityViewModel viewModel) {
        this.matches = matches;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public SingleMatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SingleMatchViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.event_layout,parent,false),
                viewModel
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SingleMatchViewHolder holder, int position) {
        holder.bindData(matches.get(position),matches.get(position).getAthlete1(),matches.get(position).getAthlete2());
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public static class SingleMatchViewHolder extends RecyclerView.ViewHolder{

        private final EventLayoutBinding binding;
        private final MainActivityViewModel viewModel;

        public SingleMatchViewHolder(@NonNull View itemView, MainActivityViewModel viewModel) {
            super(itemView);
            this.viewModel = viewModel;
            binding = EventLayoutBinding.bind(itemView);
            binding.eventUpdateButton.setOnClickListener(this::onUpdateButtonClicked);
            binding.eventDeleteButton.setOnClickListener(this::onDeleteButtonClicked);
        }

        private void bindData(SingleMatch match,Athlete athlete1, Athlete athlete2) {
            binding.athlete1IdInput.setText(String.valueOf(athlete1.getAthleteName()));
            binding.athlete1NameInput.setText(String.valueOf(athlete1.getAthleteName()));
            binding.athlete2IdInput.setText(String.valueOf(athlete2.getAthleteName()));
            binding.athlete2NameInput.setText(String.valueOf(athlete2.getAthleteName()));
            binding.athleteNamesInput.setText(String.valueOf(athlete1.getAthleteName()) + " vs " + String.valueOf(athlete2.getAthleteName()) );
            binding.eventIdInput.setText(match.getId());

        }

        private void onUpdateButtonClicked(View view) {
            long id = Long.parseLong(binding.eventIdInput.getText().toString());
           // viewModel.navigateTo(R.id.updateEventFragment, UpdateAthleteFragment.getBundleRequest(id));
        }

        private void onDeleteButtonClicked(View view) {
            //long id =
           // viewModel.deleteMatchById(id);
        }
    }

}
