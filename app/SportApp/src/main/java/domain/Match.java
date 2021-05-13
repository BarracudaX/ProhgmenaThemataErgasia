package domain;

import java.time.LocalDateTime;

public abstract class Match {

    private final boolean isTeamMatch;
    private final LocalDateTime matchDate;
    private final String city;
    private final String country;
    private final Sport sport;


    protected Match(boolean isTeamMatch, LocalDateTime matchDate, String city, String country, Sport sport) {
        this.isTeamMatch = isTeamMatch;
        this.matchDate = matchDate;
        this.city = city;
        this.country = country;
        this.sport = sport;
    }
}
