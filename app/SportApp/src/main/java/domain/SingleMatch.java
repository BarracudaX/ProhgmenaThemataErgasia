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


}
