package com.tnqkr98.service_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SubActivity extends AppCompatActivity {
    private BoundService mBoundService = null;
    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBoundService = ((BoundService.LocalBinder)service).getService();
            mBoundService.FunctionAPI();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBoundService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Button bt = (Button)findViewById(R.id.button3);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Sub Activity","onclick - started service");
                startService(new Intent(getApplicationContext(),StartedService.class));
            }
        });

        Button bt2 = (Button)findViewById(R.id.button4);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Sub Activity","onclick - bound service");
                Intent serviceIntent = new Intent(getApplicationContext(), BoundService.class);
                bindService(serviceIntent, mServiceConnection, BIND_AUTO_CREATE);
            }
        });
    }
}