  package dao;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Set;

import domain.Match;
import domain.SingleMatch;
import domain.TeamMatch;

public class FirestoreMatchDao implements MatchDao{

    private final static String MatchCollectionName = "matches";

    private final TeamDao teamDao;
    private final AthleteDao athleteDao;
    private final SportDao sportDao;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public FirestoreMatchDao(TeamDao teamDao, AthleteDao athleteDao, SportDao sportDao) {
        this.teamDao = teamDao;
        this.athleteDao = athleteDao;
        this.sportDao = sportDao;
    }

    @Override
    public void insertTeamMatch(TeamMatch match) {
        db.collection(MatchCollectionName).add(match).addOnSuccessListener(documentReference ->{
                    match.setId(documentReference.getId());
                });
    }

    @Override
    public void insertSingleMatch(SingleMatch match) {

    }

    @Override
    public void updateTeamMatch(TeamMatch match) {

    }

    @Override
    public void updateSingleMatch(SingleMatch match) {

    }

    @Override
    public void deleteTeamMatch(TeamMatch match) {

    }

    @Override
    public void deleteSingleMatch(SingleMatch match) {

    }

    @Override
    public Set<TeamMatch> selectTeamMatches() {
        return null;
    }

    @Override
    public Set<SingleMatch> selectSingleMatches() {
        return null;
    }
}
