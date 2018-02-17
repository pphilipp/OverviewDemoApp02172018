package philippbugayevskiy.example.com.data.datasource;


import com.amatkivskiy.result.Result;

import java.util.List;

import io.reactivex.Observable;

import philippbugayevskiy.example.com.data.entity.ReEntity;
import philippbugayevskiy.example.com.data.net.MobileApi;
import philippbugayevskiy.example.com.presentation.view.preferences.IntPreference;

import static philippbugayevskiy.example.com.data.datasource.verifier.Verifiers.verifyResponseAndFetchPropertiesList;

public class DefaultMetadataDataSource {// TODO: 2/17/18 Add interface
    MobileApi restApi;
    String metadataHost;
    IntPreference endpointEnvPrefs;

    public DefaultMetadataDataSource(MobileApi restApi, String metadataHost, IntPreference endpointEnvPrefs) {
        this.restApi = restApi;
        this.metadataHost = metadataHost;
        this.endpointEnvPrefs = endpointEnvPrefs;
    }

    public Observable<Result<List<ReEntity>, Throwable>> getPropertiesByPageNumber(int pageNumber, String order) {
        return restApi.getPropertiesByPageNumber(metadataHost, Constants.API_ENDPOINT_PROPERTIES,
                Integer.toString(pageNumber), order)
                      .cache()
                      .map(verifyResponseAndFetchPropertiesList());
    }
}
