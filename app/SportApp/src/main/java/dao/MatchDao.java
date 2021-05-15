package dao;

import androidx.lifecycle.LiveData;

import java.util.List;

import domain.match.Match;
import domain.match.AthletesMatch;
import domain.match.TeamMatch;

public interface MatchDao {

    void insertTeamMatch(TeamMatch match);

    void insertSingleMatch(AthletesMatch match);

    void updateTeamMatch(TeamMatch match);

    void updateSingleMatch(AthletesMatch match);

    void deleteMatch(Match match);

    LiveData<List<TeamMatch>> selectTeamMatches();

    LiveData<List<AthletesMatch>> selectSingleMatches();

}
