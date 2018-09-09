package com.example.nicky.saltsidecodingexercise.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.nicky.saltsidecodingexercise.data.Items;

import java.util.List;

/**
 * Created by NICKY on 09-09-2018.
 */
@Dao
public interface ItemDao {

    @Query("SELECT * FROM items")
    List<Items> getItems();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<Items> items);

    @Delete
    void clear(List<Items> items);
}
