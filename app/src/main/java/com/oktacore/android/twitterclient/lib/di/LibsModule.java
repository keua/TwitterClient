package com.oktacore.android.twitterclient.lib.di;

import android.support.v4.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.oktacore.android.twitterclient.images.ui.ImagesFragment;
import com.oktacore.android.twitterclient.lib.GlideImageLoader;
import com.oktacore.android.twitterclient.lib.GreenRobotEventBus;
import com.oktacore.android.twitterclient.lib.base.EventBus;
import com.oktacore.android.twitterclient.lib.base.ImageLoader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by usuario on 16/06/16.
 */
@Module
public class LibsModule {

    private Fragment fragment;

    public LibsModule(ImagesFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(RequestManager requestManager){
        return new GlideImageLoader(requestManager);
    }

    @Provides
    @Singleton
    RequestManager providesRequestManager(Fragment fragment){
        return  Glide.with(fragment);
    }

    @Provides
    @Singleton
    Fragment providesFragment(){
        return this.fragment;
    }

    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus){
        return new GreenRobotEventBus(eventBus);
    }

    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus(){
        return  org.greenrobot.eventbus.EventBus.getDefault();
    }
}
