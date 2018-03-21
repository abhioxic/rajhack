package com.example.rohit.kheti;

import com.dd.processbutton.ProcessButton;
import com.dd.processbutton.iml.ActionProcessButton;

import android.os.Handler;

import java.util.Random;

public class loginclasshelper {

    public interface OnCompleteListener {

        public void onComplete();
    }

    private OnCompleteListener mListener;
    private int mProgress;

    public loginclasshelper(OnCompleteListener listener) {
        mListener = listener;
    }

    public void start(final ProcessButton button) {
        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mProgress += 10;
//                button.setProgress(mProgress);
//                if (mProgress < 100) {
//                    handler.postDelayed(this, generateDelay());
//                } else {
//                    mListener.onComplete();
//                }
//            }
//        }, generateDelay());
        // you can display endless google like progress indicator

// set progress > 0 to start progress indicator animation
        button.setProgress(1);
    }

    private Random random = new Random();

    private int generateDelay() {
        return random.nextInt(1000);
    }
}