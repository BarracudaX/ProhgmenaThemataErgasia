package com.sport.sportapp.fragments.match;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.sport.sportapp.R;
import com.sport.sportapp.fragments.BaseFragment;
import com.sport.sportapp.spinner.TeamAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

import domain.match.TeamMatch;
import domain.match.TeamScore;
import domain.sport.Sport;
import domain.sport.TeamSport;
import domain.team.Team;
import viewmodels.MatchViewModel;
import viewmodels.SportViewModel;
import viewmodels.TeamViewModel;

public class InsertTeamMatchFragment extends TeamMatchFormFragment {


    @Override
    public int getButtonStringResourceId() {
        return R.string.create_team_match_title;
    }

    @Override
    public int getTitleStringResourceId() {
        return R.string.create_team_match_title;
    }

    @Override
    public void buttonClickedEvent(TeamMatch teamMatch) {
        matchViewModel.insertTeamMatch(teamMatch);
        mainViewModel.navigateBack();
        Toast.makeText(getContext(), "Team Match created successfully!", Toast.LENGTH_LONG).show();
    }
}
