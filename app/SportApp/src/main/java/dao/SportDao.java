package dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import domain.Sport;

@Dao
public interface SportDao {

    @Query("SELECT * FROM Sport")
    List<Sport> loadAllSports();

    @Insert
    void insertSport(Sport sport);

    @Update
    void updateSport(Sport sport);

    @Delete
    void deleteSport(Sport sport);

}
