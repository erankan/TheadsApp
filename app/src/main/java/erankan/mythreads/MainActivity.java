package erankan.mythreads;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button asyncButton = findViewById(R.id.async_button);
        Button myThreadButton = findViewById(R.id.my_threads_button);

        asyncButton.setOnClickListener(this);
        myThreadButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.async_button:
                Intent asyncIntent = new Intent(this, AsyncActivity.class);
                startActivity(asyncIntent);
                break;
            case R.id.my_threads_button:
                Intent myThreadsIntent = new Intent(this, ThreadsActivity.class);
                startActivity(myThreadsIntent);
                break;
        }
    }
}
