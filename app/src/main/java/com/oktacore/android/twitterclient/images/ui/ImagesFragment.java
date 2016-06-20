package com.oktacore.android.twitterclient.images.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.oktacore.android.twitterclient.R;
import com.oktacore.android.twitterclient.TwitterClientApp;
import com.oktacore.android.twitterclient.entities.Image;
import com.oktacore.android.twitterclient.images.ImagesPresenter;
import com.oktacore.android.twitterclient.images.di.ImagesComponent;
import com.oktacore.android.twitterclient.images.ui.adapters.ImagesAdapter;
import com.oktacore.android.twitterclient.images.ui.adapters.OnItemClickListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImagesFragment extends Fragment implements ImagesView, OnItemClickListener {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.container)
    FrameLayout container;

    @Inject
    ImagesAdapter imagesAdapter;
    @Inject
    ImagesPresenter imagesPresenter;

    public ImagesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);
        setupInjetction();
        setupRecyclerView();
        imagesPresenter.getImageTweets();
        return view;
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(imagesAdapter);
    }

    private void setupInjetction() {
        TwitterClientApp app = (TwitterClientApp)getActivity().getApplication();
        ImagesComponent imagesComponent = app.getImagesComponent(this, this,this);
        imagesComponent.inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        imagesPresenter.onResume();
    }
    @Override
    public void onPause() {
        imagesPresenter.onPause();
        super.onPause();
    }
    @Override
    public void onDestroy() {
        imagesPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showImages() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideImages() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(String error) {
        Snackbar.make(container,error,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setContent(List<Image> items) {
        imagesAdapter.setItems(items);
    }

    @Override
    public void onItemClickListener(Image image) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(image.getBaseTweetUrl()));
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
