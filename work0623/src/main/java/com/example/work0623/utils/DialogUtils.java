package com.example.work0623.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by john on 2018/6/21.
 */

public class DialogUtils {
    public static ProgressDialog getProgressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("正在删除...");
        progressDialog.setCancelable(false);
        return progressDialog;
    }
}
