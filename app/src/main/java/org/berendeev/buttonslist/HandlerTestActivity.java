package org.berendeev.buttonslist;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by roma on 02.03.2017.
 */

public class HandlerTestActivity extends AppCompatActivity {

    Button button;
    TextView textView;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        button = (Button) findViewById(R.id.handler_button);
        textView = (TextView) findViewById(R.id.handler_text_view);
        final Handler myHandler = new Handler(){
            @Override public void handleMessage(Message msg) {
                textView.setText("" + msg.what);
            }
        };
        button.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                new MyThread(myHandler).start();
            }
        });

    }


    private class MyThread extends Thread{

        private Handler handler;

        public MyThread(Handler handler){
            this.handler = handler;
        }

        @Override public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.sendEmptyMessage((int)System.currentTimeMillis());
        }
    }

    @Override protected void onPause() {
        super.onPause();

    }

    private class MyHandlerThread extends HandlerThread{

        public MyHandlerThread(String name) {
            super(name);
        }

        public MyHandlerThread(String name, int priority) {
            super(name, priority);
        }

        public void post(){
            this.isAlive();
            this.isInterrupted();
            this.quit();
        }
    }
}
