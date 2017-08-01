package com.example.navi.tecnocarappv3.view;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Isai on 15/07/2017.
 */
public class MyDialogProgress {

    private static MyDialogProgress INSTANCE;
    private static ProgressDialog progressBar;
    private static Context mContext;

    public static MyDialogProgress getInstance(Context context) {
        if (INSTANCE == null) {
            mContext = context;
            INSTANCE = new MyDialogProgress();
            initProgressBar();
        }
        return INSTANCE;
    }

    private MyDialogProgress() {

    }

    public void show(String message) {
        if (!progressBar.isShowing()) {
            progressBar.setMessage(message);
            progressBar.show();
        }

    }

    public void dismiss() {
        if (progressBar.isShowing())
            progressBar.dismiss();
    }


    private static void initProgressBar() {
        progressBar = new ProgressDialog(mContext);
        progressBar.setCancelable(false);
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setProgress(0);
        progressBar.setMax(100);
    }
}
