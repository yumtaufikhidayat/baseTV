package com.yumtaufik.basetv.activities.search;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.yumtaufik.basetv.R;
import com.yumtaufik.basetv.activities.details.TvShowDetailsActivity;
import com.yumtaufik.basetv.adapters.TVShowsAdapter;
import com.yumtaufik.basetv.databinding.ActivitySearchBinding;
import com.yumtaufik.basetv.listeners.TvShowsListener;
import com.yumtaufik.basetv.models.home.TVShowsItems;
import com.yumtaufik.basetv.viewmodels.search.SearchTVShowsViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SearchActivity extends AppCompatActivity implements TvShowsListener {

    private ActivitySearchBinding activitySearchBinding;
    private SearchTVShowsViewModel searchTVShowsViewModel;
    private TVShowsAdapter tvShowsAdapter;
    private Timer timer;

    List<TVShowsItems> tvShowsItems = new ArrayList<>();

    private int currentPage = 1;
    private int totalAvailablePages = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySearchBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);

        setInit();
    }

    private void setInit() {
        activitySearchBinding.imgBack.setOnClickListener(v -> onBackPressed());
        activitySearchBinding.rvSearchTVShows.setHasFixedSize(true);
        searchTVShowsViewModel = new ViewModelProvider(this).get(SearchTVShowsViewModel.class);
        tvShowsAdapter = new TVShowsAdapter(tvShowsItems, this);
        activitySearchBinding.rvSearchTVShows.setAdapter(tvShowsAdapter);

        setSearchTextWatcher();
    }

    private void setSearchTextWatcher() {
        activitySearchBinding.etInputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (timer != null) {
                    timer.cancel();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().trim().isEmpty()) {
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            new Handler(Looper.getMainLooper()).post(() -> {
                                currentPage = 1;
                                totalAvailablePages = 1;
                                searchTVShows(s.toString());
                            });
                        }
                    }, 800);
                } else {
                    tvShowsItems.clear();
                    tvShowsAdapter.notifyDataSetChanged();
                }
            }
        });

        activitySearchBinding.rvSearchTVShows.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!activitySearchBinding.rvSearchTVShows.canScrollVertically(1)) {
                    if (!activitySearchBinding.etInputSearch.getText().toString().isEmpty()) {
                        if (currentPage < totalAvailablePages) {
                            currentPage += 1;
                            searchTVShows(activitySearchBinding.etInputSearch.getText().toString());
                        }
                    }
                }
            }
        });

        activitySearchBinding.etInputSearch.requestFocus();
    }

    private void searchTVShows(String query) {
        toggleLoading();
        searchTVShowsViewModel.searchTVShows(query, currentPage).observe(this, tvShowsResponse -> {
            toggleLoading();
            if (tvShowsResponse != null) {
                totalAvailablePages = tvShowsResponse.getPages();
                if (tvShowsResponse.getTv_shows() != null) {
                    int oldCount = tvShowsItems.size();
                    tvShowsItems.addAll(tvShowsResponse.getTv_shows());
                    tvShowsAdapter.notifyItemRangeInserted(oldCount, tvShowsItems.size());
                }
            }
        });
    }

    private void toggleLoading() {
        if (currentPage == 1) {
            activitySearchBinding.setIsLoading(activitySearchBinding.getIsLoading() == null || !activitySearchBinding.getIsLoading());
        } else {
            activitySearchBinding.setIsLoadingMore(activitySearchBinding.getIsLoadingMore() == null || !activitySearchBinding.getIsLoadingMore());
        }
    }

    @Override
    public void onTvShowClicked(TVShowsItems tvShowsItems) {
        Intent intent = new Intent(this, TvShowDetailsActivity.class);
        intent.putExtra(TvShowDetailsActivity.KEY_DETAILS_PARCELABLE, tvShowsItems);
        startActivity(intent);
    }
}