package test.example.com.data.repository;

import com.amatkivskiy.result.Result;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Observable;
import test.example.com.data.datasource.DefaultMetadataDataSource;
import test.example.com.data.entity.ResPropertyEntity;
import test.example.com.domain.repository.MetadataRepository;

@Singleton
public class DefaultMetadataRepository implements MetadataRepository {
    DefaultMetadataDataSource dataSource;

    public DefaultMetadataRepository(DefaultMetadataDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Observable<Result<List<ResPropertyEntity>, Throwable>> getPropertiesByPageNumber(int pageNumber, String order) {
        return dataSource.getPropertiesByPageNumber(pageNumber, order);
    }
}
