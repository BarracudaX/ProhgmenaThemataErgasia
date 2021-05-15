package domain.match;

import com.google.firebase.firestore.Exclude;

import java.util.Date;

public abstract class Match {

    private Date matchDate;
    private String city;
    private String country;
    private String Id;

    public Match(){

    }

    public Match(Date matchDate, String city, String country) {
        this.matchDate = matchDate;
        this.city = city;
        this.country = country;
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
    public String getId() {
        return Id;
    }

}
