package com.student.kppc.service_report01_lotto;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button startBtn, closeBtn, checkBtn;
    static LinearLayout resultL;
    Util util = new Util();
    MyService ms;
    boolean isService = false;
    Intent intent ;

    int[] r = null;
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyService.MyBinder mb = (MyService.MyBinder) iBinder;
            ms = mb.getService();
            isService = true;

        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isService = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutInit();

        startBtn.setOnClickListener(this);
        closeBtn.setOnClickListener(this);
        checkBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        intent = new Intent(MainActivity.this, MyService.class);
        switch (view.getId()) {
            case R.id.startBtn:
                //bindService(intent 객체, 서비스와 연결에 대한 정의, BIND_AUTO_CREATE);
                bindService(intent, connection, BIND_AUTO_CREATE);
                util.logTextView(this, resultL, "서비스 시작합니다.");
                break;
            case R.id.closeBtn:
                //unbindService(서비스와 연결에 대한 정의);
                util.logTextView(this, resultL, "서비스 종료되었습니다.");
                unbindService(connection);
                isService = false;
                break;
            case R.id.checkBtn:
                if (!isService) {
                    Toast.makeText(getApplicationContext(), "서비스중이 아닙니다.\n데이터를 받을 수 없습니다.", Toast.LENGTH_SHORT).show();
                    util.logTextView(this, resultL, "서비스중이 아닙니다. 데이터를 받을 수 없습니다.");
                    return;
                } else {
                    util.logTextView(this, resultL, "Lotto 숫자 : "+ Arrays.toString(ms.getRun()));
                }
        }
    }

    void layoutInit() {
        startBtn = findViewById(R.id.startBtn);
        closeBtn = findViewById(R.id.closeBtn);
        checkBtn = findViewById(R.id.checkBtn);
        resultL = findViewById(R.id.resultTV);
    }
}