package philippbugayevskiy.example.com.data.datasource.verifier;


import android.support.annotation.NonNull;

import com.amatkivskiy.result.Result;

import java.util.List;

import philippbugayevskiy.example.com.data.entity.PropertiesEntity;
import philippbugayevskiy.example.com.data.entity.ReEntity;
import philippbugayevskiy.example.com.data.exception.MissingMetaDataException;
import io.reactivex.functions.Function;
import retrofit2.HttpException;
import retrofit2.Response;

public class Verifiers {

    @NonNull
    public static Function<Response<PropertiesEntity>, Result<List<ReEntity>, Throwable>> verifyResponseAndFetchPropertiesList() {
        return response -> {
            if (!response.isSuccessful()) {
                return Result.failure(new HttpException(response));
            }

            PropertiesEntity entities = response.body();
            if (entities != null && entities.isReEntityListNotEmpty()) {
                return Result.success(entities.getRes());
            }

            return Result.failure(new MissingMetaDataException());
        };
    }
}
