package com.yumtaufik.basetv.activities.details;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.yumtaufik.basetv.R;
import com.yumtaufik.basetv.adapters.ImageSliderAdapter;
import com.yumtaufik.basetv.databinding.ActivityTvShowDetailsBinding;
import com.yumtaufik.basetv.models.home.TVShowsItems;
import com.yumtaufik.basetv.viewmodels.details.TvShowDetailsViewModel;

import java.util.List;
import java.util.Locale;

public class TvShowDetailsActivity extends AppCompatActivity {

    public static final String KEY_DETAILS_ID = "com.yumtaufik.basetv.activities.details.KEY_DETAILS_ID";
    public static final String KEY_DETAILS_PARCELABLE = "com.yumtaufik.basetv.activities.details.KEY_DETAILS";

    private ActivityTvShowDetailsBinding activityTvShowDetailsBinding;
    private TvShowDetailsViewModel tvShowDetailsViewModel;

    private TVShowsItems tvShowsItems;

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
        tvShowsItems = getIntent().getParcelableExtra(KEY_DETAILS_PARCELABLE);
    }

    private void setArrowBack() {
        activityTvShowDetailsBinding.imgBack.setOnClickListener(view -> onBackPressed());
    }

    private void getTvShowDetails() {

        activityTvShowDetailsBinding.setIsLoading(true);

        String tvShowId = String.valueOf(getIntent().getIntExtra(KEY_DETAILS_ID, 0));
        tvShowDetailsViewModel.getTvShowDetails(tvShowId).observe(this, tvShowDetailsResponse -> {
            activityTvShowDetailsBinding.setIsLoading(false);
            if (tvShowDetailsResponse.getTvShow() != null) {
                if (tvShowDetailsResponse.getTvShow().getPictures() != null) {
                    loadImageSlider(tvShowDetailsResponse.getTvShow().getPictures());
                }

                activityTvShowDetailsBinding.setImageUrl(tvShowDetailsResponse.getTvShow().getImagePath());
                activityTvShowDetailsBinding.imgTvShow.setVisibility(View.VISIBLE);

                activityTvShowDetailsBinding.setDescription(
                        String.valueOf(HtmlCompat.fromHtml(tvShowDetailsResponse.getTvShow().getDescription(), HtmlCompat.FROM_HTML_MODE_LEGACY))
                );
                activityTvShowDetailsBinding.tvShowDescription.setVisibility(View.VISIBLE);

                activityTvShowDetailsBinding.setRating(
                        String.format(
                                Locale.getDefault()
                                ,"%.2f"
                                , Double.parseDouble(tvShowDetailsResponse.getTvShow().getRating()))
                );

                if (tvShowDetailsResponse.getTvShow().getGenres() != null) {
                    activityTvShowDetailsBinding.setGenre(tvShowDetailsResponse.getTvShow().getGenres().get(0));
                } else {
                    activityTvShowDetailsBinding.setGenre("N/A");
                }

                activityTvShowDetailsBinding.setRuntime(tvShowDetailsResponse.getTvShow().getRuntime() + " min");

                activityTvShowDetailsBinding.viewDivider1.setVisibility(View.VISIBLE);
                activityTvShowDetailsBinding.llLayoutMisc.setVisibility(View.VISIBLE);
                activityTvShowDetailsBinding.viewDivider2.setVisibility(View.VISIBLE);

                loadBasicInfoTvShowDetails();

                setReadMore();
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

    private void loadBasicInfoTvShowDetails() {

        if (tvShowsItems != null) {

            activityTvShowDetailsBinding.setTvShowName(tvShowsItems.getName());
            activityTvShowDetailsBinding.tvShowName.setVisibility(View.VISIBLE);

            activityTvShowDetailsBinding.setNetworkCountry(tvShowsItems.getNetwork() + " (" + tvShowsItems.getCountry() + ")");
            activityTvShowDetailsBinding.tvShowNetworkCountry.setVisibility(View.VISIBLE);

            activityTvShowDetailsBinding.setStartedDate(tvShowsItems.getStart_date());
            activityTvShowDetailsBinding.tvShowStarted.setVisibility(View.VISIBLE);

            activityTvShowDetailsBinding.setStatus(tvShowsItems.getStatus());
            activityTvShowDetailsBinding.tvShowStatus.setVisibility(View.VISIBLE);
        }
    }

    private void setReadMore() {

        activityTvShowDetailsBinding.tvReadMore.setVisibility(View.VISIBLE);
        activityTvShowDetailsBinding.tvReadMore.setOnClickListener(view->{
            if (activityTvShowDetailsBinding.tvReadMore.getText().toString().equals("Read More")) {
                activityTvShowDetailsBinding.tvShowDescription.setMaxLines(Integer.MAX_VALUE);
                activityTvShowDetailsBinding.tvShowDescription.setEllipsize(null);
                activityTvShowDetailsBinding.tvReadMore.setText(R.string.tvReadLess);
            } else {
                activityTvShowDetailsBinding.tvShowDescription.setMaxLines(4);
                activityTvShowDetailsBinding.tvShowDescription.setEllipsize(TextUtils.TruncateAt.END);
                activityTvShowDetailsBinding.tvReadMore.setText(R.string.tvReadMore);
            }
        });
    }
}