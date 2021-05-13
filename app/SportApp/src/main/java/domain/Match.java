package domain;

import com.google.firebase.firestore.Exclude;

import java.util.Date;

public abstract class Match {

    private Date matchDate;
    private String city;
    private String country;
    private Sport sport;
    private long sportId;
    private String Id;

    public Match(){


    }

    public Match(Date matchDate, String city, String country, Sport sport) {
        this.matchDate = matchDate;
        this.city = city;
        this.country = country;
        this.sport = sport;
        this.sportId = sport.getSportId();
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

    @Exclude
    public Sport getSport() {
        return sport;
    }

    public long getSportId(){
        return sportId;
    }

    @Exclude
    public String getId() {
        return Id;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }
}
