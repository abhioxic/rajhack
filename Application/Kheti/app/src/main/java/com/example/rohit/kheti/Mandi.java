package com.example.rohit.kheti;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.klinker.android.send_message.Message;
import com.klinker.android.send_message.Settings;
import com.klinker.android.send_message.Transaction;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

public class Mandi extends AppCompatActivity{


private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
final private int REQUEST_READ_PHONE_STATE = 124;
    public static final String broadcastJSON="com.example.rohit.kheti.DSTDAT";
    private IntentFilter intentFilter;
    private WaveSwipeRefreshLayout mWaveSwipeRefreshLayout;
    private List<MandiListItem> mlist;
    private ArrayList<MandiListItem> myyList;
    BetterSpinner districtspinner;
    int selecteditem =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mandi);
        intentFilter=new IntentFilter();
        intentFilter.addAction(broadcastJSON);
        this.registerReceiver(broadcastReceiver,intentFilter);

        mRecyclerView = (RecyclerView) findViewById(R.id.mandi_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        if(ContextCompat.checkSelfPermission(getBaseContext(), "android.permission.READ_SMS") == PackageManager.PERMISSION_GRANTED) {
            Log.v("PERMISSION","GRANTED");
        }else{
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_SMS"}, REQUEST_CODE_ASK_PERMISSIONS);
            Log.v("PERMISSION","ASKED");
        }
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        }
        


        String districts[] = {"ABU ROAD","AJMER FV","AJMER GRAIN"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, districts);
        districtspinner = (BetterSpinner)
                findViewById(R.id.districtspinner);
        districtspinner.setAdapter(adapter);
        districtspinner.bringToFront();
        mRecyclerView.bringToFront();
        districtspinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (checkSelfPermission(Manifest.permission.SEND_SMS)
                        == PackageManager.PERMISSION_DENIED) {

                    Log.d("permission", "permission denied to SEND_SMS - requesting it");
                    String[] permissions = {Manifest.permission.SEND_SMS};

                    requestPermissions(permissions, REQUEST_CODE_ASK_PERMISSIONS);

                }else{
                    Log.d("permission", "permission allowed to SEND_SMS - requesting it");
                }

                if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
                        == PackageManager.PERMISSION_DENIED) {

                    Log.d("permission", "permission denied to READ_PHONE_STATE - requesting it");
                    String[] permissions = {Manifest.permission.READ_PHONE_STATE};

                    requestPermissions(permissions, REQUEST_CODE_ASK_PERMISSIONS);

                }else{
                    Log.d("permission", "permission allowed to READ_PHONE_STATE - requesting it");
                }
                Log.v("ITEM",String.valueOf(i));
                Log.v("ITEM",String.valueOf(l));
                selecteditem = i+1;
                Toast.makeText(Mandi.this,"Selected Item : "+String.valueOf(i),Toast.LENGTH_LONG).show();
                JSONObject senddata = new JSONObject();
                try {
                    senddata.put("c",i+1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.v("APPLI","1");
                Settings settings = new Settings();
                Log.v("APPLI","2");
                settings.setUseSystemSending(true);
                Log.v("APPLI","3");
                Transaction transaction = new Transaction(getApplicationContext(), settings);
                Log.v("APPLI","4");
                String zz = "RJDST"+senddata.toString();
                compressdecompress cd = new compressdecompress();
                String compressedstr="none";
//                try {
//                    compressedstr = compressdecompress.compress(zz);
                    compressedstr=zz;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                compressedstr=compressedstr.replace("\"","'");
                Log.v("APPLI",compressedstr);
                Message message = new Message(compressedstr, "8000374228");
                Log.v("APPLI","5");
                if(ContextCompat.checkSelfPermission(getBaseContext(), "android.permission.READ_SMS") == PackageManager.PERMISSION_GRANTED) {
                    Log.v("APPLI","6");
                    transaction.sendNewMessage(message,Transaction.NO_THREAD_ID);
                    Toast.makeText(Mandi.this,"Msg sent : "+zz,Toast.LENGTH_LONG).show();
                }else{
                    ActivityCompat.requestPermissions(Mandi.this, new String[]{"android.permission.READ_SMS"}, REQUEST_CODE_ASK_PERMISSIONS);
                    Log.v("APPLI","7");
                }
                Log.v("APPLI","8");
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
                Log.v("DATAREC",s);
                myyList = new ArrayList<MandiListItem>();
                mAdapter = new MandiListAdapter(context,Mandi.this,mlist);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Mandi.this);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                try {
                    JSONObject js = new JSONObject(s);
                    JSONObject ss = js.getJSONObject("data");
                    JSONArray js1 = ss.getJSONArray("veg");
                    ArrayList m = new ArrayList();
                    for(int i=0;i<js1.length();i++){
                       JSONArray js2 = js1.getJSONArray(i);
                        ArrayList m1 = new ArrayList();
                        m1.add(js2.getInt(0));
                        m1.add(js2.getInt(1));
                        m1.add(js2.getInt(2));
                        m.add(m1);
                        myyList.add(new MandiListItem(i+1,selecteditem,js2.getInt(0),js2.getInt(1),js2.getInt(2),String.valueOf(i)));
                    }
                    mAdapter = new MandiListAdapter(context,Mandi.this,myyList);
                    mRecyclerView.setAdapter(mAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }
    };



    @Override
    public void onBackPressed() {
        back();
    }
    public void back(){
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }


}
