package be.gershon_lehrer.gettingcarassistance.service;


import okhttp3.OkHttpClient;
import retrofit2.*;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gershonlehrer on 06/10/16.
 */

public class ServiceFactory {

    /**
     * Creates a retrofit service from an arbitrary class (clazz)
    * @return retrofit service with defined endpoint
     */

    //source: https://github.com/ruler88/GithubDemo/blob/master/app/src/main/java/app/service/ServiceFactory.java
//    public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {
//        final RestAdapter restAdapter = new RestAdapter.Builder()
//                .setEndpoint(endPoint)
//                .build();
//        T service = restAdapter.create(clazz);
//
//        return service;
//    }

//source https://inthecheesefactory.com/blog/retrofit-2.0/en and
    public static GarageService buildRetrofitService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GarageService.SERVICE_ENDPOINT)
                // Data converter
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();

        return retrofit.create(GarageService.class);
    }
}