/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package philippbugayevskiy.example.com.presentation.di.components;


import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Component;
import philippbugayevskiy.example.com.domain.executor.PostExecutionThread;
import philippbugayevskiy.example.com.domain.executor.ThreadExecutor;
import philippbugayevskiy.example.com.domain.repository.MetadataRepository;
import philippbugayevskiy.example.com.presentation.debug.DebugAppContainer;
import philippbugayevskiy.example.com.presentation.di.modules.ApplicationModule;
import philippbugayevskiy.example.com.presentation.di.modules.DebugModule;
import philippbugayevskiy.example.com.presentation.di.modules.NetworkModule;
import philippbugayevskiy.example.com.presentation.di.modules.RepositoryModule;
import philippbugayevskiy.example.com.presentation.view.BaseActivity;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = {ApplicationModule.class,
                      NetworkModule.class,
                      RepositoryModule.class,
                      DebugModule.class
})
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    //Exposed to sub-graphs.
    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    MetadataRepository provideMetadataRepository();

    DebugAppContainer provideDebugContainer();

    SharedPreferences provideSharedPreferences();
}
