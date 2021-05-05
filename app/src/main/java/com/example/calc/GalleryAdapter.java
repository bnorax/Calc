package com.example.calc;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder>{
    private List<GalleryItem> items;
    private LayoutInflater li;
    Context context;
    Uri currentSound;
    MediaPlayer player;
    WebViewClient client;
    WebView web;
    GalleryAdapter(Context context, List<GalleryItem> items){
        this.items = items;
        this.context = context;
        this.li = LayoutInflater.from(context);
        player = new MediaPlayer();



    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GalleryItem item = items.get(position);
        web = holder.itemView.findViewById(R.id.webView);
        client = new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url != null && (url.startsWith("http://") || url.startsWith("https://"))) {
                    web.loadUrl(url);
                    return true;
                } else {
                    return false;
                }
            }
        };
        web.setWebViewClient(client);
        web.getSettings().setJavaScriptEnabled(true);
        // web.loadUrl("https://www.google.com");




        holder.itemView.setOnClickListener(v->{
            player.stop();
            Bundle data = new Bundle();
            data.putParcelable("item", items.get(position));
            GalleryItemFragment fragment  = new GalleryItemFragment();
            fragment.setArguments(data);
            ((FragmentActivity)holder.itemView.getContext()).getSupportFragmentManager().beginTransaction().addToBackStack("openFull")
                    .replace(R.id.flGallery, fragment, null)
                    .commit();
        });
        holder.header.setText(item.headerRes);
        holder.desc.setText(item.descRes);
        holder.image.setImageURI(item.imageRes);


        holder.image.setOnClickListener(v->{
            if(item.musicRes == null) return;
            if(player != null){
                if(player.isPlaying()){
                    if(currentSound == item.musicRes){
                        player.pause();
                        return;
                    }
                    player.stop();
                    player = MediaPlayer.create(context, item.musicRes);
                    currentSound = item.musicRes;
                    player.start();
                    return;
                }
                if(currentSound == item.musicRes){
                    player.start();
                    return;
                }
                player = MediaPlayer.create(context, item.musicRes);
                currentSound = item.musicRes;
                player.start();
                return;
            }
            player = MediaPlayer.create(context, item.musicRes);
            currentSound = item.musicRes;
            player.start();
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = li.inflate(R.layout.fragment_gallery_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView header;
        TextView desc;

        ViewHolder(View view){
            super(view);
            image = view.findViewById(R.id.imageView);
            header = view.findViewById(R.id.header);
            desc = view.findViewById(R.id.desc);
            desc.setMaxLines(2);
        }
    }
}