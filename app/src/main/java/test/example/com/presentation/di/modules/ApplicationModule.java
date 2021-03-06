package test.example.com.presentation.di.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import test.example.com.AppApplication;
import test.example.com.UIThread;
import test.example.com.data.cache.DefaultCache;
import test.example.com.data.cache.UserCacheImpl;
import test.example.com.data.datasource.DefaultDiskDataStore;
import test.example.com.data.executor.JobExecutor;
import test.example.com.domain.executor.PostExecutionThread;
import test.example.com.domain.executor.ThreadExecutor;


/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
    private final AppApplication application;

    public ApplicationModule(AppApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    DefaultCache provideUserCache(UserCacheImpl userCache) {
        return userCache;
    }

    @Provides
    @Singleton
    DefaultDiskDataStore provideDiskUserDataStore(DefaultCache cache) {
        return new DefaultDiskDataStore(cache);
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
