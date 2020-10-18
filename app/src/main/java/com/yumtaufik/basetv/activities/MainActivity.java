package com.yumtaufik.basetv.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

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
    private final List<TVShowsItems> tvShowsItemsList = new ArrayList<>();
    private TVShowsAdapter tvShowsAdapter;

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
    }

    private void getMostPopularTVShows() {
        activityMainBinding.setIsLoading(true);
        mostPopularTVShowsViewModel.getMostPopularTVShows(0).observe(this, mostPopularTVShowsResponse -> {
            activityMainBinding.setIsLoading(false);
            if (mostPopularTVShowsResponse != null) {
                if (mostPopularTVShowsResponse.getTv_shows() != null) {
                    tvShowsItemsList.addAll(mostPopularTVShowsResponse.getTv_shows());
                    tvShowsAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}