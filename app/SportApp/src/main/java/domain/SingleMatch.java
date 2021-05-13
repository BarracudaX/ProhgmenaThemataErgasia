package domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class SingleMatch extends Match {

    private final Set<AthleteScore> scores = new HashSet<>();

    protected SingleMatch(LocalDateTime matchDate, String city, String country, Sport sport) {
        super(matchDate, city, country, sport);
    }

    public Set<AthleteScore> getAthleteScores() {
        return scores;
    }


}
