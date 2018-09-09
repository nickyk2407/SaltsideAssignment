package com.example.nicky.saltsidecodingexercise.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.nicky.saltsidecodingexercise.data.Items;
import com.example.nicky.saltsidecodingexercise.data.database.dao.ItemDao;

/**
 * @author Artur Vasilov
 */
@Database(entities = {Items.class}, version = 1)
public abstract class ItemDatabase extends RoomDatabase {
    public abstract ItemDao itemDao();
}
