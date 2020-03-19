package com.example.pruebaandroid.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
//POJO

public class BreedImageListResponse {
    @SerializedName("message")
    private List<String> imageURL;
    private String status;


    public BreedImageListResponse(List<String> imageURL, String status) {
        this.imageURL = imageURL;
        this.status = status;
    }

    public BreedImageListResponse(List<String> imageURL) {
        this.imageURL = imageURL;
    }

    public List<String> getImageURL() {
        return imageURL;
    }

    public void setImageURL(List<String> imageURL) {
        this.imageURL = imageURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
