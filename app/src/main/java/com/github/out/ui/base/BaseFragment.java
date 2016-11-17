package com.github.out.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mzp on 2016/11/16.
 */

public abstract class BaseFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        if (getContentViewLayoutID() != 0) {
            return inflater.inflate(getContentViewLayoutID(), null);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    /**
     * bind layout resource file
     *
     * @return id of layout resource
     */
    protected abstract int getContentViewLayoutID();
}
