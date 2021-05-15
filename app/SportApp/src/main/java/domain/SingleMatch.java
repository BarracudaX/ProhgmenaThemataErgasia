package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SingleMatch extends Match {

    private final List<AthleteScore> athleteScores = new ArrayList<>();

    private AthleteScore athlete1;
    private AthleteScore athlete2;
    public SingleMatch(){

    }

    public SingleMatch(Date matchDate, String city, String country, Sport sport, AthleteScore athlete1, AthleteScore athlete2) {
        super(matchDate, city, country, sport);
        this.athlete1=athlete1;
        this.athlete2=athlete2;
        athleteScores.add(athlete1);
        athleteScores.add(athlete2);
    }


    public List<AthleteScore> getAthleteScores() {
        return athleteScores;
    }

   public Long getAthlete1Id(){ return athleteScores.get(0).getAthleteId();}

   public Long getAthlete2Id(){ return athleteScores.get(1).getAthleteId();}

    public Athlete getAthlete1(){ return athleteScores.get(0).getAthlete();}

    public Athlete getAthlete2(){ return athleteScores.get(1).getAthlete();}

    public double getAthlete1Score(){return athleteScores.get(0).getScore();}

    public double getAthlete2Score(){return athleteScores.get(1).getScore();}


}
