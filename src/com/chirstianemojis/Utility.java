package com.chirstianemojis;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by wirate on 8/19/14.
 */
public class Utility {
    private Utility() {
    }

    public static void simpleAlert(String title, String msg, Context context) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(msg)
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface v, int n) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
