package dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import domain.Athlete;
import domain.AthleteIdNameSportModel;
import domain.Team;

@Dao
public interface AthleteDao extends BaseDao<Athlete> {

    @Query("SELECT * FROM Athlete")
    LiveData<List<Athlete>> loadAllAthletes();
    @Query("SELECT * FROM Athlete WHERE sportId = :sport")
    LiveData<List<Athlete>> findAthletesBySport(long sport);
    @Query("SELECT name, athleteCode, sportId FROM Athlete ORDER BY sportId")
    LiveData<List<AthleteIdNameSportModel>> loadAllAthletesBySport();
    @Query("DELETE FROM Athlete WHERE athleteCode = :id")
    void deleteById(long id);
}
