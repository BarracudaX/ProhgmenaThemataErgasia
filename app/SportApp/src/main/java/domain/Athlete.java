package domain;
import android.room;

@Entity
public final class Athlete {
    @PrimaryKey
    public int AthleteCode;
    public String Name;
    public String Surname;
    public String City;
    public String Country;
    public int SportCode;
    public LocalDate DateOfBirth;

    public Athlete(int athleteCode, String name, String surname, String city, String country, int sportCode, LocalDate dateOfBirth) {
        AthleteCode = athleteCode;
        Name = name;
        Surname = surname;
        City = city;
        Country = country;
        SportCode = sportCode;
        DateOfBirth = dateOfBirth;
    }

    public int getAthleteCode() {
        return AthleteCode;
    }

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public String getCity() {
        return City;
    }

    public String getCountry() {
        return Country;
    }

    public int getSportCode() {
        return SportCode;
    }

    public LocalDate getDateOfBirth() {
        return DateOfBirth;
    }
}
