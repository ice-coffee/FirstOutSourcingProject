package com.github.out.view;


import com.github.out.bean.UserInfos;

/**
 * Created by mzp on 2016/9/13.
 */
public interface LoginView
{
    String getUserName();

    String getPassword();

    void loginSuccess(UserInfos userInfos);
}
