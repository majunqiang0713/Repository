package com.example.aidl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SpecialBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.SCREEN_OFF")) {
            System.out.println("屏幕锁屏了");
        } else if (intent.getAction().equals("android.intent.action.SCREEN_ON")) {
            System.out.println("屏幕解锁了");
        }

    }
}
