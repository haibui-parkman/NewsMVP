package com.havrylyuk.newsmvp.data.source.remote;

import com.havrylyuk.newsmvp.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Igor Havrylyuk on 27.03.2017.
 */

public class ApiClient {

   private static Retrofit sRetrofit = null;

    public ApiClient() {
    }

    public static Retrofit getClient() {
        if (sRetrofit == null) {
            synchronized (Retrofit.class) {
                if (sRetrofit == null) {
                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
                    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
                    sRetrofit = new Retrofit.Builder()
                            .baseUrl(BuildConfig.BASE_API_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(client)
                            .build();
                }
            }
        }
        return sRetrofit;
    }

}
