package dao;
@Dao
public interface AthleteDao {
    @Insert
    public void insertAthlete(Athlete athlete);

    @Update
    public void updateAthlete(Athlete athlete);

    @Delete
    public void deleteAthlete(Athlete athlete);

    @Query("SELECT * FROM Athlete")
    public Athlete[] loadAllAthletes; 
}
