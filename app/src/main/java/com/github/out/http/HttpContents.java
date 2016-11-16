package com.github.out.http;

/**
 * Created by mzp on 2016/11/15.
 */

public interface HttpContents
{
    /*-----------------------------net config---------------------------------------*/
    //域名
    String DOMAIN_URL = "http://www.oyochou.com/";

    //默认超时时间
    int DEFAULT_TIMEOUT = 10;

    /*----------------------------request code----------------------------------------*/
    //请求状态
    String SUCCESS = "0";
    String ERROR = "1";
    String FAIL = "2";

    /*----------------------------------apis------------------------------------------*/
    //登录
    String API_LOGIN = "/api/?m=api&c=&a=login_do";

    //注册
    String API_REGISTER = "";

    //忘记密码
    String API_FEEDBACK_PWD = "";
}
