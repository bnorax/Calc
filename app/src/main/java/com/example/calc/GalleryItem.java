package com.example.calc;

import android.media.Image;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.fragment.app.Fragment;

public class GalleryItem implements Parcelable {
    String headerRes;
    String descRes;
    Uri musicRes;
    Uri imageRes;
    public GalleryItem(String header, String desc, Uri music, Uri image){
        this.headerRes = header;
        this.descRes = desc;
        this.musicRes = music;
        this.imageRes = image;
    }
    public GalleryItem(Uri image){
        this.imageRes = image;
        this.headerRes = "empty";
    }
    public GalleryItem(){
        this.imageRes = null;
        this.headerRes = "empty";
    }

    public String getDescRes() {
        return descRes;
    }

    public Uri getImageRes() {
        return imageRes;
    }

    public String getHeaderRes() {
        return headerRes;
    }

    public Uri getMusicRes() {
        return musicRes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(headerRes);
        dest.writeString(descRes);
        dest.writeString(musicRes.toString());
        dest.writeString(imageRes.toString());
    }
    public static final Creator<GalleryItem> CREATOR = new Creator<GalleryItem>() {
        @Override
        public GalleryItem createFromParcel(Parcel source) {
            String headerRes = source.readString();
            String descRes = source.readString();
            Uri musicRes = Uri.parse(source.readString());
            Uri imageRes = Uri.parse(source.readString());;
            return new GalleryItem(headerRes, descRes, musicRes, imageRes);
        }

        @Override
        public GalleryItem[] newArray(int size) {
            return new GalleryItem[size];
        }
    };
}