package domain;

import java.util.Date;

public abstract class Match {

    private Date matchDate;
    private String city;
    private String country;
    private Sport sport;
    private String Id;

    public Match(){


    }

    public Match(Date matchDate, String city, String country, Sport sport) {
        this.matchDate = matchDate;
        this.city = city;
        this.country = country;
        this.sport = sport;
    }

    public void setId(String id) {
        Id = id;
    }

    public Date getMatchDate() {
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
