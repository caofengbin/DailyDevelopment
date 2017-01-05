package com.cfb.myapi.finder;

import android.app.Activity;
import android.content.Context;
import android.view.View;

/**
 * Created by fengbincao on 2017/1/5.
 */

public class ActivityFinder implements Finder {

    @Override
    public Context getContext(Object source) {
        return (Activity) source;
    }

    @Override
    public View findView(Object source, int id) {
        return ((Activity) source).findViewById(id);
    }
}
