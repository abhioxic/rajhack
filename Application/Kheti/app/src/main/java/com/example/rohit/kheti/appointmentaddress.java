package com.example.rohit.kheti;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class appointmentaddress extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointmentaddress);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button b1 = (Button)findViewById(R.id.button2address);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp=getApplicationContext().getSharedPreferences("Home",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
//                SharedPreferences sharedPref = appointmentaddress.this.getPreferences(Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPref.edit();
                EditText e = (EditText)findViewById(R.id.editTextaddress) ;
                editor.putString("addr",e.getText().toString() );
                editor.apply();
                Intent nextActivity = new Intent(appointmentaddress.this,SoilTestActivity.class);
                startActivity(nextActivity);
            }
        });


    }

}
