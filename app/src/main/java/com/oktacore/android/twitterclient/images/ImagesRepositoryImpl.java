package com.oktacore.android.twitterclient.images;

import android.provider.ContactsContract;

import com.oktacore.android.twitterclient.api.CustomTwitterApiClient;
import com.oktacore.android.twitterclient.entities.Image;
import com.oktacore.android.twitterclient.images.events.ImagesEvent;
import com.oktacore.android.twitterclient.lib.base.EventBus;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by usuario on 18/06/16.
 */
public class ImagesRepositoryImpl implements ImagesRepository{

    private EventBus eventBus;
    private CustomTwitterApiClient client;
    private final static int TWEET_COUNT = 100;

    public ImagesRepositoryImpl(EventBus eventBus, CustomTwitterApiClient client) {
        this.eventBus = eventBus;
        this.client = client;
    }

    @Override
    public void getImages() {
        Callback<List<Tweet>> callback = new Callback<List<Tweet>>() {
            @Override
            public void success(List<Tweet> tweets, Response response) {
                List<Image> items = new ArrayList<Image>();
                for (Tweet tweet : tweets){
                    if (containsImages(tweet)){
                        Image tweetModel = new Image();
                        tweetModel.setId(tweet.idStr);
                        tweetModel.setFavoriteCount(tweet.favoriteCount);

                        String tweetText = tweet.text;
                        int index = tweetText.indexOf("http");
                        if (index > 0){
                            tweetText.substring(0,index);
                        }
                        tweetModel.setTweetText(tweetText);

                        MediaEntity currentPhoto = tweet.entities.media.get(0);
                        String imageURL = currentPhoto.mediaUrl;
                        tweetModel.setImageURL(imageURL);

                        items.add(tweetModel);

                    }
                }

                Collections.sort(items, new Comparator<Image>() {
                    @Override
                    public int compare(Image image, Image t1) {
                        return t1.getFavoriteCount() - image.getFavoriteCount();
                    }
                });

                post(items,null);
            }

            @Override
            public void failure(RetrofitError error) {
                post(error.getLocalizedMessage());
            }
        };

        client.getTimelineService().homeTimeline(TWEET_COUNT,true,true,true,true,callback);
    }

    private boolean containsImages(Tweet tweet){
        return tweet.entities != null &&
                tweet.entities.media != null &&
                !tweet.entities.media.isEmpty();
    }

    public void post(String error){
        post(null,error);
    }

    public void post(List<Image> items,String error){
        ImagesEvent event = new ImagesEvent();
        event.setImages(items);
        event.setError(error);
        eventBus.post(event);
    }
}
