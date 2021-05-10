package com.example.calc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.media.browse.MediaBrowser;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.calc.data.JsonParser;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Gallery extends Fragment{
    ArrayList<GalleryItem> galleryItems;
    RecyclerView  recyclerView;
    GalleryAdapter galleryAdapter;
    GalleryItemViewModel newItem;
    JsonParser parser;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parser = new JsonParser(getContext());
        galleryItems = new ArrayList<>();
        newItem = new ViewModelProvider(requireActivity()).get(GalleryItemViewModel.class);
        newItem.getSelectedItem().observeForever(galleryItem -> {
            GalleryItem newItem = galleryItem;
            galleryItems.add(newItem);
            parser.savePostsToFS(galleryItems);
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // SetData();
        List<GalleryItem> fsRead = (ArrayList<GalleryItem>)parser.getPostsFromFS();
        if(fsRead != null) galleryItems = (ArrayList<GalleryItem>)parser.getPostsFromFS();
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        FloatingActionButton button = view.findViewById(R.id.addButton);
        recyclerView = view.findViewById(R.id.list);
        button.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("add")
                    .replace(R.id.flGallery, AddToList.class, null)
                    .commit();
        });
        galleryAdapter = new GalleryAdapter(getContext(), galleryItems);
        recyclerView.setAdapter(galleryAdapter);
        return view;

    }

    @Override
    public void onPause() {
        super.onPause();
        parser.savePostsToFS(galleryItems);
    }
}