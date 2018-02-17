package philippbugayevskiy.example.com.data.datasource;


import philippbugayevskiy.example.com.data.net.MobileApi;
import philippbugayevskiy.example.com.presentation.view.preferences.IntPreference;

public class DefaultMetadataDataSource {// TODO: 2/17/18 Add interface
    MobileApi restApi;
    String metadataHost;
    IntPreference endpointEnvPrefs;

    public DefaultMetadataDataSource(MobileApi restApi, String metadataHost, IntPreference endpointEnvPrefs) {
        this.restApi = restApi;
        this.metadataHost = metadataHost;
        this.endpointEnvPrefs = endpointEnvPrefs;
    }

    // TODO: 2/17/18 create a getters for Usecase data sorces. 
}
