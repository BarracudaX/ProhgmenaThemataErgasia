package converters;

import androidx.room.TypeConverter;

import domain.sport.AthleteSportType;

public class AthleteSportTypeConverter {


    @TypeConverter
    public static String toString(AthleteSportType athleteSportType){
        return athleteSportType.toString();
    }

    @TypeConverter
    public static AthleteSportType toAthleteSportType(String athleteSportType){
        return AthleteSportType.valueOf(athleteSportType);
    }

}
