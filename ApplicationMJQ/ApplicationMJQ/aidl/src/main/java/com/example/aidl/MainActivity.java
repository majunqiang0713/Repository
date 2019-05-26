package com.example.aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.mjq.drawboard.DrawingBoardActivity;
import com.mjq.zoompitcure.ZoomingActivity;

/**
 * 调用服务里面的方法水示例
 */
public class MainActivity extends AppCompatActivity {
    private Iservice mMyBinder;
    private MyServiceConnection mMyServiceConnection;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIntent = new Intent(this, TsetService.class);
        mMyServiceConnection = new MyServiceConnection();
        bindService(mIntent, mMyServiceConnection, BIND_AUTO_CREATE);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv1:
                //调用服务里面的方法
                callServiceMethod();
            break;
            case R.id.tv2:
                //混合方式开启服务
                mutileStartService();
            break;
            case R.id.tv3:
                //在服务里面注册广播接收者
             regeistBroadCastReceiver();
            break;
            case R.id.tv4:
            Intent intent = new Intent(this, ZoomingActivity.class);
            startActivity(intent);
            break;
            case R.id.tv5:
            Intent intentdraw = new Intent(this, DrawingBoardActivity.class);
            startActivity(intentdraw);
            break;
            default:

        }
        //调用服务里面的方法
       // callServiceMethod();

    }

    private void regeistBroadCastReceiver() {

        startService(mIntent);
    }

    public void mutileStartService(){
       Intent intent = new Intent(this,TsetService.class);
        startService(intent);
        bindService(intent, mMyServiceConnection, BIND_AUTO_CREATE);
        unbindService(mMyServiceConnection);
        stopService(intent);

    }




    private class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMyBinder = (Iservice) service;
            Log.i(" MainActivity ", "服务绑定成功");


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(" MainActivity ", "服务绑定失败");
        }
    }

    /**
     * 调用服务里面的方法
     */
    public void callServiceMethod() {
        mMyBinder.callInServices();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mMyServiceConnection);
    }
}
