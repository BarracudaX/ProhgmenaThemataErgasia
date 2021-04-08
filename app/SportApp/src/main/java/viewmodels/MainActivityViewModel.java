package viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import dao.AppDatabase;
import dao.AthleteDao;
import dao.SportDao;
import dao.TeamDao;
import domain.SportIdNameModel;

public class MainActivityViewModel extends AndroidViewModel {

    private final TeamDao teamDao;
    private final SportDao sportDao;
    private final AthleteDao athleteDao;
    private LiveData<List<SportIdNameModel>> sportIdsAndNames ;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        teamDao = appDatabase.teamDao();
        sportDao = appDatabase.sportDao();
        athleteDao = appDatabase.athleteDao();
    }

    public LiveData<List<SportIdNameModel>> getSportIdsAndNames() {
        if (sportIdsAndNames == null) {
            sportIdsAndNames = sportDao.sportIdsAndNames();
        }
        return sportIdsAndNames;
    }

}
