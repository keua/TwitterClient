package com.oktacore.android.twitterclient.api;

import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.TwitterApiClient;

/**
 * Created by usuario on 16/06/16.
 */
public class CustomTwitterApiClient extends TwitterApiClient {
    public CustomTwitterApiClient(Session session) {
        super(session);
    }

    public TimelineService getTimelineService(){
        return getService(TimelineService.class);
    }
}
