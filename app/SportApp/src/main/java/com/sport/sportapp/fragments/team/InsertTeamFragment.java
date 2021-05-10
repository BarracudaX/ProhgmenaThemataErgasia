package com.sport.sportapp.fragments.team;

import android.widget.Toast;

import com.sport.sportapp.R;
import com.sport.sportapp.fragments.team.TeamFormFragment;

import domain.Team;

public class InsertTeamFragment extends TeamFormFragment {

    @Override
    public int getButtonStringResourceId() {
        return R.string.team_create_button;
    }

    @Override
    public int getTitleStringResourceId() {
        return R.string.create_team_title;
    }

    @Override
    public void buttonClickedEvent(Team team) {
        activityViewModel.insertTeam(team);
        activityViewModel.navigateBack();
        Toast.makeText(getActivity(), R.string.added_team_success_message, Toast.LENGTH_LONG).show();
    }

}
