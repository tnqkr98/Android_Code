package com.tnqkr98.service_practice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class BoundService extends Service {

    private int t = 0;

    public class LocalBinder extends Binder {
        public BoundService getService() {      // 이것으로 바인딩된 서비스를 컴포넌트가 참조획득 가능 (인터페이스용, 서비스에 정의된 각종 메서드를 이용가능)
            return BoundService.this;
        }
    }

    private final IBinder mBinder = new BoundService.LocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("Bound Service", "onBind");
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Bound Service", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Bound Service", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    public void FunctionAPI(){
        Log.d("Bound Service","function call");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // 각종 메모리 해제를 수행한다.
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
