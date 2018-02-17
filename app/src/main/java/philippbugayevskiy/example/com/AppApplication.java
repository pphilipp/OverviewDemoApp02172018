/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package philippbugayevskiy.example.com;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Util;
import com.squareup.leakcanary.LeakCanary;
import philippbugayevskiy.example.com.presentation.di.components.ApplicationComponent;
import philippbugayevskiy.example.com.presentation.di.components.DaggerApplicationComponent;
import philippbugayevskiy.example.com.presentation.di.modules.ApplicationModule;
import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

public class AppApplication extends Application {
    protected String userAgent;
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        userAgent = Util.getUserAgent(this, "ExoPlayerDemo");
        Fabric.with(this, new Crashlytics());

        this.initializeTimber();
        this.initializeInjector();
        this.initializeLeakDetection();
    }

    private void initializeTimber() {
        Timber.plant(new Timber.DebugTree());
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }

    // TODO: 1/31/18 move to injections
    public DataSource.Factory buildDataSourceFactory(DefaultBandwidthMeter bandwidthMeter) {
        return new DefaultDataSourceFactory(this, bandwidthMeter,
                buildHttpDataSourceFactory(bandwidthMeter));
    }

    public HttpDataSource.Factory buildHttpDataSourceFactory(DefaultBandwidthMeter bandwidthMeter) {
        return new DefaultHttpDataSourceFactory(userAgent, bandwidthMeter, DefaultHttpDataSource.DEFAULT_CONNECT_TIMEOUT_MILLIS,
                DefaultHttpDataSource.DEFAULT_READ_TIMEOUT_MILLIS, true);
    }

    public boolean useExtensionRenderers() {
        return BuildConfig.FLAVOR.equals("withExtensions");
    }

}
