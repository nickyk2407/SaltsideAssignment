package com.example.nicky.saltsidecodingexercise.data;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NICKY on 09-09-2018.
 */

public class JsonResponse {
    @SerializedName("image")
    private String image;

    @SerializedName("description")
    private String description;

    @SerializedName("title")
    private String title;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
