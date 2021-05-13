package dao;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.Set;

import domain.Match;
import domain.SingleMatch;
import domain.TeamMatch;

public interface MatchDao {

    void insertTeamMatch(TeamMatch match);

    void insertSingleMatch(SingleMatch match);

    void updateTeamMatch(TeamMatch match);

    void updateSingleMatch(SingleMatch match);

    void deleteMatch(Match match);

    LiveData<List<TeamMatch>> selectTeamMatches();

    LiveData<List<SingleMatch>> selectSingleMatches();

}
