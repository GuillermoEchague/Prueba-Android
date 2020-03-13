package com.example.pruebaandroid.api;

import com.example.pruebaandroid.model.BreedImageListResponse;
import com.example.pruebaandroid.model.BreedListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiDog {
    //https://dog.ceo/
    @GET("api/breeds/list")
    Call<BreedListResponse> getBreedList();

    @GET("api/breed/{breed}/images/")
    Call<BreedImageListResponse> getBreedImageList(@Path("breed") String breed);
}
