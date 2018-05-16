package erankan.mythreads;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.TextView;

public class AsyncThread extends AsyncTask<Object, Integer, Void> {

    @SuppressLint("StaticFieldLeak")
    private TextView textView;

    public AsyncThread(TextView v){
        textView = v;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        textView.setText("");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        textView.setText("Done");
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        textView.setText(String.valueOf(values[0]));
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        textView.setText("counter");
    }

    @Override
    protected Void doInBackground(Object... objects) {
        for (int i = 0; i < 10; i++) {
            if (isCancelled()){
                break;
            }
            publishProgress(i+1);
            SystemClock.sleep(500);
        }
        return null;
    }
}
