package dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import converters.GenderConverter;
import converters.LocalDateConverter;
import converters.SportTypeConverter;
import domain.Athlete;
import domain.Sport;
import domain.Team;

@Database(entities = {Team.class, Athlete.class, Sport.class}, version = 1)
@TypeConverters({LocalDateConverter.class, GenderConverter.class, SportTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract AthleteDao athleteDao();

    public abstract TeamDao teamDao();

    public abstract SportDao sportDao();

}
