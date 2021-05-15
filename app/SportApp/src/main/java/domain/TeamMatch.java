package domain;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class TeamMatch extends Match {

    private Team firstTeam;

    private long firstTeamId;

    private int firstTeamScore;

    private Team secondTeam;

    private long secondTeamId;

    private int secondTeamScore;

    public TeamMatch(){

    }

    public TeamMatch(Date matchDate, String city, String country,
                     Sport sport, Team firstTeam, int firstTeamScore, Team secondTeam, int secondTeamScore) {
        super(matchDate, city, country, sport);
        this.firstTeam = firstTeam;
        this.firstTeamScore = firstTeamScore;
        this.secondTeam = secondTeam;
        this.secondTeamScore = secondTeamScore;
        this.firstTeamId = firstTeam.getTeamId();
        this.secondTeamId = secondTeam.getTeamId();
    }

    public TeamMatch(Date lastDatePicked, String toString, String toString1, Sport sportById) {
    }

    @Exclude
    public Team getFirstTeam() {
        return firstTeam;
    }

    public long getFirstTeamId() {
        return firstTeamId;
    }

    public int getFirstTeamScore() {
        return firstTeamScore;
    }

    @Exclude
    public Team getSecondTeam() {
        return secondTeam;
    }

    public long getSecondTeamId() {
        return secondTeamId;
    }

    public int getSecondTeamScore() {
        return secondTeamScore;
    }

    public void setFirstTeam(Team firstTeam) {
        this.firstTeam = firstTeam;
    }

    public void setSecondTeam(Team secondTeam) {
        this.secondTeam = secondTeam;
    }
}
