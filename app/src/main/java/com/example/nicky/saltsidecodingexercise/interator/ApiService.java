package com.example.nicky.saltsidecodingexercise.interator;

import com.example.nicky.saltsidecodingexercise.data.JsonResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by NICKY on 09-09-2018.
 */

public interface ApiService {
    String API_ENDPOINT = "https://gist.githubusercontent.com/ashwini9241/";

    @GET("6e0f26312ddc1e502e9d280806eed8bc/raw/7fab0cf3177f17ec4acd6a2092fc7c0f6bba9e1f/saltside-json-data")
    Observable<List<JsonResponse>> getJsonData();
}
