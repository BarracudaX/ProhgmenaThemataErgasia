package domain.match;

import com.google.firebase.firestore.Exclude;

import domain.sport.TeamSport;
import domain.team.Team;

public class TeamScore {

    private double score;
    private long teamId;

    public TeamScore(){

    }

    public TeamScore(Team team, double score) {
        this.score = score;
        this.teamId = team.getTeamId();
    }

    public double getScore() {
        return score;
    }

    public long getTeamId() {
        return teamId;
    }
}
