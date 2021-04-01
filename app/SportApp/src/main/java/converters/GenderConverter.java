package converters;


import androidx.room.TypeConverter;

import domain.Gender;

public class GenderConverter {

    @TypeConverter
    public static Gender fromString(String genderString){
        return Gender.valueOf(genderString);
    }

    @TypeConverter
    public static String genderToString(Gender gender) {
        return gender.toString();
    }

}
