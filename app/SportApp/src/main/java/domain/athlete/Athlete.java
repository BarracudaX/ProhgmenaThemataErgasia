package domain.athlete;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

import domain.sport.Sport;
import domain.team.Team;


public abstract class Athlete {

    public String firstName;

    public String lastName;

    public String city;

    public String country;

    public LocalDate dateOfBirth;


    public Athlete(String firstName, String lastName, String city, String country, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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

    public abstract Long getAthleteId();
}
