package com.app.helperlibrary.control;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ViewGroup;

import com.app.helperlibrary.R;


public class CustomProgressDialog extends androidx.appcompat.app.AlertDialog {

    public static class Builder {

        private Context context;

        private boolean cancelable = true;
        private OnCancelListener cancelListener;

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setCancelListener(OnCancelListener cancelListener) {
            this.cancelListener = cancelListener;
            return this;
        }

        public CustomProgressDialog build() {
            return new CustomProgressDialog(
                    context,
                    cancelable,
                    cancelListener
            );
        }
    }

    private CustomProgressDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context);
        setCancelable(cancelable);
        if (cancelListener != null) setOnCancelListener(cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_progress);
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onStart() {
        if (getWindow() != null) {
            getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

}
