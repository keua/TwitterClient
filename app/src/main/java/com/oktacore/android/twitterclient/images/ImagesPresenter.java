package com.oktacore.android.twitterclient.images;

import com.oktacore.android.twitterclient.images.events.ImagesEvent;

/**
 * Created by usuario on 18/06/16.
 */
public interface ImagesPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getImageTweets();
    void onEventMainThread(ImagesEvent event);

}
