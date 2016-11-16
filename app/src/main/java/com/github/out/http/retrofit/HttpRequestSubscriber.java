package com.github.out.http.retrofit;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.github.out.R;
import com.github.out.bean.BaseEntity;
import com.github.out.http.HttpContents;
import com.github.out.listener.SubscriberListener;
import com.github.out.weight.progress.ProgressDialogHandler;
import com.google.gson.Gson;

import rx.Subscriber;

/**
 * 请求订阅者
 */
public class HttpRequestSubscriber extends Subscriber<BaseEntity> implements ProgressDialog.OnCancelListener
{
    private Context context;

    private ProgressDialogHandler pdh;

    private SubscriberListener onNextListener;

    /**
     * 默认订阅者, 含有默认请求弹窗
     * @param context
     * @param onNextListener
     */
    public HttpRequestSubscriber(Context context, SubscriberListener onNextListener)
    {
        this.context = context;
        this.onNextListener = onNextListener;

        pdh = ProgressDialogHandler.getInstance(context, this);
    }

    /**
     * 不含请求弹窗, 适用于列表刷新/加载, 防止重复
     * @param context
     * @param onNextListener
     * @param isShowProgress
     */
    public HttpRequestSubscriber(Context context, SubscriberListener onNextListener, boolean isShowProgress)
    {
        this.context = context;
        this.onNextListener = onNextListener;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        if (pdh != null)
        {
            pdh.show();
        }
    }

    @Override
    public void onCompleted()
    {
        if (pdh != null)
        {
            pdh.dismiss();
        }
    }

    @Override
    public void onError(Throwable e)
    {
        if (pdh != null)
        {
            pdh.dismiss();
        }
        Toast.makeText(context, context.getString(R.string.net_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNext(BaseEntity baseEntity)
    {
        //对请求是否得到正确相应做出判断
        if (null != onNextListener)
        {
            if (HttpContents.SUCCESS.equals(baseEntity.getCode()))
            {
                Gson gson = new Gson();
                String infoStr = gson.toJson(baseEntity.getData());
                onNextListener.onSuccess(infoStr);
            }
            else
            {
                onNextListener.onSuccess(baseEntity.getMsg());
            }
        }
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancel(DialogInterface dialog)
    {
        if (this.isUnsubscribed())
        {
            this.unsubscribe();
        }
    }
}
