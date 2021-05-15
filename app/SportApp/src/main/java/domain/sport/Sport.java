package domain.sport;

import domain.Gender;

public abstract class Sport {

    private String sportName;

    private Gender gender;

    public Sport(String sportName, Gender gender) {
        this.sportName = sportName;
        this.gender = gender;
    }

    public String getSportName() {
        return sportName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public abstract long getSportId();

}
