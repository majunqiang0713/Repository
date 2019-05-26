package com.example.aidl;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class TsetService extends Service  {
    public static final String TAG = "TsetService";
    private SpecialBroadCastReceiver mSpecialBroadCastReceiver;

    public TsetService() {
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "服务开启了onCreate() ");
        super.onCreate();
        //注册屏幕的解锁锁屏，只能用代码注册
        mSpecialBroadCastReceiver = new SpecialBroadCastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        registerReceiver(mSpecialBroadCastReceiver,intentFilter );

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy()服务销毁了");
        if(mSpecialBroadCastReceiver != null){
            unregisterReceiver(mSpecialBroadCastReceiver);
        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "服务绑定了onBind");
    return new MyBinder();

    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "服务解绑了onUnbind（）");
        return super.onUnbind(intent);
    }

    public void methodInServices() {
        System.out.println("我是服务里面的方法");
    }

    public class MyBinder extends Binder implements Iservice {
        @Override
        public void callInServices() {
            methodInServices();
        }
    }


}
