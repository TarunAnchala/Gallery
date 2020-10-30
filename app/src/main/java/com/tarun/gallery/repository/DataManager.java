package com.tarun.gallery.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.tarun.gallery.api.ApiInterface;
import com.tarun.gallery.api.RetrofitService;
import com.tarun.gallery.data.Image;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Singleton class to fetch data from API
 */
public class DataManager {

    private static volatile DataManager dataManager;
    private ApiInterface apiInterface;
    private MutableLiveData<ArrayList<Image>> imagesLiveData = new MutableLiveData<ArrayList<Image>>();
    private static final String TAG = "DataManager";

    private DataManager() {
        apiInterface = RetrofitService.getRetrofitInstance().create(ApiInterface.class);
        fetchImageList();
    }

    public static DataManager getInstance() {
        if (dataManager == null) {
            synchronized (DataManager.class) {
                if (dataManager == null) {
                    dataManager = new DataManager();
                }
            }
        }
        return dataManager;
    }

    /**
     * API to fetch list of images from Server
     */
    private void fetchImageList() {
        Call<ArrayList<Image>> call = apiInterface.getListOfImages(100);
        call.enqueue(new Callback<ArrayList<Image>>() {
            @Override
            public void onResponse(Call<ArrayList<Image>> call, Response<ArrayList<Image>> response) {
                Log.e(TAG, "onResponse: called ");
                imagesLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Image>> call, Throwable t) {
                Log.e(TAG, "onFailure: called ===" + t.getMessage());
                imagesLiveData.setValue(null);
            }
        });
    }

    /**
     * API to return list of images live data
     *
     * @return MutableLiveData<ArrayList < Image>>
     */
    public MutableLiveData<ArrayList<Image>> getImagesLiveData() {
        return imagesLiveData;
    }

    public static void deleteInstance() {
        dataManager = null;
    }
}
