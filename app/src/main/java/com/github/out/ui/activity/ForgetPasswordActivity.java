package com.github.out.ui.activity;

import android.widget.Button;
import android.widget.EditText;

import com.github.out.R;
import com.github.out.bean.UserInfos;
import com.github.out.presenter.ForgetPwdPresenter;
import com.github.out.ui.base.BaseActivity;
import com.github.out.view.ForgetPwdView;

/**
 * Created by mzp on 2016/11/17.
 */

public class ForgetPasswordActivity extends BaseActivity implements ForgetPwdView
{
    private ForgetPwdPresenter forgetPwdPresenter;

    private EditText etUserName;
    private EditText etPassword;
    private Button btRegister;

    @Override
    protected int getContentViewLayoutID()
    {
        return R.layout.activity_forget_pwd;
    }

    @Override
    protected void initViewsAndEvents()
    {
        forgetPwdPresenter = new ForgetPwdPresenter(this, this);

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
    public void chengPwdSuccess(UserInfos userInfos)
    {

    }
}
