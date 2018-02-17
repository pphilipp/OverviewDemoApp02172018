package philippbugayevskiy.example.com.data.net;

import io.reactivex.Observable;
import philippbugayevskiy.example.com.data.entity.PropertiesEntity;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MobileApi {

    @GET("//{host}/{endpoint}?")
    Observable<Response<PropertiesEntity>> getPropertiesByPageNumber(@Path("host")String metadataHost,
                                                                     @Path("endpoint") String fullEndpoint,
                                                                     @Query("page") String pageNumber,
                                                                     @Query("order") String order);
}
