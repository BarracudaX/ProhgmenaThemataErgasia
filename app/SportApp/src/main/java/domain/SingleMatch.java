package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SingleMatch extends Match {

    private final List<AthleteScore> athleteScores = new ArrayList<>();

    public SingleMatch(){

    }

    public SingleMatch(Date matchDate, String city, String country, Sport sport) {
        super(matchDate, city, country, sport);
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
