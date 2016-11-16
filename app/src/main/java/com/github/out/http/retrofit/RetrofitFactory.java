package com.github.out.http.retrofit;

import com.github.out.http.HttpContents;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory
{
    private static Retrofit retrofit;

    private RetrofitFactory()
    {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(HttpContents.DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        /**
         * addConverterFactory添加Gson解析器
         * addCallAdapterFactory(RxJavaCallAdapterFactory.create())后调用service的返回值为Observable
         */
        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(HttpContents.DOMAIN_URL)
                .build();
    }

    /**
     * 单例模式创建实例
     * 懒汉式单例模式需要加 `synchronized`
     * @return
     */
    public synchronized static Retrofit getInstance()
    {
        if (null == retrofit)
        {
            new RetrofitFactory();
        }

        return retrofit;
    }
}
