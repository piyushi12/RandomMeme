package com.example.randommeme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Myname extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myname);
        Thread thread=new Thread(){
            public void run(){
                try {
                    sleep(5000);

                }
                catch(Exception e){
                    e.printStackTrace();

                }
                finally {
                    Intent intent=new Intent(Myname.this,MainActivity.class);
                    startActivity(intent);
                }
            }

        };thread.start();

    }
}