package com.github.out.view;


import com.github.out.bean.UserInfos;

/**
 * Created by mzp on 2016/9/13.
 */
public interface RegisterView
{
    String getUserName();

    String getPassword();

    void registerSuccess(UserInfos userInfos);
}
