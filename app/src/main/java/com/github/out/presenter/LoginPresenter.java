package com.github.out.presenter;

import android.content.Context;
import android.widget.Toast;

import com.github.out.bean.UserInfos;
import com.github.out.http.retrofit.RetrofitFactory;
import com.github.out.http.retrofit.api.ApiInterfaces;
import com.github.out.http.retrofit.api.ApiService;
import com.github.out.listener.SubscriberListener;
import com.github.out.source.AccountDataSource;
import com.github.out.view.LoginView;
import com.google.gson.Gson;


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

    //login data source
    private AccountDataSource accountDataSource;

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

        accountDataSource = new AccountDataSource(context, apiService, apiInterface);
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
     * 请求登录
     */
    public void requestLogin()
    {
        if (null != accountDataSource)
        {
            accountDataSource.toLogin(loginCBListener, loginView.getUserName(), loginView.getPassword());
        }
    }

    /**
     * 请求注册
     */
    public void requestRegitser()
    {
        if (null != accountDataSource)
        {
            accountDataSource.toRegitser(registerCBListener, loginView.getUserName(), loginView.getPassword());
        }
    }

    /**
     * 请求找回密码
     */
    public void requestFeedBackPwd()
    {
        if (null != accountDataSource)
        {
            accountDataSource.toFeedBackPwd(feedBackCBListener, loginView.getUserName(), loginView.getPassword());
        }
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
