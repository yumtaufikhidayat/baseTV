package com.yumtaufik.basetv.activities.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.yumtaufik.basetv.R;
import com.yumtaufik.basetv.databinding.ActivityTvShowDetailsBinding;
import com.yumtaufik.basetv.models.home.TVShowsItems;
import com.yumtaufik.basetv.viewmodels.details.TvShowDetailsViewModel;

public class TvShowDetailsActivity extends AppCompatActivity {

    public static final String KEY_DETAILS_ID = "com.yumtaufik.basetv.activities.details.KEY_DETAILS_ID";
    public static final String KEY_DETAILS_PARCELABLE = "com.yumtaufik.basetv.activities.details.KEY_DETAILS";

    private ActivityTvShowDetailsBinding activityTvShowDetailsBinding;
    private TvShowDetailsViewModel tvShowDetailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTvShowDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_tv_show_details);

        setInit();

        getTvShowDetails();
    }

    private void setInit() {
        tvShowDetailsViewModel = new ViewModelProvider(this).get(TvShowDetailsViewModel.class);
    }

    private void getTvShowDetails() {

        activityTvShowDetailsBinding.setIsLoading(true);

        String tvShowId = String.valueOf(getIntent().getIntExtra(KEY_DETAILS_ID, 0));
        TVShowsItems tvShowsItems = getIntent().getParcelableExtra(KEY_DETAILS_PARCELABLE);
        tvShowDetailsViewModel.getTvShowDetails(tvShowId).observe(this, tvShowDetailsResponse -> {
            activityTvShowDetailsBinding.setIsLoading(false);
            Toast.makeText(this, tvShowDetailsResponse.getTvShow().getUrl(), Toast.LENGTH_SHORT).show();
        });

    }
}