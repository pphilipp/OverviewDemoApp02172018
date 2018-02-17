package philippbugayevskiy.example.com.presentation.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import philippbugayevskiy.example.com.BuildConfig;
import philippbugayevskiy.example.com.data.datasource.DefaultMetadataDataSource;
import philippbugayevskiy.example.com.data.net.MobileApi;
import philippbugayevskiy.example.com.data.repository.DefaultMetadataRepository;
import philippbugayevskiy.example.com.domain.repository.MetadataRepository;
import philippbugayevskiy.example.com.presentation.di.NetworkEnv;
import philippbugayevskiy.example.com.presentation.view.preferences.IntPreference;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    MetadataRepository provideMetadataRepository(MobileApi restApi, @NetworkEnv IntPreference networkEnv) {
        DefaultMetadataDataSource dataSource = new DefaultMetadataDataSource(restApi, BuildConfig.HOST,
                networkEnv);

        return new DefaultMetadataRepository(dataSource);
    }

}
