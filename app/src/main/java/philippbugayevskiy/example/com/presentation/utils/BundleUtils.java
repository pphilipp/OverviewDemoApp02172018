package philippbugayevskiy.example.com.presentation.utils;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import java.util.List;

public class BundleUtils {

    /**
     * Creates a {@link Bundle} and fulfills it with data form pairs using {@link Bundle#putString(String, String)}.
     *
     * @param pairs list of {@link Pair} that is used as source of data.
     * @return {@link Bundle} with all data.
     */
    public static @NonNull
    Bundle createBundleFromListOfPairs(@NonNull List<Pair<String, String>> pairs) {
        Preconditions.checkNotNull(pairs, "List of pairs could not be null");

        Bundle bundle = new Bundle();
        if (!pairs.isEmpty()) {
            for (Pair<String, String> parameter : pairs) {
                bundle.putString(parameter.first, parameter.second);
            }
        }

        return bundle;
    }

}
