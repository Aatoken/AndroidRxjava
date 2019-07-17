package com.atoken.study.retrofit;



import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Author Aatoken
 * Date 2019/7/16 17:27
 * Description
 */
public class NetworkService {

    private NetworkInterface mService;
    private static final int TIME_OUT = 60;
    private static final String BASE_URL = "http://192.168.96.2:1314/RestServer/api/";

    private NetworkService() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                //http://192.168.1.9:1314/RestServer/api/test.txt
                .baseUrl(BASE_URL)
                .client(client)
                //String解析器
                .addConverterFactory(ScalarsConverterFactory.create())
                //Json解析器
                .addConverterFactory(GsonConverterFactory.create())
                //RxJava
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        mService = retrofit.create(NetworkInterface.class);

    }


    private static class Holder {
        private static final NetworkService INSTANCE = new NetworkService();
    }

    public static NetworkInterface getInstance() {
        return Holder.INSTANCE.mService;
    }


    public static  final class NetworkTransfoemer<T> implements ObservableTransformer<T,T>
    {
        @Override
        public ObservableSource<T> apply(Observable<T> upstream) {
            return upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    }

}
