package com.leanapp.leanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ProfilePage extends AppCompatActivity {

    ListView menu_listview;

    private String[] menu_items = {"My Profile", "About Us", "Version Control", "Change Password", "Logout"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        menu_listview = findViewById(R.id.menu_listview);
        ArrayAdapter<String> adapter = new  ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, menu_items);
        menu_listview.setAdapter(adapter);


        menu_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                //Toast.makeText(getApplicationContext(),menu_items[position], Toast.LENGTH_LONG).show();

                switch( position )
                {
                    case 0:
                        Intent i = new Intent(getBaseContext(), ProfilePage.class);
                        startActivity(i);
                        break;

                    case 1:
                        Intent i1 = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(i1);
                        break;

                    case 4: // Logout
                        LocalDataManager coreData  =new LocalDataManager();
                        coreData.save(ProfilePage.this, "isLogged", "0");
                        Toast.makeText(ProfilePage.this, "Oturum Kapatıldı",Toast.LENGTH_LONG).show();
                        Intent logout = new Intent(getBaseContext(), SplashScreen.class);
                        startActivity(logout);
                        finish();
                        break;

                }


            }
        });


    }
}