package com.example.nicky.saltsidecodingexercise.interator;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.nicky.saltsidecodingexercise.data.Items;
import com.example.nicky.saltsidecodingexercise.data.JsonResponse;
import com.example.nicky.saltsidecodingexercise.data.database.DatabaseHolder;
import com.example.nicky.saltsidecodingexercise.data.database.dao.ItemDao;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by NICKY on 09-09-2018.
 */

public class ApiRepository {
    @NonNull
    private final ApiService mApiService;

    private ItemDao mItemDao;
    private static ApiRepository apiRepository;
    final MutableLiveData<Boolean> loadingLiveData;
    private List<Items> responseItems;

    public ApiRepository() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mApiService = retrofit.create(ApiService.class);
        if (DatabaseHolder.database() != null) {
            mItemDao = DatabaseHolder.database().itemDao();
        }

        loadingLiveData = new MutableLiveData<>();
    }

    public synchronized static ApiRepository getInstance() {
        if (apiRepository == null) {
            synchronized (ApiRepository.class) {
                if (apiRepository == null) {
                    apiRepository = new ApiRepository();
                }
            }
        }
        return apiRepository;
    }

    @NonNull
    public LiveData<List<Items>> getJsonDataList() {
        final MutableLiveData<List<Items>> data = new MutableLiveData<>();

        mApiService.getJsonData()
                .doOnSubscribe(disposable -> {
                    loadingLiveData.postValue(true);
                })
                .doOnTerminate(() -> {
                    loadingLiveData.postValue(false);
                })
                .doOnComplete(() -> {
                    loadingLiveData.postValue(false);
                })
                .doOnNext(jsonResponses -> {
                    if (jsonResponses != null && jsonResponses.size() > 0) {
                        responseItems = new ArrayList<>();
                        for (JsonResponse response : jsonResponses) {
                            Items item = new Items();
                            item.setTitle(response.getTitle());
                            item.setDescription(response.getDescription());
                            item.setImage(response.getImage());
                            responseItems.add(item);
                        }
                    }
                    // mItemDao.saveAll(responseItems);
                    data.postValue(responseItems);
                    List<Items> items = mItemDao.getItems();
                    mItemDao.clear(items);
                    mItemDao.saveAll(responseItems);
                })
                .onErrorResumeNext(throwable -> {
                    List<Items> items = mItemDao.getItems();
                    Observable.just(items);
                    data.postValue(items);
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
        return data;

    }

    public LiveData<Boolean> getLoadingLiveData() {
        return loadingLiveData;
    }
}
