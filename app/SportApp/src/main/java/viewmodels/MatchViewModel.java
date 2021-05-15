package viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import org.jetbrains.annotations.NotNull;

import dao.AppDatabase;
import dao.FirestoreMatchDao;
import dao.MatchDao;

public class MatchViewModel extends AndroidViewModel {

    private final MatchDao matchDao;

    public MatchViewModel(@NonNull @NotNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        matchDao = new FirestoreMatchDao(appDatabase.teamDao(), appDatabase.athleteDao(), appDatabase.sportDao());
    }
}
