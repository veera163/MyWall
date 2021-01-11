package com.frazen.edaftar.api;

import android.text.TextUtils;
import android.util.Log;


import com.frazen.edaftar.BuildConfig;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ramakanth on 20-12-2019.
 */

public class AppServerService {

  //  public static final String baseURL = "http://139.59.87.193:8080/amservices/";

   // public static final String baseURL = "http://157.245.110.240:8080/socialboard/";
  public static final String baseURL = "http://142.93.216.92:8080/socialboard/";

    private static final OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

    private static final Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create());

    public static<S> S createService(Class<S> serviceClass){
        return createService(serviceClass, null);
    }

    public static<S> S createService(Class<S> serviceClass, String clientID, String clientSecret){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        if(BuildConfig.DEBUG){
            okHttpClientBuilder.addInterceptor(logging);
        }

        if(!TextUtils.isEmpty(clientID) && !TextUtils.isEmpty(clientSecret)){
            String authToken = Credentials.basic(clientID,clientSecret);
            Log.e("veera",authToken);
            System.out.println("*************************");
            System.out.println("Auth Token: " + authToken);
            System.out.println("*************************");
            return createService(serviceClass, authToken);
        }

        return createService(serviceClass,null,null);
    }

    public static<S> S createService(Class<S> serviceClass, final String authToken){

        Retrofit retrofit = builder.build();

        if(!TextUtils.isEmpty(authToken)){
            AuthenticationInterceptor interceptor = new AuthenticationInterceptor(authToken);

            if(!okHttpClientBuilder.interceptors().contains(interceptor)){
                okHttpClientBuilder.addNetworkInterceptor((interceptor));

                builder.client(okHttpClientBuilder.build());
                retrofit = builder.build();
            }
        }

        return retrofit.create(serviceClass);
    }
}
