package com.cfb.myapi.finder;

import android.content.Context;
import android.view.View;

/**
 * Created by fengbincao on 2017/1/5.
 */

public class ViewFinder implements Finder {

    @Override
    public Context getContext(Object source) {
        return ((View) source).getContext();
    }

    @Override
    public View findView(Object source, int id) {
        return ((View) source).findViewById(id);
    }
}
