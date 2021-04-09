package viewmodels;

import android.os.Bundle;

public class NavigationRequest {

    private final int destinationId;
    private final Bundle bundle;

    NavigationRequest(int destinationId, Bundle bundle) {
        this.destinationId = destinationId;
        this.bundle = bundle;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public Bundle getBundle() {
        return bundle;
    }
}
