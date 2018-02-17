package philippbugayevskiy.example.com;


import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import java.util.List;

/**
 * General interface for tracking analytics inside application.
 */
public interface ApplicationAnalytics {
    /**
     * Tracks an event with provided name and event parameters.
     */
    void trackEvent(@NonNull String eventName, @NonNull List<Pair<String, String>> eventParams);

    /**
     * Simplified version of {@link #trackEvent(String, List)} that tracks event without parameters.
     */
    void trackEvent(@NonNull String eventName);

    /**
     * Tracks screen view event with additional event parameters.
     */
    void trackScreenView(@NonNull String screenName, @NonNull Pair<String, String>... additionalParams);

    /**
     * Tracks screen view event.
     */
    void trackScreenView(@NonNull String screenName);

    /**
     * Configures user values that will be sent during analytics events.
     */
    void setProperty(@NonNull String propertyName, @NonNull String propertyValue);
}
