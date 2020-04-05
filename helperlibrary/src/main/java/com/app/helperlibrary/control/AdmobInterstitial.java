package com.app.helperlibrary.control;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class AdmobInterstitial {
    private static boolean isPurchase = false;
    private static AdmobInterstitial mInstance;
    private InterstitialAd interstitial;
    private AdRequest adRequest;
    private AdmobCallback myCallback;
    private boolean isshow = true;

    public static AdmobInterstitial getInstance() {
        if (mInstance == null) {
            mInstance = new AdmobInterstitial();
        }
        return mInstance;
    }

    public void loadAdmobIntertialads(final AppCompatActivity context,String fullscreen_id,String AdmobID) {
        MobileAds.initialize(context,AdmobID);
        interstitial = new InterstitialAd(context);
        interstitial.setAdUnitId(fullscreen_id);
        adRequest = new AdRequest.Builder().build();
        interstitial.loadAd(adRequest);
        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                if (myCallback != null) {
                    myCallback.callbackCall();
                    myCallback = null;
                }
                interstitial.loadAd(adRequest);
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();

            }
        });
    }

    public void displayAdmobInterstitial(Context context, AdmobCallback _myCallback) {
        this.myCallback = _myCallback;

        if (AdmobInterstitial.getInstance().isPurchase) {
            if (myCallback != null) {
                myCallback.callbackCall();
                myCallback = null;
            }
        } else {
            if (!isshow) {
                if (myCallback != null) {
                    myCallback.callbackCall();
                    myCallback = null;
                }
            } else {
                if (interstitial != null) {
                    if (interstitial.isLoaded()) {
                        interstitial.show();

                    } else if (!interstitial.isLoading()) {
                        interstitial.loadAd(adRequest);
                        if (myCallback != null) {
                            myCallback.callbackCall();
                            myCallback = null;
                        }
                    } else {
                        if (myCallback != null) {
                            myCallback.callbackCall();
                            myCallback = null;
                        }
                    }
                } else {
                    if (myCallback != null) {
                        myCallback.callbackCall();
                        myCallback = null;
                    }
                }
            }
        }
    }

    public interface AdmobCallback {
        void callbackCall();
    }
}