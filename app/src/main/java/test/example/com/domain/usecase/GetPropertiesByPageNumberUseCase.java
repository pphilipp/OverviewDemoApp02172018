package test.example.com.domain.usecase;

import com.amatkivskiy.result.Result;

import java.util.List;

import io.reactivex.Observable;
import test.example.com.data.datasource.Constants;
import test.example.com.data.entity.ResPropertyEntity;
import test.example.com.domain.executor.PostExecutionThread;
import test.example.com.domain.executor.ThreadExecutor;
import test.example.com.domain.repository.MetadataRepository;
import test.example.com.presentation.utils.Preconditions;

public class GetPropertiesByPageNumberUseCase extends UseCase<List<ResPropertyEntity>, Throwable>{
    private MetadataRepository metadataRepository;
    private int pageNumber;
    private String order = Constants.EMPTY_STRING;

    public GetPropertiesByPageNumberUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                                            MetadataRepository metadataRepository) {
        super(threadExecutor, postExecutionThread);

        this.metadataRepository = metadataRepository;
    }

    public GetPropertiesByPageNumberUseCase lookForPageNumber(int pageNumber) {
        Preconditions.checkNotNull(pageNumber, "pageNumber can't be null or empty");
        this.pageNumber = pageNumber;

        return this;
    }

    public GetPropertiesByPageNumberUseCase lookForOrder(String order) {
        this.order = order;

        return this;
    }

    @Override
    public Observable<Result<List<ResPropertyEntity>, Throwable>> getRawObservable() {
        return metadataRepository.getPropertiesByPageNumber(pageNumber, order);
    }
}
