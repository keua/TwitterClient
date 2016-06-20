package com.oktacore.android.twitterclient.images.di;

import com.oktacore.android.twitterclient.images.ImagesPresenter;
import com.oktacore.android.twitterclient.images.ui.ImagesFragment;
import com.oktacore.android.twitterclient.lib.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by usuario on 18/06/16.
 */
 @Singleton @Component(modules = {LibsModule.class, ImagesModule.class})
public interface ImagesComponent {
    void inject(ImagesFragment fragment);
    ImagesPresenter getPresenter();
}
