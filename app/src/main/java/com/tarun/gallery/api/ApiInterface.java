package com.tarun.gallery.api;


import com.tarun.gallery.data.Image;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * This file consists of API's to fetch list of images
 */
public interface ApiInterface {

    String BASE_URL = "https://picsum.photos/v2/";

    @GET("list")
    Call<ArrayList<Image>> getListOfImages(@Query("limit") int limit);

    @GET("list")
    Call<ArrayList<Image>> getList();
}
