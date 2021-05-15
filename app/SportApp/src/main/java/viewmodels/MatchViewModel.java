package viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import dao.AppDatabase;
import dao.FirestoreMatchDao;
import dao.MatchDao;
import domain.match.TeamMatch;

public class MatchViewModel extends AndroidViewModel {

    private final MatchDao matchDao;

    public MatchViewModel(@NonNull @NotNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        matchDao = new FirestoreMatchDao(appDatabase.teamDao(), appDatabase.athleteDao(), appDatabase.sportDao());
    }

    public LiveData<List<TeamMatch>> teamMatches() {
        return matchDao.teamMatches();
    }

    public void updateTeamMatch(TeamMatch teamMatch) {
        matchDao.updateTeamMatch(teamMatch);
    }

    public void deleteTeamMatch(TeamMatch teamMatch) {

    }

    public void insertTeamMatch(TeamMatch teamMatch) {
        matchDao.insertTeamMatch(teamMatch);
    }
}
