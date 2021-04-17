package domain;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity
public class Athlete {

    @PrimaryKey(autoGenerate = true)
    public long athleteCode;

    public String name;

    public String surname;

    public String city;

    public String country;

    //public Sport sport;

    public LocalDate dateOfBirth;

    //public Team team;

    public Athlete(String name, String surname, String city, String country, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.country = country;
        //this.sport = sport;
        this.dateOfBirth = dateOfBirth;
        //this.team= team;
    }

    public long getAthleteCode() {
        return athleteCode;
    }

    public String getAthleteName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setAthleteCode(long athleteCode) {
        this.athleteCode = athleteCode;
    }
}
