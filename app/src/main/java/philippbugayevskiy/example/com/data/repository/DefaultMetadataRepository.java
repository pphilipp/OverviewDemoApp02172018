package philippbugayevskiy.example.com.data.repository;

import javax.inject.Singleton;

import philippbugayevskiy.example.com.data.datasource.DefaultMetadataDataSource;
import philippbugayevskiy.example.com.domain.repository.MetadataRepository;

@Singleton
public class DefaultMetadataRepository implements MetadataRepository {
    DefaultMetadataDataSource dataSource;

    public DefaultMetadataRepository(DefaultMetadataDataSource dataSource) {
        this.dataSource = dataSource;
    }


}
