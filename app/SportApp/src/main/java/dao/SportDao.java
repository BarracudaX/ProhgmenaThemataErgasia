package dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import java.util.Map;

import domain.sport.AthleteSport;
import domain.sport.Sport;
import domain.sport.TeamSport;

@Dao
public interface SportDao {

    @Insert
    void insertAthleteSport(AthleteSport sport);

    @Insert
    void insertTeamSport(TeamSport sport);

    @Update
    void updateAthleteSport(AthleteSport sport);

    @Update
    void updateTeamSport(TeamSport sport);

    @Query("SELECT * FROM AthleteSport")
    LiveData<List<AthleteSport>> athleteSports();

    @Query("SELECT * FROM TeamSport")
    LiveData<List<TeamSport>> teamSports();

    @Query("SELECT * FROM AthleteSport WHERE sportId = :sportId")
    LiveData<AthleteSport> findAthleteSpordById(long sportId);

    @Query("SELECT * FROM TeamSport WHERE sportId = :sportId")
    LiveData<TeamSport> findTeamSportById(long sportId);

    @Query("SELECT * FROM TeamSport")
    List<TeamSport> allTeamSports();

    @Query("DELETE FROM AthleteSport WHERE sportId = :sportId")
    void deleteAthleteSportById(long sportId);

    @Query("DELETE FROM TeamSport WHERE sportId = :sportId")
    void deleteTeamSportById(long sportId);

    @Query("SELECT * FROM TeamSport WHERE sportId = :sportId")
    TeamSport getSportTeamById(long sportId);
}
