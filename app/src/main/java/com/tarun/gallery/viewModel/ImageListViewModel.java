package com.tarun.gallery.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.tarun.gallery.repository.DataManager;
import com.tarun.gallery.data.Image;

import java.util.ArrayList;

public class ImageListViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Image>> imagesLiveData = new MutableLiveData<ArrayList<Image>>();

    public ImageListViewModel(@NonNull Application application) {
        super(application);
        init();
    }

    private void init() {
        imagesLiveData = DataManager.getInstance().getImagesLiveData();
    }

    /**
     * API to retun list of images live data
     * @return MutableLiveData<ArrayList<Image>
     */
    public MutableLiveData<ArrayList<Image>> getImagesLiveData() {
        return imagesLiveData;
    }

    /**
     * API to return Image Obj based on Position
     * @param position
     * @return Image
     */
    public Image getImageObjBasedOnPos(int position) {
        if (imagesLiveData.getValue() != null && imagesLiveData.getValue().size() > position) {
            return imagesLiveData.getValue().get(position);
        }
        return null;
    }
}
