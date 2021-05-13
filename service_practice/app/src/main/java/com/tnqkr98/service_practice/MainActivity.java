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

public class MainActivity extends AppCompatActivity {

    private BoundService mBoundService = null;

    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBoundService = ((BoundService.LocalBinder)service).getService();

            mBoundService.FunctionAPI();    //  서비스 함수 호출가능!
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBoundService = null;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button bt = (Button)findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SubActivity.class));
            }
        });


        // Started Service 를 실행. 1회성 작업을 수행하는 용도
        Button bt2 = (Button)findViewById(R.id.button2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Main Activity","onclick - started service");
                startService(new Intent(getApplicationContext(),StartedService.class));
            }
        });

        // Bound Service 를 실행. 서버-클라이언트 구조 생성.
        Button bt3 = (Button)findViewById(R.id.button5);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Main Activity","onclick - bound service");

                Intent serviceIntent = new Intent(getApplicationContext(), BoundService.class);
                bindService(serviceIntent, mServiceConnection, BIND_AUTO_CREATE);
            }
        });

        Button bt4 = (Button)findViewById(R.id.button6);
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBoundService != null)
                    mBoundService.FunctionAPI(); //  서비스 함수 호출가능!
            }
        });

    }
}