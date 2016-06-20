package com.oktacore.android.twitterclient.images.ui.adapters;

import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.oktacore.android.twitterclient.R;
import com.oktacore.android.twitterclient.entities.Image;
import com.oktacore.android.twitterclient.lib.base.ImageLoader;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by usuario on 18/06/16.
 */
public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {

    private List<Image> dataset;
    private ImageLoader imageLoader;
    private OnItemClickListener clickListener;

    public ImagesAdapter(List<Image> dataset, ImageLoader imageLoader, OnItemClickListener clickListener) {
        this.dataset = dataset;
        this.imageLoader = imageLoader;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_images,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Image ImageTweet = dataset.get(position);
        holder.setOnClickListener(ImageTweet,clickListener);
        holder.txtTweet.setText(ImageTweet.getTweetText());
        imageLoader.load(holder.imgMedia, ImageTweet.getImageURL());
    }

    public void setItems(List<Image> newItems){
        dataset.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.txtTweet)
        TextView txtTweet;
        @Bind(R.id.imgMedia)
        ImageView imgMedia;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            this.view = itemView;
        }

        public void setOnClickListener(final Image image, final OnItemClickListener listener){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClickListener(image);
                }
            });
        }
    }
}
