package com.github.out.presenter;

import android.content.Context;
import android.widget.Toast;

import com.github.out.bean.UserInfos;
import com.github.out.http.retrofit.HttpRequestSubscriber;
import com.github.out.http.retrofit.RetrofitFactory;
import com.github.out.http.retrofit.api.ApiInterfaces;
import com.github.out.http.retrofit.api.ApiService;
import com.github.out.listener.SubscriberListener;
import com.github.out.view.LoginView;
import com.google.gson.Gson;

import rx.Observable;


/**
 * Login page control
 */
public class LoginPresenter
{
    private final static String TAG = "RetrofitRxxJava";

    private Context context;

    private LoginView loginView;

    private ApiService apiService;

    private ApiInterfaces apiInterface;

    /**
     * initialize
     *
     * @param context   must from activity
     * @param loginView
     */
    public LoginPresenter(final Context context, final LoginView loginView)
    {
        this.context = context;
        this.loginView = loginView;

        initUtils();
    }

    /**
     * initialize function class
     */
    private void initUtils()
    {
        apiInterface = RetrofitFactory.getInstance().create(ApiInterfaces.class);

        apiService = new ApiService();
    }

    /**
     * 登录
     */
    public void toLogin()
    {
        if (null == apiInterface || null == apiService)
        {
            initUtils();
        }

        Observable observable = apiInterface.toLogin(loginView.getUserName(), loginView.getPassword());

        apiService.ApiRequest(new HttpRequestSubscriber(context, loginCBListener), observable);
    }

    /**
     * 注册
     */
    public void toRegitser()
    {
        if (null == apiInterface || null == apiService)
        {
            initUtils();
        }

        Observable observable = apiInterface.toRegister(loginView.getUserName(), loginView.getPassword());

        apiService.ApiRequest(new HttpRequestSubscriber(context, registerCBListener), observable);
    }

    /**
     * 忘记密码
     */
    public void toFeedBackPwd()
    {
        if (null == apiInterface || null == apiService)
        {
            initUtils();
        }

        Observable observable = apiInterface.toFeedBackPWD(loginView.getUserName(), loginView.getPassword());

        apiService.ApiRequest(new HttpRequestSubscriber(context, feedBackCBListener), observable);
    }

    //登录结果回调
    private SubscriberListener loginCBListener = new SubscriberListener()
    {
        @Override
        public void onSuccess(String dataMsg)
        {
            loginView.loginSuccess(new Gson().fromJson(dataMsg, UserInfos.class));
        }

        @Override
        public void onError(String errorMsg)
        {
            Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();
        }
    };

    //注册结果回调
    private SubscriberListener registerCBListener = new SubscriberListener()
    {
        @Override
        public void onSuccess(String dataMsg)
        {
        }

        @Override
        public void onError(String errorMsg)
        {
            Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();
        }
    };

    //忘记密码结果回调
    private SubscriberListener feedBackCBListener = new SubscriberListener()
    {
        @Override
        public void onSuccess(String dataMsg)
        {
        }

        @Override
        public void onError(String errorMsg)
        {
            Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();
        }
    };
}
