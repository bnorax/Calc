package com.example.calc;

import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.documentfile.provider.DocumentFile;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class AddToList extends Fragment {
    TextView musicInfo;
    EditText header, desc;
    Button addPost, addMusic;
    ImageView image;
    ActivityResultLauncher<String> getImage, getAudio;
    GalleryItem localItem;
    private GalleryItemViewModel newItem;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        localItem = new GalleryItem();
        newItem = new ViewModelProvider(requireActivity()).get(GalleryItemViewModel.class);
        getImage = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        localItem.imageRes = result;
                        image.setImageURI(result);
                    }
                });
        getAudio = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        localItem.musicRes = result;
                        musicInfo.setText(DocumentFile.fromSingleUri(getContext(), result).getName());
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_to_list, container, false);
        musicInfo = view.findViewById(R.id.musicInfo);
        header = view.findViewById(R.id.headerAdd);
        desc = view.findViewById(R.id.descAdd);
        addPost = view.findViewById(R.id.addPost);
        addMusic = view.findViewById(R.id.musicAdd);
        image = view.findViewById(R.id.imageViewAdd);
        image.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_add_24, null));
        image.setOnClickListener(v->{
            getImage.launch("image/*");
        });
        addMusic.setOnClickListener(v->{
            getAudio.launch("audio/*");
        });
        addPost.setOnClickListener(v->{
            localItem.headerRes = header.getText().toString();
            localItem.descRes = desc.getText().toString();
            if(localItem.headerRes.isEmpty() ||  localItem.descRes.isEmpty()){
                return;
            }
            newItem.selectItem(localItem);
            getParentFragmentManager().popBackStack();
        });

        // Inflate the layout for this fragment
        return view;
    }

}