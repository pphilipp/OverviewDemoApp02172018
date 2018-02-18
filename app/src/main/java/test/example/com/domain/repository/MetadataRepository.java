package test.example.com.domain.repository;

import com.amatkivskiy.result.Result;
import java.util.List;
import io.reactivex.Observable;
import test.example.com.data.entity.ResPropertyEntity;

public interface MetadataRepository {

    Observable<Result<List<ResPropertyEntity>,Throwable>> getPropertiesByPageNumber(int pageNumber, String order);

}
