package com.github.out.presenter;

import android.content.Context;
import android.widget.Toast;

import com.github.out.bean.UserInfos;
import com.github.out.http.retrofit.RetrofitFactory;
import com.github.out.http.retrofit.api.ApiInterfaces;
import com.github.out.http.retrofit.api.ApiService;
import com.github.out.listener.SubscriberListener;
import com.github.out.source.AccountDataSource;
import com.github.out.view.ForgetPwdView;
import com.github.out.view.LoginView;
import com.google.gson.Gson;


/**
 * Login page control
 */
public class ForgetPwdPresenter
{
    private final static String TAG = "RetrofitRxxJava";

    private Context context;

    private ForgetPwdView forgetPwdView;

    private ApiService apiService;

    private ApiInterfaces apiInterface;

    //login data source
    private AccountDataSource accountDataSource;

    /**
     * initialize
     *
     * @param context   must from activity
     * @param forgetPwdView
     */
    public ForgetPwdPresenter(final Context context, final ForgetPwdView forgetPwdView)
    {
        this.context = context;
        this.forgetPwdView = forgetPwdView;

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
     * 请求找回密码
     */
    public void requestFeedBackPwd()
    {
        if (null != accountDataSource)
        {
            accountDataSource.toFeedBackPwd(feedBackCBListener, forgetPwdView.getUserName(), forgetPwdView.getPassword());
        }
    }

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
