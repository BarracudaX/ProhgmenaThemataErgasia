package domain;

import java.time.LocalDateTime;

public abstract class Match {

    private LocalDateTime matchDate;
    private String city;
    private String country;
    private Sport sport;
    private String Id;

    public Match(){


    }

    public Match(LocalDateTime matchDate, String city, String country, Sport sport) {
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
