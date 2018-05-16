package erankan.mythreads;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThreadsActivity extends AppCompatActivity implements View.OnClickListener{

    TextView counter;
    Button createButton, startButton, cancelButton;
    Thread thread;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.threads_activity);
        counter = findViewById(R.id.threads_counter);
        createButton = findViewById(R.id.threads_create_btn);
        startButton = findViewById(R.id.threads_start_btn);
        cancelButton = findViewById(R.id.threads_cancel_btn);

        createButton.setOnClickListener(this);
        startButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

        startButton.setClickable(false);
        cancelButton.setClickable(false);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String value = String.valueOf(msg.arg1);
                if (msg.arg1 >= 0){
                    counter.setText(value);
                }
                else if (msg.arg1 == -1){
                    counter.setText("Done");
                }
                else if (msg.arg1 == -2){
                    counter.setText("counter");
                }
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.threads_create_btn:
                thread = new Thread(new MyThreads(handler));
                startButton.setClickable(true);
                lockScreenOrient();
                break;
            case R.id.threads_start_btn:
                thread.start();
                cancelButton.setClickable(true);
                break;
            case R.id.threads_cancel_btn:
                thread.interrupt();
                unlockScreenOrient();
                break;
        }
    }


    private void lockScreenOrient(){
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    private void unlockScreenOrient(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
    }
}
