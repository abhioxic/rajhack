package com.example.rohit.kheti;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dd.processbutton.iml.ActionProcessButton;
import com.goodiebag.pinview.Pinview;
import com.klinker.android.send_message.Message;
import com.klinker.android.send_message.Settings;
import com.klinker.android.send_message.Transaction;

import org.json.JSONException;
import org.json.JSONObject;

public class VerifyOtp extends AppCompatActivity implements loginclasshelper.OnCompleteListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        final loginclasshelper progressGenerator = new loginclasshelper(this);
        final ActionProcessButton btnOtp = (ActionProcessButton) findViewById(R.id.btnOtp);
        Bundle bundle = getIntent().getExtras();

//        if(bundle.getString("phone")!= null)
//        {
            Settings settings = new Settings();
            settings.setUseSystemSending(true);
            Transaction transaction = new Transaction(getApplicationContext(), settings);
            JSONObject senddata = new JSONObject();
            String ph;
//            ph = bundle.getString("phone");
            ph="9426210564";
            try {
                senddata.put("phone",ph);
            } catch (JSONException e) {
                e.printStackTrace();
            }

//        }
        btnOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressGenerator.start(btnOtp);
                btnOtp.setEnabled(false);
                btnOtp.setMode(ActionProcessButton.Mode.ENDLESS);
                Settings settings = new Settings();
                settings.setUseSystemSending(true);
                Transaction transaction = new Transaction(getApplicationContext(), settings);
                JSONObject senddata = new JSONObject();
                try {
                    Pinview pin = new Pinview(VerifyOtp.this);
                    pin = (Pinview) findViewById(R.id.pinview1);
                    senddata.put("otp",pin.getValue());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String zz = "RJOTPA"+senddata.toString();
                Message message = new Message(zz, "9426210564");
                transaction.sendNewMessage(message,Transaction.NO_THREAD_ID);
                Intent i = new Intent(VerifyOtp.this,MainActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onComplete() {

    }
}
