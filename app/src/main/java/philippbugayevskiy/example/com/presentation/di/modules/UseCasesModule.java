package philippbugayevskiy.example.com.presentation.di.modules;

import dagger.Module;
import dagger.Provides;
import philippbugayevskiy.example.com.domain.executor.PostExecutionThread;
import philippbugayevskiy.example.com.domain.executor.ThreadExecutor;
import philippbugayevskiy.example.com.domain.repository.MetadataRepository;
import philippbugayevskiy.example.com.domain.usecase.GetPropertiesByPageNumberUseCase;
import philippbugayevskiy.example.com.presentation.di.PerActivity;

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
