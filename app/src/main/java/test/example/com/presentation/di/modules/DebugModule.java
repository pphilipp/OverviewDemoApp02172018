package test.example.com.presentation.di.modules;

import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import test.example.com.presentation.di.NetworkEnv;
import test.example.com.presentation.view.preferences.IntPreference;


@Module
public class DebugModule {

    @Provides
    @Singleton
    @NetworkEnv
    IntPreference provideNetworkEnv(SharedPreferences preferences) {
        return new IntPreference(preferences, "debug_network_env", 0);
    }
}
