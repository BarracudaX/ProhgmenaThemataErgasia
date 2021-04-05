package services;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import com.sport.sportapp.fragments.InsertTeamFragment;

import dao.AthleteDao;
import dao.SportDao;
import dao.TeamDao;

public class MyFactoryFragment extends FragmentFactory {

    private final SportDao sportDao;
    private final TeamDao teamDao;
    private final AthleteDao athleteDao;

    public MyFactoryFragment(SportDao sportDao, TeamDao teamDao, AthleteDao athleteDao) {
        this.sportDao = sportDao;
        this.teamDao = teamDao;
        this.athleteDao = athleteDao;
    }

    @NonNull
    @Override
    public Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull String className) {
        Class<? extends Fragment> fragmentClass = loadFragmentClass(classLoader, className);
        if (fragmentClass == InsertTeamFragment.class) {
            return new InsertTeamFragment(teamDao);
        }
        return super.instantiate(classLoader, className);
    }
}
