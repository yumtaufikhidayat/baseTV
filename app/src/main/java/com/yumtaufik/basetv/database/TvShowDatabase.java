package com.yumtaufik.basetv.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.yumtaufik.basetv.dao.TvShowDao;
import com.yumtaufik.basetv.models.home.TVShowsItems;

@Database(entities = TVShowsItems.class, version = 1, exportSchema = false)
public abstract class TvShowDatabase extends RoomDatabase {

    private static TvShowDatabase tvShowDatabase;

    public static synchronized TvShowDatabase getTvShowDatabase(Context context) {
        if (tvShowDatabase == null) {
            tvShowDatabase = Room.databaseBuilder(
                    context,
                    TvShowDatabase.class,
                    "tv_show_db"
            ).build();
        }

        return tvShowDatabase;
    }

    public abstract TvShowDao tvShowDao();
}
