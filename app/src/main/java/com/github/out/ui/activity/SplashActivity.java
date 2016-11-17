package com.github.out.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.view.KeyEvent;

import com.github.out.R;
import com.github.out.ui.base.BaseActivity;

/**
 * Created by mzp on 2016/11/15.
 */

public class SplashActivity extends BaseActivity
{
    @Override
    protected int getContentViewLayoutID()
    {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViewsAndEvents()
    {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                finish();
            }
        }, 3000);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK)
        {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
