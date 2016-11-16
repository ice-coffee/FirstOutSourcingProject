package com.github.out.presenter;

import android.content.Context;
import android.widget.Toast;

import com.github.out.bean.UserInfos;
import com.github.out.http.retrofit.HttpRequestSubscriber;
import com.github.out.http.retrofit.RetrofitFactory;
import com.github.out.http.retrofit.api.ApiInterface;
import com.github.out.http.retrofit.api.ApiService;
import com.github.out.listener.SubscriberListener;
import com.github.out.view.LoginView;
import com.google.gson.Gson;

import rx.Observable;


/**
 * Login control
 */
public class LoginPresenter
{
    private final static String TAG = "RetrofitRxxJava";

    private Context context;

    private LoginView loginView;

    private ApiService apiService;

    private SubscriberListener onNextListener;

    /**
     * initialize
     * @param context must from activity
     * @param loginView
     */
    public LoginPresenter(final Context context, final LoginView loginView)
    {
        this.context = context;
        this.loginView = loginView;

        apiService = new ApiService();

        //请求成功回调
        onNextListener = new SubscriberListener()
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
    }

    public void loginPresenter()
    {
        ApiInterface apiInterface = RetrofitFactory.getInstance().create(ApiInterface.class);

        Observable observable = apiInterface.toLogin(loginView.getUserName(), loginView.getPassword());

        apiService.ApiRequest(new HttpRequestSubscriber(context, onNextListener), observable);
    }
}
