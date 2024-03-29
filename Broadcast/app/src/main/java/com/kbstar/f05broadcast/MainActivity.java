package com.kbstar.broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AndPermission
                .with(this)
                .runtime()
                .permission(Permission.RECEIVE_SMS)
                .onGranted(new Action<List<String>>(){
                    @Override
                    public void onAction(List<String> permit) {
                        Log.d("SMSRCV", "---------------- permit size = " + permit.size());
                    }
                })
                .onDenied(new Action<List<String>>(){
                    @Override
                    public void onAction(List<String> permit) {
                        Log.d("SMSRCV", "---------------- permit size (D) = " + permit.size());
                    }
                })
                .start();
    }
}