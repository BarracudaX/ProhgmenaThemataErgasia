package converters;


import androidx.room.TypeConverter;

import domain.SportType;

public class SportTypeConverter {

    @TypeConverter
    public static SportType fromString(String sportTypeString){
        return SportType.valueOf(sportTypeString);
    }

    @TypeConverter
    public static String sportTypeToString(SportType sportType) {
        return sportType.toString();
    }

}
