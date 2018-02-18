package test.example.com.domain.usecase;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import com.amatkivskiy.result.Result;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import test.example.com.data.datasource.DefaultMetadataDataSource;
import test.example.com.data.entity.ResPropertyEntity;
import test.example.com.data.net.AcceptJsonRequestInterceptor;
import test.example.com.data.net.MobileApi;
import test.example.com.data.repository.DefaultMetadataRepository;
import test.example.com.domain.repository.MetadataRepository;
import test.example.com.presentation.view.preferences.IntPreference;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static test.example.com.utils.TestUtils.assertFailureNonEmptyResult;
import static test.example.com.utils.TestUtils.assertSuccessfulNonEmptyResult;
import static test.example.com.utils.TestUtils.createMockedResponse;
import static test.example.com.utils.TestUtils.createRetrofit;
import static test.example.com.utils.TestUtils.getLoggingInterceptor;
import static test.example.com.utils.TestUtils.getRequestUrl;
import static test.example.com.utils.TestUtils.mockServerUrlWithoutScheme;
import static test.example.com.utils.TestUtils.setupMockWebServer;

public class GetPropertiesByPageNumberUseCaseTests {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private MockWebServer mockWebServer;
    private GetPropertiesByPageNumberUseCase getPropertiesByPageNumberUseCase;


    @Before
    public void setUp() throws Exception {
        mockWebServer = setupMockWebServer();

        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(new AcceptJsonRequestInterceptor())
                                                        .addInterceptor(getLoggingInterceptor())
                                                        .build();
        MobileApi mobileApi = createRetrofit(client, "http://any").create(MobileApi.class);
        String mockedHost = mockServerUrlWithoutScheme(mockWebServer);

        IntPreference prefs = mock(IntPreference.class);
        given(prefs.get()).willReturn(1);

        DefaultMetadataDataSource metadataDataSource = new DefaultMetadataDataSource(mobileApi, mockedHost, prefs);
        MetadataRepository metadataRepository = new DefaultMetadataRepository(metadataDataSource);

        getPropertiesByPageNumberUseCase = new GetPropertiesByPageNumberUseCase(null, null, metadataRepository);
    }

    @Test
    public void testGetPropertiesByPageNumberReturnsCorrectResult() throws Exception {
        mockWebServer.enqueue(createMockedResponse("property/get-properties-by-page-number-success.json"));

        Result<List<ResPropertyEntity>, Throwable> result = getPropertiesByPageNumberUseCase
                .lookForPageNumber(0)
                .getRawObservable()
                .test()
                .assertValueCount(1)
                .assertNoErrors()
                .assertComplete()
                .values()
                .get(0);

        // Assert correct response received
        assertSuccessfulNonEmptyResult(result);

        List<ResPropertyEntity> entity = result.value();

        assertThat(entity.size(), is(20));

        assertThat(entity.get(0).price, is("2,550,000"));
        assertThat(entity.get(0).title, is("Apartment, 2 beds, 3 baths"));
    }

    @Test
    public void testGetPropertiesByPageNumberOnHttpErrorReturnsFailure() throws Exception {
        mockWebServer.enqueue(createMockedResponse("").setResponseCode(400));

        Result<List<ResPropertyEntity>, Throwable> result = getPropertiesByPageNumberUseCase
                .lookForPageNumber(0)
                .getRawObservable()
                .test()
                .assertValueCount(1)
                .assertNoErrors()
                .assertComplete()
                .values()
                .get(0);

        // Assert correct response received
        assertFailureNonEmptyResult(result);

        assertThat(result.error(), instanceOf(retrofit2.HttpException.class));
        assertThat(result.error().getMessage(), CoreMatchers.is("HTTP 400 Client Error"));
    }

}
