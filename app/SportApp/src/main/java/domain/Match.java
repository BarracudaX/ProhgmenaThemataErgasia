package domain;

import java.time.LocalDateTime;

public abstract class Match {

    private final LocalDateTime matchDate;
    private final String city;
    private final String country;
    private final Sport sport;
    private String Id;


    protected Match(LocalDateTime matchDate, String city, String country, Sport sport) {
        this.matchDate = matchDate;
        this.city = city;
        this.country = country;
        this.sport = sport;
    }

    public void setId(String id) {
        Id = id;
    }

    public LocalDateTime getMatchDate() {
        return matchDate;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Sport getSport() {
        return sport;
    }

    public String getId() {
        return Id;
    }
}
