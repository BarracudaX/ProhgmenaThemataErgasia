package domain;

import java.time.LocalDateTime;

public abstract class Match {

    private final LocalDateTime matchDate;
    private final String city;
    private final String country;
    private final Sport sport;
    private final String Id;


    protected Match(LocalDateTime matchDate, String city, String country, Sport sport, String id) {
        this.matchDate = matchDate;
        this.city = city;
        this.country = country;
        this.sport = sport;
        Id = id;
    }
}
