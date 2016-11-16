package com.github.out.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.out.R;
import com.github.out.bean.UserInfos;
import com.github.out.presenter.LoginPresenter;
import com.github.out.ui.base.BaseActivity;
import com.github.out.view.LoginView;

/**
 * Created by mzp on 2016/9/6.
 */
public class LoginActivity extends BaseActivity implements LoginView, View.OnClickListener
{
    private LoginPresenter loginPresenter;

    private EditText etUserName;
    private EditText etPassword;
    private Button btLogin;

    @Override
    protected int getContentViewLayoutID()
    {
        return R.layout.activity_login;
    }

    @Override
    protected void initViewsAndEvents()
    {
        etUserName = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btLogin = (Button) findViewById(R.id.bt_login);

        btLogin.setOnClickListener(this);

        loginPresenter = new LoginPresenter(this, this);
    }

    @Override
    protected TranslateMode getTranslateMode()
    {
        return TranslateMode.RIGHT;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.bt_login:
                loginPresenter.loginPresenter();
        }
    }

    @Override
    public String getUserName()
    {
        return etUserName.getText().toString().trim();
    }

    @Override
    public String getPassword()
    {
        return etPassword.getText().toString().trim();
    }

    @Override
    public void loginSuccess(UserInfos userInfos)
    {
        Toast.makeText(LoginActivity.this, "requestSuccess" + userInfos.getMobile(), Toast.LENGTH_SHORT).show();
    }
}
