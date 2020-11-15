package com.yumtaufik.basetv.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.yumtaufik.basetv.models.home.TVShowsItems;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface TvShowDao {

    @Query("SELECT * FROM tvShows")
    Flowable<List<TVShowsItems>> getWatchList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addToWatchList(TVShowsItems TVShowsItems);

    @Delete
    void removeFromWatchList(TVShowsItems TVShowsItems);
}
