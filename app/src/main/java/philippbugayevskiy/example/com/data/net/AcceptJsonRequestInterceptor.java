package philippbugayevskiy.example.com.data.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * {@link Interceptor} that simply adds request header "Accept: application/json" to every request that it intercepts.
 */
public class AcceptJsonRequestInterceptor implements Interceptor {
    @Override public Response intercept(Chain chain) throws IOException {
        return chain.proceed(chain.request()
                .newBuilder()
                .addHeader("Accept", "application/json")
                .build());
    }
}
