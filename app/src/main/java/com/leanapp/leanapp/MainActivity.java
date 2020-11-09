package com.leanapp.leanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1=findViewById(R.id.button1);
        LocalDataManager coreData = new LocalDataManager();


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*coreData.save(MainActivity.this, "isLogged", "0");
                Toast.makeText(MainActivity.this, "Oturum Kapatıldı",Toast.LENGTH_LONG).show();
                Intent i = new Intent(getBaseContext(), SplashScreen.class);
                startActivity(i);
                finish();*/

                Intent i = new Intent(getBaseContext(), ProfilePage.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.nullanim);


            }
        });
    }
}