package com.sport.sportapp.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sport.sportapp.R;
import com.sport.sportapp.databinding.FragmentUpdateTeamBinding;

import domain.Team;
import viewmodels.MainActivityViewModel;

public class UpdateTeamFragment extends TeamFormFragment {

    private static final String ID_KEY = "TEAM_ID";

    private MainActivityViewModel viewModel;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
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
        viewModel.updateTeam(updateRequest);
        viewModel.navigateBack();
        Toast.makeText(getActivity(), R.string.update_team_success_message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getTeamById(getArguments().getLong(ID_KEY)).observe(this,team -> {
            binding.teamNameInput.setText(team.getTeamName());
            binding.stadiumNameInput.setText(team.getStadiumName());
            binding.teamCityInput.setText(team.getCity());
            binding.teamCountryInput.setText(team.getCountry());
            binding.teamFoundationInput.setText(team.getFoundationDate().toString());
            int pos = 0;
            for (int i = 0; i < binding.teamSportInput.getAdapter().getCount(); i++) {
                String sportIdName = (String) binding.teamSportInput.getItemAtPosition(i);
                if (team.getSportId() == Long.parseLong(sportIdName.substring(0, sportIdName.indexOf("-")))) {
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
