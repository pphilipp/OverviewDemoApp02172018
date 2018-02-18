package test.example.com.presentation.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import test.example.com.BuildConfig;
import test.example.com.data.datasource.DefaultMetadataDataSource;
import test.example.com.data.net.MobileApi;
import test.example.com.data.repository.DefaultMetadataRepository;
import test.example.com.domain.repository.MetadataRepository;
import test.example.com.presentation.di.NetworkEnv;
import test.example.com.presentation.view.preferences.IntPreference;

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
