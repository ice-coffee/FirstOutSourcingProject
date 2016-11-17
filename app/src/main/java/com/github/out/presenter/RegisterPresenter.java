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
import com.github.out.view.RegisterView;
import com.google.gson.Gson;


/**
 * Login page control
 */
public class RegisterPresenter
{
    private final static String TAG = "RetrofitRxxJava";

    private Context context;

    private RegisterView registerView;

    private ApiService apiService;

    private ApiInterfaces apiInterface;

    //login data source
    private AccountDataSource accountDataSource;

    /**
     * initialize
     *
     * @param context   must from activity
     */
    public RegisterPresenter(final Context context, final RegisterView registerView)
    {
        this.context = context;
        this.registerView = registerView;

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
     * 请求注册
     */
    public void requestRegitser()
    {
        if (null != accountDataSource)
        {
            accountDataSource.toRegitser(registerCBListener, registerView.getUserName(), registerView.getPassword());
        }
    }

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
}
