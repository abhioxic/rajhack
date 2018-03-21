package com.example.rohit.kheti;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.klinker.android.send_message.Message;
import com.klinker.android.send_message.Settings;
import com.klinker.android.send_message.Transaction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements loginclasshelper.OnCompleteListener {

    public static final String EXTRAS_ENDLESS_MODE = "EXTRAS_ENDLESS_MODE";
    public static final String broadcastJSON="com.example.rohit.kheti.LOGIN";
    int REQUEST_READ_SMS=123;
    int REQUEST_READ_PHONE_STATE=123;
    IntentFilter intentFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        intentFilter=new IntentFilter();
        intentFilter.addAction(broadcastJSON);
        this.registerReceiver(broadcastReceiver,intentFilter);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        } else {
            //TODO
        }
        int permissionCheck1 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);

        if (permissionCheck1 != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, REQUEST_READ_SMS);
        } else {
            //TODO
        }
        int permissionCheck2 = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);

        if (permissionCheck2 != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, REQUEST_READ_SMS);
        } else {
            //TODO
        }
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final EditText editbhamal = (EditText) findViewById(R.id.editbhama);
        final EditText editadhar = (EditText) findViewById(R.id.editadhar);
        TextView toptext = (TextView)findViewById(R.id.logintoptext);
        Typeface typeface = Typeface.createFromAsset(getAssets(),
                "Oxygen-Bold.ttf");
        toptext.setTypeface(typeface);
        final loginclasshelper progressGenerator = new loginclasshelper(this);
        final ActionProcessButton btnSignIn = (ActionProcessButton) findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressGenerator.start(btnSignIn);
                btnSignIn.setEnabled(false);
                editbhamal.setEnabled(false);
                editadhar.setEnabled(false);
                btnSignIn.setMode(ActionProcessButton.Mode.ENDLESS);
                Settings settings = new Settings();
                settings.setUseSystemSending(true);
                Transaction transaction = new Transaction(getApplicationContext(), settings);
                JSONObject senddata = new JSONObject();
                try {
                    senddata.put("bhama",editbhamal.getText());
                    senddata.put("adhar",editadhar.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String zz = "RJLOG"+senddata.toString();
                zz=zz.replace("\"","'");
                Message message = new Message(zz, "8000374228");
                transaction.sendNewMessage(message,Transaction.NO_THREAD_ID);

            }
        });


    }


    private BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        int ii=0;
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(broadcastJSON))
            {
                String s = intent.getStringExtra("data");
                Log.v("APPLIRECE",s);
                try {
                    JSONObject js = new JSONObject(s);
                    JSONArray js1 = js.getJSONArray("info");
                    if(js1.getString(0).equals("fail")){
                        Toast.makeText(LoginActivity.this,"INCORRECT CREDENTIALS",Toast.LENGTH_LONG).show();
                    }else{
                        Intent i = new Intent(LoginActivity.this, VerifyOtp.class);
                        i.putExtra("phone",3);
                        i.putExtra("name",2);
                        finish();  //Kill the activity from which you will go to next activity
                        startActivity(i);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext())
//                        .setSmallIcon(R.drawable.home1)
//                        .setContentTitle("Server Notification")
//                        .setContentText(s)
//                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
//                notificationManager.notify(ii, mBuilder.build());
//                ii++;
            }

        }
    };

    @Override
    protected void onStop()
    {
        unregisterReceiver(broadcastReceiver);
        super.onStop();
    }


    @Override
    public void onComplete() {

    }
}
