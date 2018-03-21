package com.example.rohit.kheti;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.klinker.android.send_message.Message;
import com.klinker.android.send_message.Settings;
import com.klinker.android.send_message.Transaction;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SoilTestActivity extends AppCompatActivity {
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soil_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListDetail = mandilistviewexpand.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new mandilistviewadapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
//                Toast.makeText(
//                        getApplicationContext(),
//                        expandableListTitle.get(groupPosition)
//                                + " -> "
//                                + expandableListDetail.get(
//                                expandableListTitle.get(groupPosition)).get(
//                                childPosition), Toast.LENGTH_SHORT
//                ).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(SoilTestActivity.this);


                LayoutInflater inflater = SoilTestActivity.this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.soilappointmentmodallayout, null);
                final TextView datelay = (TextView) dialogView.findViewById(R.id.soilappointmentdate);
                final TextView timelay = (TextView) dialogView.findViewById(R.id.soilappointmenttime);
//                datelay.setText(expandableListTitle.get(groupPosition)
//                        + " -> "
//                        + expandableListDetail.get(
//                        expandableListTitle.get(groupPosition)).get(
//                        childPosition));
                datelay.setText(expandableListTitle.get(groupPosition));
                timelay.setText(expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition));
                builder.setView(dialogView);
//                RJACF
//                RJRFH
                builder.setTitle("Confirm Appointment");
//                builder.setMessage("Are you sure?");

                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing, but close the dialog
                        Settings settings = new Settings();
                        settings.setUseSystemSending(true);
                        Transaction transaction = new Transaction(getApplicationContext(), settings);
                        JSONObject senddata = new JSONObject();
                        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("Home",Context.MODE_PRIVATE);
                        String addr = sharedPref.getString("addr", "");
                        try {
                            senddata.put("addr",addr);
                            senddata.put("date",datelay.getText());
                            senddata.put("timer",timelay.getText());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String zz = "RJACF"+senddata.toString();
                        zz=zz.replace("\"","'");
                        Log.v("MESSAGE",zz);
                        Message message = new Message(zz, "8000374228");
                        transaction.sendNewMessage(message,Transaction.NO_THREAD_ID);
                        Toast.makeText(getApplicationContext(),"Your Application is Confirmed",Toast.LENGTH_LONG).show();
//                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("Decline", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Your Application is Declined",Toast.LENGTH_LONG).show();
                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

                return false;
            }
        });
    }

}
