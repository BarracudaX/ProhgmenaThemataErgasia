package domain.athlete;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

import domain.sport.TeamSport;
import domain.team.Team;

@Entity(foreignKeys = {
        @ForeignKey(entity = Team.class,parentColumns = "teamId",childColumns = "teamId",onDelete = ForeignKey.CASCADE)
})
public class AthleteTeam extends Athlete{

    @PrimaryKey(autoGenerate = true)
    private Long athleteId;

    private long teamId;

    @Ignore
    public AthleteTeam(String firstName, String lastName, String city, String country, LocalDate dateOfBirth,long teamId) {
        super(firstName, lastName, city, country, dateOfBirth);
        this.teamId = teamId;
    }

    public AthleteTeam(String firstName, String lastName, String city, String country,
                       LocalDate dateOfBirth, long athleteId,long teamId) {
        super(firstName, lastName, city, country, dateOfBirth);
        this.athleteId = athleteId;
        this.teamId = teamId;
    }

    public Long getAthleteId() {
        return athleteId;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setAthleteId(long athleteId) {
        this.athleteId = athleteId;
    }
}
