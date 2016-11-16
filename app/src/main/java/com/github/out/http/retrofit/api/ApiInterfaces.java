package com.github.out.http.retrofit.api;


import com.github.out.bean.BaseEntity;
import com.github.out.http.HttpContents;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by mzp on 2016/9/6.
 */
public interface ApiInterfaces
{
    //登录
    @FormUrlEncoded
    @POST(HttpContents.API_LOGIN)
    Observable<BaseEntity> toLogin(@Field("mobile") String mobile, @Field("password") String password);

    //注册
    @FormUrlEncoded
    @POST(HttpContents.API_REGISTER)
    Observable<BaseEntity> toRegister(@Field("mobile") String mobile, @Field("password") String password);

    //忘记密码
    @FormUrlEncoded
    @POST(HttpContents.API_FEEDBACK_PWD)
    Observable<BaseEntity> toFeedBackPWD(@Field("mobile") String mobile, @Field("password") String password);
}
