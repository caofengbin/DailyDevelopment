package com.cfb.myprocess;

import com.squareup.javapoet.ClassName;

/**
 * Created by fengbincao on 2017/1/5.
 */

public class TypeUtil {
    public static final ClassName ONCLICK_LISTENER = ClassName.get("android.view", "View", "OnClickListener");
    public static final ClassName ANDROID_VIEW = ClassName.get("android.view", "View");
    public static final ClassName FINDER = ClassName.get("com.cfb.myapi.finder", "Finder");
    public static final ClassName INJECTOR = ClassName.get("com.cfb.myapi", "Injector");


}
