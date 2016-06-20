package com.oktacore.android.twitterclient.images;

/**
 * Created by usuario on 18/06/16.
 */
public class ImagesInteractorImpl implements ImagesInteractor{

    private ImagesRepository repository;

    public ImagesInteractorImpl(ImagesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.getImages();
    }
}
