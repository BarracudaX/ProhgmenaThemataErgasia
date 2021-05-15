package domain.sport;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

public enum AthleteSportType {
    TENNIS(2),TRACK(8),PING_PONG(12);

    private final int numberOfAthletes;

    AthleteSportType(int numberOfAthletes) {
        this.numberOfAthletes = numberOfAthletes;
    }

    public int getNumberOfAthletes() {
        return numberOfAthletes;
    }

}
