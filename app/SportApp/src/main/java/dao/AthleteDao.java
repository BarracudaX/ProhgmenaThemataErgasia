package dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import domain.Athlete;
import domain.Team;

@Dao
public interface AthleteDao extends BaseDao<Athlete> {

    @Query("SELECT * FROM Athlete")
    LiveData<List<Athlete>> loadAllAthletes();

    @Query("SELECT * FROM Athlete WHERE sportId = :sport")
    List<Athlete> findAthletesBySport(long sport);

    @Query("DELETE FROM Athlete WHERE athleteCode = :id")
    void deleteById(long id);

    @Query("SELECT * FROM Athlete WHERE athleteCode = :athleteId")
    LiveData<Athlete> findById(long athleteId);

    @Query("SELECT * FROM Athlete")
    List<Athlete> Athletes();
}
