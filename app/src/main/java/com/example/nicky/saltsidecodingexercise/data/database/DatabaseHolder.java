package com.example.nicky.saltsidecodingexercise.data.database;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

public class DatabaseHolder {

    private static ItemDatabase database;

    @MainThread
    public static void init(@NonNull Context context) {
        database = Room.databaseBuilder(
                context.getApplicationContext(),
                ItemDatabase.class,
                "items-database"
        ).build();
    }

    @NonNull
    public static ItemDatabase database() {
        return database;
    }
}
