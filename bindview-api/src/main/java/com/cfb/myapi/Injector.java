package com.cfb.myapi;

import com.cfb.myapi.finder.Finder;

/**
 * Created by fengbincao on 2017/1/5.
 */

public interface Injector<T> {
    /**
     * @param host   目标
     * @param source 来源
     * @param finder
     */
    void inject(T host, Object source, Finder finder);
}
