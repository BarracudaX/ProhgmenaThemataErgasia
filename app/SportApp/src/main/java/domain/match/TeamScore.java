package domain.match;

import com.google.firebase.firestore.Exclude;

import domain.team.Team;

public class TeamScore {

    private long sportId;
    private double score;
    private long teamId;

    public TeamScore(Team team, double score) {
        this.sportId = team.getSportId();
        this.score = score;
        this.teamId = team.getTeamId();
    }

    @Exclude
    public long getSportId() {
        return sportId;
    }

    public double getScore() {
        return score;
    }

    public long getTeamId() {
        return teamId;
    }
}
