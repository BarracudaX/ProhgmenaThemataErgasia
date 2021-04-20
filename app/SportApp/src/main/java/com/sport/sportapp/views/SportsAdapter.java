package com.sport.sportapp.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sport.sportapp.R;
import com.sport.sportapp.databinding.SportLayoutBinding;
import com.sport.sportapp.fragments.UpdateSportFragment;
import java.util.List;


import domain.Sport;
import viewmodels.MainActivityViewModel;

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.SportViewHolder> {

    private final List<Sport> sports;
    private final MainActivityViewModel viewModel;

    public SportsAdapter(List<Sport> sports, MainActivityViewModel viewModel){
        this.sports = sports;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public SportsAdapter.SportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SportsAdapter.SportViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.sport_layout,parent,false),
                viewModel
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
        private final MainActivityViewModel viewModel;

        public SportViewHolder(@NonNull View itemView, MainActivityViewModel viewModel) {
            super(itemView);
            this.viewModel = viewModel;
            binding = SportLayoutBinding.bind(itemView);
            binding.sportUpdateButton.setOnClickListener(this::onUpdateButtonClicked);
            binding.sportDeleteButton.setOnClickListener(this::onDeleteButtonClicked);
        }

        private void bindData(Sport sport) {
            binding.sportIdInput.setText(String.valueOf(sport.getSportId()));
            binding.sportNameInput.setText(sport.getSportName());
        }

        private void onUpdateButtonClicked(View view) {
            long id = Long.parseLong(binding.sportIdInput.getText().toString());
            viewModel.navigateTo(R.id.updateSportFragment, UpdateSportFragment.getBundleRequest(id));
        }

        private void onDeleteButtonClicked(View view) {
            long id = Long.parseLong(binding.sportIdInput.getText(String.valueOf(sport.getSportId())));
            viewModel.deleteSportById(id);
        }
    }
}