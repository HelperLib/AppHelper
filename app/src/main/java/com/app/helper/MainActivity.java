package com.app.helper;

import android.content.ContextWrapper;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.app.helperlibrary.control.AdmobHelp;
import com.app.helperlibrary.control.Prefs;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // declare pref in my app

        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();

        // for dialog



//        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//
//
//            }
//
//            @Override
//            public void onLongClick(View view, int position) {
//            }
//        }));




//        AdmobNativeAdAdapter admobNativeAdAdapter=AdmobNativeAdAdapter.Builder.with(
//                        nativeAdId,//Create a native ad id from admob console
//                        currentAdapter,//The adapter you would normally set to your recyClerView
//                        NativeAdsType//Set it with "small","medium" or "custom"
//                )
//                .adItemIterval(interval)//native ad repeating interval in the recyclerview
//                .build();
//        recyclerView.setAdapter(admobNativeAdAdapter);//set your RecyclerView adapter with the admobNativeAdAdapter
    }
}
