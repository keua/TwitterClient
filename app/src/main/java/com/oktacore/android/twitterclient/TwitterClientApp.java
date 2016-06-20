package com.oktacore.android.twitterclient;

import android.app.Application;

import com.oktacore.android.twitterclient.images.di.DaggerImagesComponent;
import com.oktacore.android.twitterclient.images.di.ImagesComponent;
import com.oktacore.android.twitterclient.images.di.ImagesModule;
import com.oktacore.android.twitterclient.images.ui.ImagesFragment;
import com.oktacore.android.twitterclient.images.ui.ImagesView;
import com.oktacore.android.twitterclient.images.ui.adapters.OnItemClickListener;
import com.oktacore.android.twitterclient.lib.di.LibsModule;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

/**
 * Created by usuario on 15/06/16.
 */
public class TwitterClientApp extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        initFabric();
    }

    private void initFabric() {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(BuildConfig.TWITTER_KEY, BuildConfig.TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
    }

    public ImagesComponent getImagesComponent(ImagesFragment fragment, ImagesView view, OnItemClickListener clickListener){
        return DaggerImagesComponent
                .builder()
                .libsModule(new LibsModule(fragment))
                .imagesModule(new ImagesModule(view,clickListener))
                .build();
    }
}
