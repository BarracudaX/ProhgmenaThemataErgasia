package dao;

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
