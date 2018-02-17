package philippbugayevskiy.example.com.domain.usecase;

import com.amatkivskiy.result.Result;

import java.util.List;

import io.reactivex.Observable;
import philippbugayevskiy.example.com.data.entity.PropertiesEntity;
import philippbugayevskiy.example.com.data.entity.ReEntity;
import philippbugayevskiy.example.com.domain.executor.PostExecutionThread;
import philippbugayevskiy.example.com.domain.executor.ThreadExecutor;
import philippbugayevskiy.example.com.domain.repository.MetadataRepository;
import philippbugayevskiy.example.com.presentation.utils.Preconditions;

public class GetPropertiesByPageNumberUseCase extends UseCase<List<ReEntity>, Throwable>{
    private MetadataRepository metadataRepository;
    private int pageNumber;
    private String order = "";

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
    public Observable<Result<List<ReEntity>, Throwable>> getRawObservable() {
        return metadataRepository.getPropertiesByPageNumber(pageNumber, order);
    }
}
