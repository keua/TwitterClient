package com.oktacore.android.twitterclient.images.di;

import android.widget.ImageView;

import com.oktacore.android.twitterclient.api.CustomTwitterApiClient;
import com.oktacore.android.twitterclient.entities.Image;
import com.oktacore.android.twitterclient.images.ImagesInteractor;
import com.oktacore.android.twitterclient.images.ImagesInteractorImpl;
import com.oktacore.android.twitterclient.images.ImagesPresenter;
import com.oktacore.android.twitterclient.images.ImagesPresenterImpl;
import com.oktacore.android.twitterclient.images.ImagesRepository;
import com.oktacore.android.twitterclient.images.ImagesRepositoryImpl;
import com.oktacore.android.twitterclient.images.ui.ImagesView;
import com.oktacore.android.twitterclient.images.ui.adapters.ImagesAdapter;
import com.oktacore.android.twitterclient.images.ui.adapters.OnItemClickListener;
import com.oktacore.android.twitterclient.lib.base.EventBus;
import com.oktacore.android.twitterclient.lib.base.ImageLoader;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Session;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by usuario on 18/06/16.
 */
@Module
public class ImagesModule {

    private ImagesView view;
    private OnItemClickListener clickListener;

    public ImagesModule(ImagesView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    ImagesAdapter providesAdapter(List<Image> dataset, ImageLoader imageLoader, OnItemClickListener clickListener){
        return new ImagesAdapter(dataset,imageLoader,clickListener);
    }

    @Provides
    @Singleton
    OnItemClickListener  providesOnItemClickLIstener(){
        return this.clickListener;
    }

    @Provides
    @Singleton
    List<Image>  providesItemsList(){
        return new ArrayList<Image>();
    }

    @Provides
    @Singleton
    ImagesPresenter providesImagesPresenter(ImagesView view, EventBus eventBus, ImagesInteractor interactor){
        return new ImagesPresenterImpl(eventBus,view,interactor);
    }

    @Provides
    @Singleton
    ImagesView providesImagesView(){
        return this.view;
    }

    @Provides
    @Singleton
    ImagesInteractor providesImagesInteractor(ImagesRepository repository){
        return new ImagesInteractorImpl(repository);
    }

    @Provides
    @Singleton
    ImagesRepository providesImagesRepositroy(EventBus eventBus, CustomTwitterApiClient client){
        return new ImagesRepositoryImpl(eventBus,client);
    }

    @Provides
    @Singleton
    CustomTwitterApiClient providesCustomTwitterApiClient(Session session){
        return new CustomTwitterApiClient(session);
    }

    @Provides
    @Singleton
    Session providesTwitter(){
        return Twitter.getSessionManager().getActiveSession();
    }
}
