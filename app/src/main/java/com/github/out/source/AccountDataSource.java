package com.github.out.source;

import android.content.Context;

import com.github.out.http.retrofit.HttpRequestSubscriber;
import com.github.out.http.retrofit.api.ApiInterfaces;
import com.github.out.http.retrofit.api.ApiService;
import com.github.out.listener.SubscriberListener;

import rx.Observable;

/**
 * Created by mzp on 2016/11/16.
 */

public class AccountDataSource
{
    private Context context;

    private ApiService apiService;

    private ApiInterfaces apiInterface;

    public AccountDataSource(Context context, ApiService apiService, ApiInterfaces apiInterface)
    {
        this.context = context;
        this.apiService = apiService;
        this.apiInterface = apiInterface;
    }

    /**
     * 去登录
     */
    public void toLogin(SubscriberListener loginCBListener, String userName, String pwd)
    {
        if (null != apiInterface && null != apiService)
        {
            Observable observable = apiInterface.toLogin(userName, pwd);

            apiService.ApiRequest(new HttpRequestSubscriber(context, loginCBListener), observable);
        }
    }

    /**
     * 去注册
     */
    public void toRegitser(SubscriberListener registerCBListener, String userName, String pwd)
    {
        if (null != apiInterface && null != apiService)
        {
            Observable observable = apiInterface.toRegister(userName, pwd);

            apiService.ApiRequest(new HttpRequestSubscriber(context, registerCBListener), observable);
        }
    }

    /**
     * 去找回密码
     */
    public void toFeedBackPwd(SubscriberListener feedBackCBListener, String userName, String pwd)
    {
        if (null != apiInterface && null != apiService)
        {
            Observable observable = apiInterface.toFeedBackPWD(userName, pwd);

            apiService.ApiRequest(new HttpRequestSubscriber(context, feedBackCBListener), observable);
        }
    }
}
