package com.github.out.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.out.FirstProjectApplication;
import com.github.out.R;

/**
 * Created by mzp on 2016/11/15.
 */

public abstract class BaseActivity extends AppCompatActivity
{
    /**
     * global Context
     */
    public Context mContext;

    /**
     * translate enum
     */
    public enum TranslateMode
    {
        NULL, LEFT, RIGHT
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mContext = FirstProjectApplication.getContext();

        //活动切换动画
        switch (getTranslateMode())
        {
            case LEFT:
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
                break;

            case RIGHT:
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
        }

        //绑定布局文件
        if (getContentViewLayoutID() != 0)
        {
            setContentView(getContentViewLayoutID());
        }else
        {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }

        //初始化控件和事件
        initViewsAndEvents();
    }

    /**
     * bind layout resource file
     */
    protected abstract int getContentViewLayoutID();

    /**
     * init views and events
     */
    protected abstract void initViewsAndEvents();

    /**
     * get translate mode
     * default : NULL
     */
    protected TranslateMode getTranslateMode()
    {
        return TranslateMode.NULL;
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
