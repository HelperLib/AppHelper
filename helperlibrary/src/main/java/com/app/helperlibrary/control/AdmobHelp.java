package com.app.helperlibrary.control;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.helperlibrary.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;

public class AdmobHelp {
    private static AdmobHelp instance;
    InterstitialAd mPublisherInterstitialAd;
    private AdCloseListener adCloseListener;
    private boolean isReloaded = false;
    AdView adView;
    private UnifiedNativeAd nativeAd;
    public static long timeLoad=0;
    public static long selftime=50*1000;

    public static AdmobHelp getInstance() {
        if (instance == null) {
            instance = new AdmobHelp();
        }
        return instance;
    }

    private AdmobHelp() {

    }

    public void init(Context context,String AdmobID,String AdmobFullScreenID) {
        MobileAds.initialize(context,AdmobID);
        mPublisherInterstitialAd = new InterstitialAd(context);
        mPublisherInterstitialAd.setAdUnitId(AdmobFullScreenID);
        mPublisherInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                if (!isReloaded) {
                    isReloaded = true;
                    loadInterstitialAd();
                }
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
                if (adCloseListener != null) {
                    adCloseListener.onAdClosed();
                    loadInterstitialAd();
                }
            }
        });
        loadInterstitialAd();
    }

    private void loadInterstitialAd() {
        if (mPublisherInterstitialAd != null && !mPublisherInterstitialAd.isLoading() && !mPublisherInterstitialAd.isLoaded()) {
            mPublisherInterstitialAd.loadAd(new AdRequest.Builder().build());
        }
    }

    public void showInterstitialAd(long TimeReload,Boolean isTimeBased,AdCloseListener adCloseListener,boolean isDefaultTime) {
        if(isDefaultTime){
            if((timeLoad+selftime)<System.currentTimeMillis()){
                if (canShowInterstitialAd()) {
                    this.adCloseListener = adCloseListener;
                    mPublisherInterstitialAd.show();
                    timeLoad = System.currentTimeMillis();
                } else {

                    adCloseListener.onAdClosed();
                }
            }else{
                adCloseListener.onAdClosed();
            }
        }else {
            if(isTimeBased){
                if((timeLoad+TimeReload)<System.currentTimeMillis()){
                    if (canShowInterstitialAd()) {
                        this.adCloseListener = adCloseListener;
                        mPublisherInterstitialAd.show();
                        timeLoad = System.currentTimeMillis();
                    } else {

                        adCloseListener.onAdClosed();
                    }
                }else{
                    adCloseListener.onAdClosed();
                }
            }else {
                if (canShowInterstitialAd()) {
                    this.adCloseListener = adCloseListener;
                    mPublisherInterstitialAd.show();
                } else {

                    adCloseListener.onAdClosed();
                }
            }
        }



    }

    private boolean canShowInterstitialAd() {
        return mPublisherInterstitialAd != null && mPublisherInterstitialAd.isLoaded();
    }

    public interface AdCloseListener {
        void onAdClosed();
    }

    public  void loadBanner(final AppCompatActivity mActivity, String AdmobBannerID, final AdCloseListener adCloseListener) {
        final ShimmerFrameLayout containerShimmer =
                mActivity.findViewById(R.id.shimmer_container);

        containerShimmer.setVisibility(View.VISIBLE);
        containerShimmer.startShimmer();
        final LinearLayout adContainer = mActivity.findViewById(R.id.banner_container);

        try {
              adView = new AdView(mActivity);
            adView.setAdUnitId(AdmobBannerID);
            adContainer.addView(adView);
            AdSize adSize = getAdSize(mActivity);
            // Step 4 - Set the adaptive ad size on the ad view.
            adView.setAdSize(adSize);


            adView.loadAd(new AdRequest.Builder().build());
            adView.setAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                    adContainer.setVisibility(View.GONE);
                    containerShimmer.stopShimmer();
                    containerShimmer.setVisibility(View.GONE);
                    adCloseListener.onAdClosed();
                }
                @Override
                public void  onAdLoaded(){
                    containerShimmer.stopShimmer();
                    containerShimmer.setVisibility(View.GONE);
                    adContainer.setVisibility(View.VISIBLE);


                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private AdSize getAdSize(AppCompatActivity mActivity) {
        // Step 2 - Determine the screen width (less decorations) to use for the ad width.
        Display display = mActivity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;

        int adWidth = (int) (widthPixels / density);

        // Step 3 - Get adaptive ad size and return for setting on the ad view.
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(mActivity, adWidth);

    }
    public  void loadBannerFragment(final AppCompatActivity mActivity, final View rootView, String AdmobBannerID, final AdCloseListener adCloseListener) {
        final ShimmerFrameLayout containerShimmer =
                rootView.findViewById(R.id.shimmer_container);

        containerShimmer.setVisibility(View.VISIBLE);
        containerShimmer.startShimmer();
        final LinearLayout adContainer = rootView.findViewById(R.id.banner_container);

        try {
              adView = new AdView(mActivity);
            adView.setAdUnitId(AdmobBannerID);
            adContainer.addView(adView);
            AdSize adSize = getAdSize(mActivity);
            // Step 4 - Set the adaptive ad size on the ad view.
            adView.setAdSize(adSize);


            adView.loadAd(new AdRequest.Builder().build());
            adView.setAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                    adContainer.setVisibility(View.GONE);
                    containerShimmer.stopShimmer();
                    containerShimmer.setVisibility(View.GONE);
                    adCloseListener.onAdClosed();
                }
                @Override
                public void  onAdLoaded(){
                    containerShimmer.stopShimmer();
                    containerShimmer.setVisibility(View.GONE);
                    adContainer.setVisibility(View.VISIBLE);


                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadNative(final AppCompatActivity mActivity, String NativeAdmobId, final AdCloseListener adCloseListener){
        final ShimmerFrameLayout containerShimmer =
                mActivity.findViewById(R.id.shimmer_container);
        containerShimmer.setVisibility(View.VISIBLE);
        containerShimmer.startShimmer();
        VideoOptions videoOptions = new VideoOptions.Builder()
                .setStartMuted(false)
                .build();

        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                .setVideoOptions(videoOptions)
                .build();


        AdLoader adLoader = new AdLoader.Builder(mActivity, NativeAdmobId)
                .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        // Show the ad.
                        if (nativeAd != null) {
                            nativeAd.destroy();
                        }
                        containerShimmer.stopShimmer();
                        containerShimmer.setVisibility(View.GONE);

                        nativeAd = unifiedNativeAd;
                        FrameLayout frameLayout =
                                mActivity.findViewById(R.id.fl_adplaceholder);
                        if(frameLayout!=null){
                            frameLayout.setVisibility(View.VISIBLE);
                            UnifiedNativeAdView adView = (UnifiedNativeAdView) mActivity.getLayoutInflater()
                                    .inflate(R.layout.native_admob_ad, null);
                            populateUnifiedNativeAdView(unifiedNativeAd, adView);
                            frameLayout.removeAllViews();
                            frameLayout.addView(adView);
                        }

                    }
                })
                .withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        // Handle the failure by logging, altering the UI, and so on.
                        containerShimmer.stopShimmer();
                        containerShimmer.setVisibility(View.GONE);
                        adCloseListener.onAdClosed();
                    }
                })
                .withNativeAdOptions(adOptions)
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());
    }
    public void loadNativeFragment(final AppCompatActivity mActivity, final View rootView, String AdmobNativeId, final AdCloseListener adCloseListener){
        final ShimmerFrameLayout containerShimmer =
                rootView.findViewById(R.id.shimmer_container);
        containerShimmer.setVisibility(View.VISIBLE);
        containerShimmer.startShimmer();
        VideoOptions videoOptions = new VideoOptions.Builder()
                .setStartMuted(false)
                .build();

        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                .setVideoOptions(videoOptions)
                .build();


        AdLoader adLoader = new AdLoader.Builder(mActivity,AdmobNativeId)
                .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        // Show the ad.
                        if (nativeAd != null) {
                            nativeAd.destroy();
                        }
                        containerShimmer.stopShimmer();
                        containerShimmer.setVisibility(View.GONE);

                        nativeAd = unifiedNativeAd;
                        FrameLayout frameLayout =
                                rootView.findViewById(R.id.fl_adplaceholder);
                        if(frameLayout!=null){
                            frameLayout.setVisibility(View.VISIBLE);
                            UnifiedNativeAdView adView = (UnifiedNativeAdView) mActivity.getLayoutInflater()
                                    .inflate(R.layout.native_admob_ad, null);
                            populateUnifiedNativeAdView(unifiedNativeAd, adView);
                            frameLayout.removeAllViews();
                            frameLayout.addView(adView);
                        }

                    }
                })
                .withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        // Handle the failure by logging, altering the UI, and so on.
                        containerShimmer.stopShimmer();
                        containerShimmer.setVisibility(View.GONE);
                        adCloseListener.onAdClosed();
                    }
                })
                .withNativeAdOptions(adOptions)
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());
    }
    private void populateUnifiedNativeAdView(UnifiedNativeAd nativeAd, UnifiedNativeAdView adView) {


        MediaView mediaView = adView.findViewById(R.id.ad_media);
        adView.setMediaView(mediaView);

        // Set other ad assets.
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));

        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));

        // The headline is guaranteed to be in every UnifiedNativeAd.
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((TextView) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        // This method tells the Google Mobile Ads SDK that you have finished populating your
        // native ad view with this native ad. The SDK will populate the adView's MediaView
        // with the media content from this native ad.
        adView.setNativeAd(nativeAd);

    }
    public void destroyNative(){
        if (nativeAd != null) {
            nativeAd.destroy();
        }

    }
    public void destroyBanner(){
        if (adView != null) {
            adView.destroy();
        }

    }
    public void resumeBanner(){
        if (adView != null) {
            adView.resume();
        }

    }
    public void pauseBanner(){
        if (adView != null) {
            adView.pause();
        }

    }
}
