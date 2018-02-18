package test.example.com.utils;

import com.amatkivskiy.result.Result;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class TestUtils {
  public static MockWebServer setupMockWebServer() throws IOException {
    MockWebServer server = new MockWebServer();
    server.start();

    return server;
  }

  public static String mockServerUrlWithoutScheme(MockWebServer mockWebServer) {
    HttpUrl url = mockWebServer.url("");
    return url.host() + ":" + url.port();
  }

  public static String mockServerUrlWithScheme(MockWebServer mockWebServer) {
    return mockWebServer.url("").toString();
  }

  public static MockResponse createMockedResponse(String fileName) throws IOException {
    Buffer buffer = new Buffer().readFrom(ClassLoader.getSystemClassLoader()
                                                     .getResourceAsStream(fileName));
    return new MockResponse().setBody(buffer);
  }

  public static MockResponse createStringMockedResponse(String response) throws IOException {
    return new MockResponse().setBody(response);
  }

  public static HttpUrl getRequestUrl(MockWebServer webServer, RecordedRequest request) throws InterruptedException {
    String path = request.getPath();

    HttpUrl requestUrl = new HttpUrl.Builder().host(webServer.getHostName())
                                              .port(webServer.getPort())
                                              .scheme("https")
                                              .build();

    return HttpUrl.parse(requestUrl + path);
  }

  public static String getRequestBody(RecordedRequest request) {
    return request.getBody()
                  .readString(Charset.defaultCharset());
  }

  @SuppressWarnings("PMD.SystemPrintln") public static HttpLoggingInterceptor getLoggingInterceptor() {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
      @Override
      public void log(String message) {
        System.out.println(message);
      }
    });
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    return interceptor;
  }

  public static <V, E> void assertSuccessfulNonEmptyResult(Result<V, E> result) {
    assertThat(result.toString(), result.isSuccess(), is(true));
    assertThat(result.toString(), result.isEmpty(), is(false));
    assertThat(result.toString(), result.value(), is(notNullValue()));
  }

  public static <V, E> void assertSuccessfulEmptyResult(Result<V, E> result) {
    assertThat(result.toString(), result.isSuccess(), is(true));
    assertThat(result.toString(), result.isEmpty(), is(true));
    assertThat(result.toString(), result.value(), is(nullValue()));
  }

  public static void assertFailureNonEmptyResult(Result<?, ?> result) {
    assertThat(result.toString(), result.isSuccess(), is(false));
    assertThat(result.toString(), result.isEmpty(), is(false));
    assertThat(result.toString(), result.error(), is(notNullValue()));
  }

  public static Retrofit createRetrofit(OkHttpClient client, String baseUrl) {
    return new Retrofit.Builder().baseUrl(baseUrl)
                                 .addConverterFactory(GsonConverterFactory.create())
                                 .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                 .client(client)
                                 .build();
  }

}
