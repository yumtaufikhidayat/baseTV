package com.yumtaufik.basetv.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.yumtaufik.basetv.R;
import com.yumtaufik.basetv.viewmodels.MostPopularTVShowsViewModel;

public class MainActivity extends AppCompatActivity {

    private MostPopularTVShowsViewModel mostPopularTVShowsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInit();

        getMostPopularTVShows();
    }

    private void setInit() {
        mostPopularTVShowsViewModel = new ViewModelProvider(this).get(MostPopularTVShowsViewModel.class);
    }

    private void getMostPopularTVShows() {
        mostPopularTVShowsViewModel.getMostPopularTVShows(0).observe(this, mostPopularTVShowsResponse ->
                Toast.makeText(this, "Total pages: " + mostPopularTVShowsResponse.getTotal(), Toast.LENGTH_SHORT).show()
        );
    }
}