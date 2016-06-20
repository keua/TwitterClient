package com.oktacore.android.twitterclient.images.ui;


import com.oktacore.android.twitterclient.entities.Image;

import java.util.List;

/**
 * Created by usuario on 18/06/16.
 */
public interface ImagesView {
    void showImages();
    void hideImages();
    void showProgress();
    void hideProgress();
    void onError(String error);
    void setContent(List<Image> items);

}
