package test.example.com.presentation.di.modules;

import dagger.Module;
import dagger.Provides;
import test.example.com.domain.executor.PostExecutionThread;
import test.example.com.domain.executor.ThreadExecutor;
import test.example.com.domain.repository.MetadataRepository;
import test.example.com.domain.usecase.GetPropertiesByPageNumberUseCase;
import test.example.com.presentation.di.PerActivity;

@Module
public class UseCasesModule {

    @Provides
    @PerActivity
    GetPropertiesByPageNumberUseCase provideGetStationsWithEpgUseCase(ThreadExecutor threadExecutor,
                                                                      PostExecutionThread postExecutionThread,
                                                                      MetadataRepository metadataRepository) {
        return new GetPropertiesByPageNumberUseCase(threadExecutor, postExecutionThread, metadataRepository);
    }
}
