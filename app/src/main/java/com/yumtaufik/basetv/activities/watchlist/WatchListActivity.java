package com.yumtaufik.basetv.activities.watchlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.yumtaufik.basetv.R;
import com.yumtaufik.basetv.activities.details.TvShowDetailsActivity;
import com.yumtaufik.basetv.adapters.WatchListAdapter;
import com.yumtaufik.basetv.databinding.ActivityWatchListBinding;
import com.yumtaufik.basetv.listeners.WatchListListener;
import com.yumtaufik.basetv.models.home.TVShowsItems;
import com.yumtaufik.basetv.utilities.TempDataHolder;
import com.yumtaufik.basetv.viewmodels.watchlist.WatchListViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class WatchListActivity extends AppCompatActivity implements WatchListListener {

    private ActivityWatchListBinding activityWatchListBinding;
    private WatchListViewModel watchListViewModel;
    private WatchListAdapter watchListAdapter;
    private List<TVShowsItems> watchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityWatchListBinding = DataBindingUtil.setContentView(this, R.layout.activity_watch_list);

        setInit();

        setArrowBack();
    }

    private void setInit() {
        watchListViewModel = new ViewModelProvider(this).get(WatchListViewModel.class);
        watchList = new ArrayList<>();
        loadWatchList();
    }

    private void setArrowBack() {
        activityWatchListBinding.imgBack.setOnClickListener(view -> onBackPressed());
    }

    private void loadWatchList() {
        activityWatchListBinding.setIsLoading(true);
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(watchListViewModel.loadWatchList()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvShowsItems -> {
                    activityWatchListBinding.setIsLoading(false);
                    if (watchList.size() > 0) {
                        watchList.clear();
                    }

                    watchList.addAll(tvShowsItems);
                    watchListAdapter = new WatchListAdapter(tvShowsItems, this);
                    activityWatchListBinding.rvWatchList.setAdapter(watchListAdapter);
                    activityWatchListBinding.rvWatchList.setVisibility(View.VISIBLE);
                    compositeDisposable.dispose();
        }));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (TempDataHolder.IS_WATCHLIST_UPDATED) {
            loadWatchList();
            TempDataHolder.IS_WATCHLIST_UPDATED = false;
        }
    }

    @Override
    public void onTVShowClicked(TVShowsItems tvShowsItems) {

        Intent intent = new Intent(WatchListActivity.this, TvShowDetailsActivity.class);
        intent.putExtra(TvShowDetailsActivity.KEY_DETAILS_PARCELABLE, tvShowsItems);
        startActivity(intent);
    }

    @Override
    public void removeTVShowFromWatchList(TVShowsItems tvShowsItems, int position) {

        CompositeDisposable compositeDisposableForDelete = new CompositeDisposable();
        compositeDisposableForDelete.add(watchListViewModel.removeTVShowFromWatchList(tvShowsItems)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    watchList.remove(position);
                    watchListAdapter.notifyItemRemoved(position);
                    watchListAdapter.notifyItemRangeChanged(position, watchListAdapter.getItemCount());
                    compositeDisposableForDelete.dispose();
                }));
    }
}