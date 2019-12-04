package com.example.test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.PrintWriter;
import java.io.StringWriter;

public class MyService extends Service {
    private static final String TAG = "MyService";

    public MyService(){

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() is called.");
        Toast.makeText(this, "onCreate() is called.", Toast.LENGTH_LONG).show();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() is called");
        Toast.makeText(this, "onStartCommand() is called.", Toast.LENGTH_LONG).show();

        if(intent == null){
            return Service.START_STICKY;
        } else {
            processCommand(intent);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent) {
        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");

        Log.d(TAG, "Received Data : " + command+" / "+name);
        Toast.makeText(this, "Received Data : " + command+" / "+name, Toast.LENGTH_LONG).show();

        try {
            Thread.sleep(5000);
        } catch (Exception e){

            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String errorMsg = sw.toString();

            Log.d("ERROR", errorMsg);
        }

        //showIntent To MainActivity
        Intent showIntent = new Intent(getApplicationContext(), MainActivity.class);

        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        showIntent.putExtra("command", "show");
        showIntent.putExtra("name", name+" from service.");
        startActivity(showIntent);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() is called.");
        Toast.makeText(this, "onDestroy() is called.", Toast.LENGTH_LONG).show();


    }
}
