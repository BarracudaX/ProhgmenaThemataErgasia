package dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import domain.athlete.AthleteSingle;
import domain.athlete.AthleteTeam;

@Dao
public interface AthleteDao {


    @Insert
    void insertAthleteSingle(AthleteSingle athlete);

    @Insert
    void insertAthleteTeam(AthleteTeam athlete);

    @Update
    void updateAthleteSingle(AthleteSingle athlete);

    @Update
    void updateAthleteTeam(AthleteTeam athlete);

    @Query("DELETE FROM AthleteSingle WHERE athleteId = :athleteId")
    void deleteAthleteSingleById(Long athleteId);

    @Query("DELETE FROM AthleteTeam WHERE athleteId = :athleteId")
    void deleteAthleteTeamById(long athleteId);

    @Query("SELECT * FROM AthleteSingle WHERE athleteId = :athleteId")
    LiveData<AthleteSingle> findAthleteSingleById(long athleteId);

    @Query("SELECT * FROM AthleteTeam WHERE athleteId = :athleteId")
    LiveData<AthleteTeam> findAthleteTeamById(long athleteId);

    @Query("SELECT * FROM AthleteSingle")
    LiveData<List<AthleteSingle>> athleteSingles();

    @Query("SELECT * FROM AthleteTeam")
    LiveData<List<AthleteTeam>> athleteTeams();

}
