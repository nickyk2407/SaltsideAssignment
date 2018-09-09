package com.example.nicky.saltsidecodingexercise.ui.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.nicky.saltsidecodingexercise.data.Items;
import com.example.nicky.saltsidecodingexercise.interator.ApiRepository;

import java.util.List;

/**
 * Created by NICKY on 09-09-2018.
 */

public class ItemViewModel extends AndroidViewModel {

    @NonNull
    private final LiveData<Boolean> loadingLiveData;

    @Nullable
    private LiveData<List<Items>> mItemLiveData;

    public ItemViewModel(@NonNull Application application) {
        super(application);

        mItemLiveData = ApiRepository.getInstance().getJsonDataList();
        loadingLiveData = ApiRepository.getInstance().getLoadingLiveData();
    }

    @NonNull
    public LiveData<Boolean> isLoading() {
        return loadingLiveData;
    }

    @Nullable
    public LiveData<List<Items>> getJsonDataList() {
        return mItemLiveData;
    }
}
