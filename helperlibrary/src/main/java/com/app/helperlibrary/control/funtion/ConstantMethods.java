package com.app.helperlibrary.control.funtion;

import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.app.helperlibrary.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ConstantMethods {


    public static void ShowToastShort(Context mContext, String mtext) {
        Toast.makeText(mContext, mtext,
                Toast.LENGTH_SHORT).show();
    }

    public static void ShowToastLong(Context mContext, String mtext) {
        Toast.makeText(mContext, mtext,
                Toast.LENGTH_LONG).show();
    }

    public static void OpenMoreApp(Context mContext, String mStore) {
        try {
            Uri uri = Uri.parse(mStore);
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);

            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            mContext.startActivity(goToMarket);
        } catch (Exception e) {
            Toast.makeText(mContext, "Unable to find play store", Toast.LENGTH_LONG).show();
        }
    }

    public static void OpenBrowser(Context mContext, String mLink) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(mLink));
            mContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isPackageInstalled(String packageName, PackageManager packageManager) {
        try {
            return packageManager.getApplicationInfo(packageName, 0).enabled;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static void RateApp(Context mContext) {
        mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + mContext.getPackageName())));
    }
    public static String[] messageList = {"how are you babu?",
            "what?", "can I call you?",
            "what is your number?",
            "What is Your Name?",
            "I love You", "where are your from?",
            "Your favourite place?", "Nope", "You have girlfriends?",
            "You know my number?", "you want to my number?", "No", "Yes",
            "Hi handsome, how are you today?",
            "Hi Babu, good to see you here",
            "Your Number?",
            "What is your birth date?",
            "What is your age?",
            "What is your hobby?",
            "Can you give me some gifts?",
            "Please babu",
            "Please request",
            "What’s up",
            "How are you doing",
            "You’re smart",
            "Tell me something",
            "Hi, how are you?",
            "Hi, how is your day?",
            "Good morning handsome!",
            "Hi,I'm glad that you found me!",
            "I really want to have xxx with you, let's meet now in my apartment!",
            "I'm a real sax bomb in the bed, try me out!",
            "I will go buy some condos, meet me on my place in an hour, ok baby?",
            "Meet me in 2 hours, but remember it is only a xxx, nothing more!",
            "You are so lovely", "yes please", "how are you?"};
    public static String[] messageListHindi = {"क्या कर रहे हो?",
            "गुड़ मॉर्निंग",
            "आपको क्या अच्छा लगता हे ?",
            "क्या आप मुझे पसंद करते हो ?",
            "आपका बर्थडे कब  आता हे ?",
            "वो लेके आजाओ मेरे घर बादमे मज़े करते हे...",
            "आप बहुत अच्छे हो यार ",
            "आपको क्या खेलना पसंद हे? ",
            "प्लीज बाबू ",
            "आपकी उम्र क्या हे ?",
            "आपको डालना आता हे क्या ?",
            "कैसे डालते हे मुझे बताओ ना मज़ा आता हे..",
            "मुझे किस करना हे..",
            "आपका वो मुझे  देखना हे",
            "बाबू क्या कर रहे हो ?",
            "फ्री हो क्या ?",
            "मुझे घूमने जाना हे यार",
            "आओ ना मज़े करते हे",
            "आपके फ्रेंड हे क्या ?",
            "आपकी कोई गर्लफ्रेंड हे क्या ?",
            "आपको सच्ची आता हे क्या कभी करा हे आपने ?",
            "मज़ा आता होगा ना ?",
            "मेरा लॉलीपॉप क्या कर रहा हे बाबू ?",
            "लॉलीपॉप चूसने में बहुत मज़ा आता हे...",
            "मुँह में लू क्या ?",
            "आज तो खा जाउंगी लॉलीपॉप...",
            "आप स्कूल जाते हो क्या ?",
            "कितना बड़ा  हे आपका लॉलीपॉप ?",
            "क्या करने का मन हो रहा हे आपको ?",
            "आपको में अच्छी लगती हु क्या ?",
            "आप कितने अच्छे हो यार ",
            "हम रात १० बजे बाटे करेंगे ओके ",
            "मुझे अच्छे लड़के पसंद हे ",
            "चलो चलते हे घूमने...",
            "आपको घूमना अच्छा लगता हे क्या ?",
            "आपको कोनसा फ़ूड अच्छा लगता हे?",
            "मुझे पानी पूरी बहुत पसंद हे ?",
            "में सोने जा रही हु ",
            "आज रात ८ बजे फ्री हु में आप कॉल करना ",
            "छोटे लॉलीपॉप नहीं पसंद मुझे..",
            "आपका कोई भाई हे ?",
            "आपका नंबर दोना ",
            "चलो डालो मुजमे",
            "चूसने का मन हो रहा हे यार",
            "खोलो ना में चुस्ती हु.... मज़ा आएगा",
            "तुम्हारा खड़ा हो गया क्या ?",
            "लॉलीपॉप चूसना हे.. "};
    public static void SendFeedBack(Context mContext) {
        Intent intentEmail = new Intent(Intent.ACTION_SENDTO);
        intentEmail.setData(Uri.parse("mailto:feedbackapps@yahoo.com"));
        intentEmail.setType("text/plain");
        intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
        intentEmail.putExtra(Intent.EXTRA_TEXT, "Enter your FeedBack");

        try {
            mContext.startActivity(Intent.createChooser(intentEmail, "Send FeedBack..."));
        } catch (ActivityNotFoundException e) {
            ShowToastShort(mContext, "There is no email client installed.");
        }
    }


    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static boolean isConnectionAvailable(Context context) {
        boolean result = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (cm != null) {
                NetworkCapabilities capabilities = cm.getNetworkCapabilities(cm.getActiveNetwork());
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        result = true;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        result = true;
                    }
                }
            }
        } else {
            if (cm != null) {

                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                if (activeNetwork != null) {
                    // connected to the internet
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                        result = true;
                    } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                        result = true;
                    }
                }
            }
        }
        if (!result) {
            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
        return result;

    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static void setImageBackground(final Context mContext, int picture, final ImageView imageView) {
        if (mContext != null && imageView != null) {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    imageView.setImageDrawable(mContext.getResources().getDrawable(picture, mContext.getApplicationContext().getTheme()));

                } else {
                    imageView.setImageDrawable(mContext.getResources().getDrawable(picture));

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void setCardBackground(final Context mContext, int color, final CardView imageView) {
        if (mContext != null && imageView != null) {
            try {
                imageView.setCardBackgroundColor(mContext.getResources().getColor(color));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void shareApp(Context context) {
        final String appPackageName = context.getPackageName();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out the amazing App at: https://play.google.com/store/apps/details?id=" + appPackageName);
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }

    public static boolean isMyServiceRunning(Class<?> serviceClass, Context mContext) {
        ActivityManager manager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE))
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.i("isMyServiceRunning?", true + "");
                return true;
            }
        Log.i("isMyServiceRunning?", false + "");
        return false;
    }

    public static void setLanguageAds(Context mContext) {

        if (PreferenceManager.getDefaultSharedPreferences(mContext).getBoolean("Frist_ads", true)) {
            String language = PreferenceManager.getDefaultSharedPreferences(mContext).getString("lgAds", "en");
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("Frist_ads", false);
            language = Locale.getDefault().getLanguage();
            if (language.equalsIgnoreCase("vi")) {
                editor.putString("lgAds", "vi");

            }
            editor.commit();
        }


    }

    public static void setImageInt(Context context, ImageView imageView, int url) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(setPlaceholder())
                .priority(Priority.HIGH);
        Glide.with(context)
                .load(url)
                .apply(options)
                .error(setPlaceholder())
                .into(imageView);
    }

    public static void setImageString(Context context, ImageView imageView, String url) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(setPlaceholder())
                .priority(Priority.HIGH);
        Glide.with(context)
                .load(url)
                .apply(options)
                .error(setPlaceholder())
                .into(imageView);
    }




    public static void startBlinkingAnimation(Context context, ImageView imageView) {
        Animation startAnimation = AnimationUtils.loadAnimation(context, R.anim.blink);
        imageView.startAnimation(startAnimation);
    }

    private static ColorDrawable[] vibrantLightColorList =
            {
                    new ColorDrawable(Color.parseColor("#ffcdd2")), new ColorDrawable(Color.parseColor("#e1bee7")),
                    new ColorDrawable(Color.parseColor("#b39ddb")), new ColorDrawable(Color.parseColor("#90caf9")),
                    new ColorDrawable(Color.parseColor("#b388ff")), new ColorDrawable(Color.parseColor("#ff8a65")),
                    new ColorDrawable(Color.parseColor("#dce775")), new ColorDrawable(Color.parseColor("#ffcc80"))
            };

    public static ColorDrawable setPlaceholder() {
        int idx = new Random().nextInt(vibrantLightColorList.length);
        return vibrantLightColorList[idx];


    }

    public static String getTextLanguage(String mText, String key) {
        String[] temp = null;
        temp = mText.split(key + ":");
        if (temp.length == 1) {
            temp = mText.split("en" + ":");
        }
        if (temp.length == 1) {
            return temp[0];
        } else {
            String[] text = temp[1].split(",,");
            return text[0];
        }
    }


}
