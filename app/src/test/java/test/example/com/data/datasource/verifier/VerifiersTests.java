package test.example.com.data.datasource.verifier;

import com.amatkivskiy.result.Result;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Collections;
import java.util.List;
import test.example.com.data.entity.PropertiesEntity;
import test.example.com.data.entity.ResPropertyEntity;
import io.reactivex.Observable;
import retrofit2.Response;
import test.example.com.data.exception.MissingMetaDataException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class VerifiersTests {

    @Test
    public void verifyResponseAndFetchPropertiesListSuccess() throws Exception {
        PropertiesEntity entity = mock(PropertiesEntity.class);
        given(entity.isReEntityListNotEmpty()).willReturn(true);
        given(entity.getRes()).willReturn(Collections.emptyList());
        Result<List<ResPropertyEntity>, Throwable> result = Observable.just(Response.success(entity))
                                                                      .map(Verifiers.verifyResponseAndFetchPropertiesList())
                                                                      .test()
                                                                      .assertValueCount(1)
                                                                      .assertNoErrors()
                                                                      .assertComplete()
                                                                      .values()
                                                                      .get(0);

        assertTrue(result.isSuccess());
        assertThat(result.value().size(), is(0));
    }

    @Test
    public void verifyResponseAndFetchPropertiesListMissingDataError() throws Exception {
        Result<List<ResPropertyEntity>, Throwable> result = Observable.just(Response.success(new PropertiesEntity()))
                                                                      .map(Verifiers.verifyResponseAndFetchPropertiesList())
                                                                      .test()
                                                                      .assertValueCount(1)
                                                                      .assertNoErrors()
                                                                      .assertComplete()
                                                                      .values()
                                                                      .get(0);

        assertThat(result.error(), instanceOf(MissingMetaDataException.class));
    }
}