package com.yumtaufik.basetv.activities.watchlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.yumtaufik.basetv.R;
import com.yumtaufik.basetv.databinding.ActivityWatchListBinding;
import com.yumtaufik.basetv.viewmodels.watchlist.WatchListViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class WatchListActivity extends AppCompatActivity {

    private ActivityWatchListBinding activityWatchListBinding;
    private WatchListViewModel watchListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityWatchListBinding = DataBindingUtil.setContentView(this, R.layout.activity_watch_list);

        setInit();

        setArrowBack();
    }

    private void setInit() {
        watchListViewModel = new ViewModelProvider(this).get(WatchListViewModel.class);
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
                    Toast.makeText(this, "Watchlist: " + tvShowsItems.size(), Toast.LENGTH_SHORT).show();
        }));
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadWatchList();
    }
}