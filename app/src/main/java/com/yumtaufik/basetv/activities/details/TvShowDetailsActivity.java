package com.yumtaufik.basetv.activities.details;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.yumtaufik.basetv.R;
import com.yumtaufik.basetv.adapters.ImageSliderAdapter;
import com.yumtaufik.basetv.databinding.ActivityTvShowDetailsBinding;
import com.yumtaufik.basetv.viewmodels.details.TvShowDetailsViewModel;

import java.util.List;

public class TvShowDetailsActivity extends AppCompatActivity {

    public static final String KEY_DETAILS_ID = "com.yumtaufik.basetv.activities.details.KEY_DETAILS_ID";
//    public static final String KEY_DETAILS_PARCELABLE = "com.yumtaufik.basetv.activities.details.KEY_DETAILS";

    private ActivityTvShowDetailsBinding activityTvShowDetailsBinding;
    private TvShowDetailsViewModel tvShowDetailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTvShowDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_tv_show_details);

        setInit();

        setArrowBack();

        getTvShowDetails();
    }

    private void setInit() {
        tvShowDetailsViewModel = new ViewModelProvider(this).get(TvShowDetailsViewModel.class);
    }

    private void setArrowBack() {
        activityTvShowDetailsBinding.imgBack.setOnClickListener(view -> onBackPressed());
    }

    private void getTvShowDetails() {

        activityTvShowDetailsBinding.setIsLoading(true);

        String tvShowId = String.valueOf(getIntent().getIntExtra(KEY_DETAILS_ID, 0));
//        TVShowsItems tvShowsItems = getIntent().getParcelableExtra(KEY_DETAILS_PARCELABLE);
        tvShowDetailsViewModel.getTvShowDetails(tvShowId).observe(this, tvShowDetailsResponse -> {
            activityTvShowDetailsBinding.setIsLoading(false);
            if (tvShowDetailsResponse.getTvShow() != null) {
                if (tvShowDetailsResponse.getTvShow().getPictures() != null) {
                    loadImageSlider(tvShowDetailsResponse.getTvShow().getPictures());
                }

                activityTvShowDetailsBinding.setTvShowImageUrl(tvShowDetailsResponse.getTvShow().getImagePath());
                activityTvShowDetailsBinding.imgTvShow.setVisibility(View.VISIBLE);
            }
        });
    }

    private void loadImageSlider(List<String> sliderImages) {
        activityTvShowDetailsBinding.sliderViewPager.setOffscreenPageLimit(1);
        activityTvShowDetailsBinding.sliderViewPager.setAdapter(new ImageSliderAdapter(sliderImages));
        activityTvShowDetailsBinding.sliderViewPager.setVisibility(View.VISIBLE);
        activityTvShowDetailsBinding.viewFadingEdge.setVisibility(View.VISIBLE);

        setSliderIndicators(sliderImages.size());
        activityTvShowDetailsBinding.sliderViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentSliderIndicator(position);
            }
        });
    }

    private void setSliderIndicators(int count) {
        ImageView[] indicators = new ImageView[count];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );

        layoutParams.setMargins(8, 0, 8, 0);

        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(this);
            indicators[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.backround_slider_indicator_inactive));
            indicators[i].setLayoutParams(layoutParams);
            activityTvShowDetailsBinding.layoutSliderIndicator.addView(indicators[i]);
        }

        activityTvShowDetailsBinding.layoutSliderIndicator.setVisibility(View.VISIBLE);

        setCurrentSliderIndicator(0);
    }

    private void setCurrentSliderIndicator(int position) {
        int childCount = activityTvShowDetailsBinding.layoutSliderIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) activityTvShowDetailsBinding.layoutSliderIndicator.getChildAt(i);
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.backround_slider_indicator_active));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.backround_slider_indicator_inactive));
            }
        }
    }
}