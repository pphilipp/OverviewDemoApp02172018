package philippbugayevskiy.example.com.domain.repository;

import com.amatkivskiy.result.Result;

import java.util.List;

import io.reactivex.Observable;
import philippbugayevskiy.example.com.data.entity.PropertiesEntity;
import philippbugayevskiy.example.com.data.entity.ReEntity;

public interface MetadataRepository {

    Observable<Result<List<ReEntity>,Throwable>> getPropertiesByPageNumber(int pageNumber);

}
