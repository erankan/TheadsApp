package erankan.mythreads;

import android.os.Handler;
import android.os.Message;



public class MyThreads implements Runnable{

    private Handler handler;

    public MyThreads(Handler handler){
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                if (!Thread.currentThread().isInterrupted()){
                    Message msg = Message.obtain();
                    msg.arg1 = i + 1;
                    handler.sendMessage(msg);
                    Thread.sleep(500);
                }
            }
            Message msgEnd = Message.obtain();
            msgEnd.arg1 = -1;
            handler.sendMessage(msgEnd);
        }
        catch (InterruptedException e){
            Message cancelMsg = Message.obtain();
            cancelMsg.arg1 = -2;
            handler.sendMessage(cancelMsg);
        }
    }
}
