package com.oktacore.android.twitterclient.lib.base;

/**
 * Created by usuario on 16/06/16.
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
