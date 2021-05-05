package com.example.calc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GalleryItemFragment extends Fragment {
    GalleryItem item;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery_item, container, false);
        TextView desc = view.findViewById(R.id.desc);
        TextView header = view.findViewById(R.id.header);
        ImageView image = view.findViewById(R.id.imageView);
        if(item.descRes != null){
            desc.setText(item.descRes);
        }
        if(item.headerRes != null){
            header.setText(item.headerRes);
        }
        if(item.imageRes != null){
            image.setImageURI(item.imageRes);
        }
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle != null){
            item = bundle.getParcelable("item");
        }

    }

}
