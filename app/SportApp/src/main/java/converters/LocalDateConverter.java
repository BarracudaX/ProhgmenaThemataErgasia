package converters;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

import java.time.LocalDate;

public class LocalDateConverter {

    @TypeConverter
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static LocalDate fromString(String localDateString){
        return LocalDate.parse(localDateString);
    }

    @TypeConverter
    public static String localDateToString(LocalDate localDate){
        return localDate.toString();
    }


}
