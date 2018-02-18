package test.example.com.data.datasource;


import com.amatkivskiy.result.Result;

import java.util.List;

import io.reactivex.Observable;

import test.example.com.data.entity.ResPropertyEntity;
import test.example.com.data.net.MobileApi;
import test.example.com.presentation.view.preferences.IntPreference;
import test.example.com.data.datasource.verifier.Verifiers;

public class DefaultMetadataDataSource {// TODO: 2/17/18 Add interface
    MobileApi restApi;
    String metadataHost;
    IntPreference endpointEnvPrefs;

    public DefaultMetadataDataSource(MobileApi restApi, String metadataHost, IntPreference endpointEnvPrefs) {
        this.restApi = restApi;
        this.metadataHost = metadataHost;
        this.endpointEnvPrefs = endpointEnvPrefs;
    }

    public Observable<Result<List<ResPropertyEntity>, Throwable>> getPropertiesByPageNumber(int pageNumber, String order) {
        return restApi.getPropertiesByPageNumber(metadataHost, Constants.API_ENDPOINT_PROPERTIES,
                Integer.toString(pageNumber), order)
                      .cache()
                      .map(Verifiers.verifyResponseAndFetchPropertiesList());
    }
}
