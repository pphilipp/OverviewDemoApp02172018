package philippbugayevskiy.example.com.data.repository;

import com.amatkivskiy.result.Result;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Observable;
import philippbugayevskiy.example.com.data.datasource.DefaultMetadataDataSource;
import philippbugayevskiy.example.com.data.entity.PropertiesEntity;
import philippbugayevskiy.example.com.data.entity.ReEntity;
import philippbugayevskiy.example.com.domain.repository.MetadataRepository;

@Singleton
public class DefaultMetadataRepository implements MetadataRepository {
    DefaultMetadataDataSource dataSource;

    public DefaultMetadataRepository(DefaultMetadataDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Observable<Result<List<ReEntity>, Throwable>> getPropertiesByPageNumber(int pageNumber) {
        return dataSource.getPropertiesByPageNumber(pageNumber);
    }
}
