package com.sport.sportapp.fragments.team;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;

import com.sport.sportapp.R;

import domain.sport.TeamSport;
import domain.team.Team;
import viewmodels.MainViewModel;

public class UpdateTeamFragment extends TeamFormFragment {

    private static final String ID_KEY = "TEAM_ID";

    private MainViewModel viewModel;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
    }

    @Override
    public int getButtonStringResourceId() {
        return R.string.team_update_button;
    }

    @Override
    public int getTitleStringResourceId() {
        return R.string.update_team_title;
    }

    @Override
    public void buttonClickedEvent(Team team) {
        Team updateRequest = new Team(
                getArguments().getLong(ID_KEY),team.getTeamName(),team.getStadiumName(),
                team.getCity(),team.getCountry(),team.getFoundationDate(),team.getSportId()
        );
        teamViewModel.updateTeam(updateRequest);
        viewModel.navigateBack();
        Toast.makeText(getActivity(), R.string.update_team_success_message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        teamViewModel.teamById(getArguments().getLong(ID_KEY)).observe(getViewLifecycleOwner(),team -> {
            binding.teamNameInput.setText(team.getTeamName());
            binding.stadiumNameInput.setText(team.getStadiumName());
            binding.teamCityInput.setText(team.getCity());
            binding.teamCountryInput.setText(team.getCountry());
            binding.teamFoundationInput.setText(team.getFoundationDate().toString());
            int pos = 0;
            for (int i = 0; i < binding.teamSportInput.getAdapter().getCount(); i++) {
                TeamSport teamSport = (TeamSport) binding.teamSportInput.getItemAtPosition(i);
                if (team.getSportId() == teamSport.getSportId()) {
                    pos = i;
                    break;
                }
            }
            binding.teamSportInput.setSelection(pos);
        });
    }

    public static Bundle getBundleRequest(long teamId) {
         Bundle bundle = new Bundle();
         bundle.putLong(ID_KEY,teamId);
         return bundle;
     }
}
