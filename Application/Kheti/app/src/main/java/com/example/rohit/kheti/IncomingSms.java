package com.example.rohit.kheti;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Rohit on 3/13/2018.
 */
public class IncomingSms extends BroadcastReceiver {

    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();
//    final String broadcastJSON="com.example.rohit.kheti.NOTIFY";
    public void onReceive(Context context, Intent intent) {

        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();
                    Log.v("message",message);
                    if(message.substring(0,5).equals("RJNTF")) {

                        Intent intentt=new Intent();
                        intentt.setAction(MainActivity.broadcastJSON);
                        intentt.putExtra("data", message.substring(5,message.length()));
                        context.sendBroadcast(intentt);
                    }

                    if(message.substring(0,5).equals("RJDST")) {

                        Intent intentt=new Intent();
                        intentt.setAction(Mandi.broadcastJSON);
                        intentt.putExtra("data", message.substring(5,message.length()));
                        context.sendBroadcast(intentt);
                    }
                    if(message.substring(0,5).equals("RJLOG")) {

                        Intent intentt=new Intent();
                        intentt.setAction(LoginActivity.broadcastJSON);
                        intentt.putExtra("data", message.substring(5,message.length()));
                        context.sendBroadcast(intentt);
                    }

                    Log.i("SmsReceiver", "senderNum: "+ senderNum + "; message: " + message);


                    // Show Alert
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context,
                            "senderNum: "+ senderNum + ", message: " + message, duration);
                    toast.show();

                } // end for loop
            } // bundle is null

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);

        }
    }
}