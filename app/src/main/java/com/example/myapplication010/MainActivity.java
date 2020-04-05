package com.example.myapplication010;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    TextView txt_background;
    TextView txt_showTime;
    private int randomR;
    private int randomG;
    private int randomB;
    Handler handler = new Handler();
    Calendar calendar = Calendar.getInstance();
    String h = trimNum(calendar.get(Calendar.HOUR_OF_DAY));
    String m = trimNum(calendar.get(Calendar.MINUTE));
    String s = trimNum(calendar.get(Calendar.SECOND));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_background = findViewById(R.id.txt_background);
        txt_showTime = findViewById(R.id.txt_showTime);
        txt_showTime.setText(h + ":" + m + ":" + s);


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    //new Handler(Looper.getMainLooper()).post(new Runnable() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            calendar = Calendar.getInstance();
                            h = trimNum(calendar.get(Calendar.HOUR_OF_DAY));
                            m = trimNum(calendar.get(Calendar.MINUTE));
                            s = trimNum(calendar.get(Calendar.SECOND));
                            txt_showTime.setText(h + ":" + m + ":" + s);
                            Log.i("time", h + ":" + m + ":" + s);
                        }
                    });

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();


        txt_background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                randomR = random.nextInt(100);
                randomG = random.nextInt(100);
                randomB = random.nextInt(100);
                txt_background.setBackgroundColor(Color.rgb(100 + randomR, 100 + randomG, 100 + randomB));
            }
        });
    }

    public String trimNum (int n){
        String out;

        if(String.valueOf(n).length()==1){
            out = "0"+n;
        }else{
            out = ""+n;
        }

        return out;

    }
}
