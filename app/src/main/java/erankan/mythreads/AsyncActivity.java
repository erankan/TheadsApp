package erankan.mythreads;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AsyncActivity extends AppCompatActivity implements View.OnClickListener {


    TextView counter;
    Button createButton, startButton, cancelButton;
    AsyncThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asynctask_activity);
        counter = findViewById(R.id.async_counter);
        createButton = findViewById(R.id.async_create_btn);
        startButton = findViewById(R.id.async_start_btn);
        cancelButton = findViewById(R.id.async_cancel_btn);

        createButton.setOnClickListener(this);
        startButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

        startButton.setClickable(false);
        cancelButton.setClickable(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.async_create_btn:
                thread = new AsyncThread(counter);
                startButton.setClickable(true);
                lockScreenOrient();
                break;
            case R.id.async_start_btn:
                thread.execute();
                cancelButton.setClickable(true);
                break;
            case R.id.async_cancel_btn:
                thread.cancel(true);
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
