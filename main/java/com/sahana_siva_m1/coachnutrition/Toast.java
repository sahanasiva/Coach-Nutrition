package com.sahana_siva_m1.coachnutrition;

import android.content.Context;

public class Toast {

    private android.widget.Toast message;

    public Toast(Context context, String msg) {
        message = android.widget.Toast.makeText(context, msg, android.widget.Toast.LENGTH_LONG);
        message.show();
    }

}
