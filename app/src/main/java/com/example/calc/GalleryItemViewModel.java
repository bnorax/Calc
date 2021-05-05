package com.example.calc;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GalleryItemViewModel extends ViewModel {
    private final MutableLiveData<GalleryItem> selectedItem = new MutableLiveData<GalleryItem>();
    public void selectItem(GalleryItem item) {
        selectedItem.postValue(item);
    }
    public LiveData<GalleryItem> getSelectedItem() {
        return selectedItem;
    }
}
