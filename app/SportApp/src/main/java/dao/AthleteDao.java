package dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import domain.Athlete;

@Dao
public interface AthleteDao extends BaseDao<Athlete> {

    @Query("SELECT * FROM Athlete")
    List<Athlete> loadAllAthletes();

}
