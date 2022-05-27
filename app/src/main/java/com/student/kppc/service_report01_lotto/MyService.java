package com.student.kppc.service_report01_lotto;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    //TODO(study) 외부로 데이터를 전달하려면 바인더를 사용
    //TODO(study) Binder 객체는 IBinder 인터페이스 상속구현 객체입니다
    Util util = new Util();
    IBinder mBinder = new MyBinder();

    class MyBinder extends Binder {
        MyService getService() {  //서비스 객체를 리턴
            return MyService.this;
        }
    }

    @Override
    public void onCreate() {
        util.logTextView(getApplicationContext(), MainActivity.resultL, "onCreate() 호출" );

        super.onCreate();

    }
    int[] getRun() {
        return util.unReturnNumber();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        util.logTextView(getApplicationContext(), MainActivity.resultL, "onDestroy 호출" );
        Toast.makeText(this, "onDestroy() 호출", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        util.logTextView(getApplicationContext(), MainActivity.resultL, "onBind() 호출" );

        return mBinder;
    }
}