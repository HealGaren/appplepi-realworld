package com.example.retrofit.api;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RealWorldClient {
    private static final String baseUrl = "https://conduit.productionready.io/";

    public static String token;
    private static final String baseUrl = "https://conduit.productionready.io/";

    public static ApiService getApiService() {
        return getInstance().create(ApiService.class);
    }

    private static Retrofit getInstance() {

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                if (token != null) {

                    Request request = chain.request().newBuilder()
                            .addHeader("Authorization", "Token " + token)
                            .build();
                    return chain.proceed(request);
                } else
                    return chain.proceed(chain.request());

            }
        };

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client);

        return builder.build();
    }
}
