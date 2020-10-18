package com.yumtaufik.basetv.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.yumtaufik.basetv.R;
import com.yumtaufik.basetv.adapters.TVShowsAdapter;
import com.yumtaufik.basetv.databinding.ActivityMainBinding;
import com.yumtaufik.basetv.models.TVShowsItems;
import com.yumtaufik.basetv.viewmodels.MostPopularTVShowsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private MostPopularTVShowsViewModel mostPopularTVShowsViewModel;
    private TVShowsAdapter tvShowsAdapter;

    private final List<TVShowsItems> tvShowsItemsList = new ArrayList<>();
    private int currentPage = 1;
    private int totalAvailablePages = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setInit();

        getMostPopularTVShows();
    }

    private void setInit() {
        activityMainBinding.rvTvShows.setHasFixedSize(true);
        mostPopularTVShowsViewModel = new ViewModelProvider(this).get(MostPopularTVShowsViewModel.class);
        tvShowsAdapter = new TVShowsAdapter(tvShowsItemsList);
        activityMainBinding.rvTvShows.setAdapter(tvShowsAdapter);
        activityMainBinding.rvTvShows.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!activityMainBinding.rvTvShows.canScrollVertically(1)) {
                    if (currentPage <= totalAvailablePages) {
                        currentPage += 1;
                        getMostPopularTVShows();
                    }
                }
            }
        });
    }

    private void getMostPopularTVShows() {
        toggleLoading();
        mostPopularTVShowsViewModel.getMostPopularTVShows(currentPage).observe(this, mostPopularTVShowsResponse -> {
            toggleLoading();
            if (mostPopularTVShowsResponse != null) {
                totalAvailablePages = mostPopularTVShowsResponse.getPages();
                if (mostPopularTVShowsResponse.getTv_shows() != null) {
                    int oldCount = tvShowsItemsList.size();
                    tvShowsItemsList.addAll(mostPopularTVShowsResponse.getTv_shows());
                    tvShowsAdapter.notifyItemRangeInserted(oldCount, tvShowsItemsList.size());
                }
            }
        });
    }

    private void toggleLoading() {
        if (currentPage == 1) {
            activityMainBinding.setIsLoading(activityMainBinding.getIsLoading() == null || !activityMainBinding.getIsLoading());
        } else {
            activityMainBinding.setIsLoadingMore(activityMainBinding.getIsLoadingMore() == null || !activityMainBinding.getIsLoadingMore());
        }
    }
}