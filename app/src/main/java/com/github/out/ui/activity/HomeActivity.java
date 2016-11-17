package com.github.out.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.github.out.R;
import com.github.out.ui.base.BaseActivity;
import com.github.out.ui.fragment.DiscoveryFragment;
import com.github.out.ui.fragment.HomeFragment;
import com.github.out.ui.fragment.MessageFragment;

import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends BaseActivity
{
    private FrameLayout flHome;
    private RadioGroup rgHome;
    private RadioButton rbHome;
    private RadioButton rbMsg;
    private RadioButton rbDiscovery;

    private HomeFragment homeFragment;
    private MessageFragment messageFragment;
    private DiscoveryFragment discoveryFragment;

    @Override
    protected int getContentViewLayoutID()
    {
        return R.layout.activity_home;
    }

    @Override
    protected void initViewsAndEvents()
    {

        flHome = (FrameLayout) findViewById(R.id.fl_home_content);
        rbHome = (RadioButton) findViewById(R.id.rb_home);
        rbMsg = (RadioButton) findViewById(R.id.rb_msg);
        rbDiscovery = (RadioButton) findViewById(R.id.rb_discovery);

        rgHome = (RadioGroup) findViewById(R.id.main_radio);
        rgHome.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                changeFragment(checkedId);
            }
        });

        rbHome.setChecked(true);
    }

    private void changeFragment(int checkedId)
    {
        //Fragment事物对象
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        hideAllFragment(ft);

        /**
         * 每次Fragment切换时都需要实时修改标题内容
         */
        switch (checkedId)
        {
            case R.id.rb_home:
                changeToHomeFragment(ft);

                break;

            case R.id.rb_msg:
                changeToInvestFragment(ft);

                break;
            case R.id.rb_discovery:
                changeToCurrentFragment(ft);
                break;
        }

        /**
         * 这里使用commitAllowingStateLoss而不是commit方法是因为
         * 在AccountFragment中点击登录是调用了onSaveInstanceState方法,这时候调用commit方法会报错
         * 所以使用commitAllowingStateLoss方法,但是当activity再次被恢复时commit之后的状态将丢失
         */
        ft.commitAllowingStateLoss();
    }

    /**
     * 在切换Fragment时隐藏所有的Fragment
     */
    private void hideAllFragment(FragmentTransaction ft)
    {
        if (null != ft)
        {
            if (null != homeFragment)
            {
                ft.hide(homeFragment);
            }

            if (null != messageFragment)
            {
                ft.hide(messageFragment);
            }

            if (null != discoveryFragment)
            {
                ft.hide(discoveryFragment);
            }
        }
    }

    /**
     * 切换到BjbFragment
     */
    private void changeToHomeFragment(FragmentTransaction ft)
    {
        if (null != ft)
        {

            if (null == homeFragment)
            {
                homeFragment = new HomeFragment();
            }

            if (homeFragment.isAdded())
            {
                ft.show(homeFragment);
            } else
            {
                ft.replace(R.id.fl_home_content, homeFragment);
            }
        }
    }

    private void changeToCurrentFragment(FragmentTransaction ft)
    {
        if (null == messageFragment)
        {
            messageFragment = new MessageFragment();
        }

        if (messageFragment.isAdded())
        {
            ft.show(messageFragment);
        } else
        {
            ft.replace(R.id.fl_home_content, messageFragment);
        }
    }


    /**
     * 切换到InvestFragment
     */
    private void changeToInvestFragment(FragmentTransaction ft)
    {
        /**
         * 由于切换到InvestFragment时经常会报Have add错误所以每次切换到InvestFragment
         * 时新创建InvestFragment
         */
        discoveryFragment = new DiscoveryFragment();

        if (discoveryFragment.isAdded())
        {
            ft.show(discoveryFragment);
        } else
        {
            ft.replace(R.id.fl_home_content, discoveryFragment);
        }
    }

    private static Boolean isQuit = false;
    private Timer timer = new Timer();

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            if (isQuit == false)
            {
                isQuit = true;
                Toast.makeText(getBaseContext(), "再次点击确定退出软件", Toast.LENGTH_SHORT).show();
                TimerTask task = new TimerTask()
                {
                    @Override
                    public void run()
                    {
                        isQuit = false;
                    }
                };
                timer.schedule(task, 2000);
            } else
            {
                finish();
            }
        }

        return false;
    }
}
