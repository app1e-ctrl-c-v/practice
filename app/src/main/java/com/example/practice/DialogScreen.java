package com.example.practice;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class DialogScreen {
    public static AlertDialog getDialog(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle(R.string.error_title);
                builder.setMessage(R.string.error_description);
                builder.setCancelable(true);
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                return builder.create();
    }
}
