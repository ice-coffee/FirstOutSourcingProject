package com.github.out.ui.activity;

import android.widget.Button;
import android.widget.EditText;

import com.github.out.R;
import com.github.out.bean.UserInfos;
import com.github.out.presenter.RegisterPresenter;
import com.github.out.ui.base.BaseActivity;
import com.github.out.view.RegisterView;

/**
 * Created by mzp on 2016/11/17.
 */

public class RegisterActivity extends BaseActivity implements RegisterView
{
    private RegisterPresenter registerPresenter;

    private EditText etUserName;
    private EditText etPassword;
    private Button btRegister;

    @Override
    protected int getContentViewLayoutID()
    {
        return R.layout.activity_register;
    }

    @Override
    protected void initViewsAndEvents()
    {
        registerPresenter = new RegisterPresenter(this, this);

        etUserName = (EditText) findViewById(R.id.username);
        etPassword = (EditText) findViewById(R.id.password);
        btRegister = (Button) findViewById(R.id.register);
    }

    @Override
    public String getUserName()
    {
        return etUserName.getText().toString();
    }

    @Override
    public String getPassword()
    {
        return etPassword.getText().toString();
    }

    @Override
    public void registerSuccess(UserInfos userInfos)
    {

    }
}
