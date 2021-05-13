package com.tnqkr98.service_practice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class StartedService extends Service {

    private static int t = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("Started Service", "onBind");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Started Service", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        t++;
        Log.d("Started Service", "onStartCommand " + t + "times");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
