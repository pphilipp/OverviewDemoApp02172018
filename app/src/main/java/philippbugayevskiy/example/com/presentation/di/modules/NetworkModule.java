package philippbugayevskiy.example.com.presentation.di.modules;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import philippbugayevskiy.example.com.data.net.ConnectivityInterceptor;
import philippbugayevskiy.example.com.data.net.MobileApi;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    // This host will be overladen by the absolute path of every request. See Retrofit.Builder.baseUrl(HttpUrl)
    public static final String BASIC_SDK_URL_SCHEME = "https://";
    public static final String SDK_HOST_PLACEHOLDER = "any";
    public static final String RETROFIT_BASE_URL_PLACEHOLDER = BASIC_SDK_URL_SCHEME + SDK_HOST_PLACEHOLDER;

    @Provides
    OkHttpClient providesOkHttpClient(Context context) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder().addInterceptor(interceptor)
                                         .addInterceptor(new ConnectivityInterceptor(context))
                                         .build();
    }

    @Provides
    Gson providesGson() {
        return new GsonBuilder().create();
    }

    @Provides
    Retrofit providesRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder().baseUrl(RETROFIT_BASE_URL_PLACEHOLDER)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    MobileApi providesOvpApi(Retrofit retrofit) {
        return retrofit.create(MobileApi.class);
    }
}
