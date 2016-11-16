package com.github.out.http.retrofit.api;


import com.github.out.bean.BaseEntity;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by mzp on 2016/9/6.
 */
public interface ApiInterface
{
    @FormUrlEncoded
    @POST(ApiContents.API_LOGIN)
    Observable<BaseEntity> toLogin(@Field("mobile") String mobile, @Field("password") String password);
}
