package com.leanapp.leanapp;

/**
 *  Leanapp Nexans Project
 *  Created By LINE SOFTWARE TECHNOLOGIES 2020
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Helper helper = new Helper();
    LocalDataManager coreData = new LocalDataManager();
    EditText email, password;
    Button signin;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signin = findViewById(R.id.signin);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);

        String isLogged = coreData.get(LoginActivity.this, "isLogged", "");

        if (isLogged.equals("1")){

            Intent i = new Intent(getBaseContext(), MainActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            finish();

        }

        String emailCache = coreData.get(LoginActivity.this, "emailCache", "");
        email.setText(emailCache);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailResult = email.getText().toString();
                String passwordResult = password.getText().toString();

                if (emailResult.equals("") || passwordResult.equals("")){

                    if (emailResult.equals("")){

                        inputEmailNull();

                    }
                    if (passwordResult.equals("")){

                        inputPasswordNull();
                    }

                }

                else {

                    login();
                    signin.setAlpha(0.3f);
                }

                /*Intent intent = new Intent(getApplicationContext(), VerificationActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.nullanim);*/

            }
        });
    }

    public void login(){

        String asd  = helper.BASE_URL();
        String dc = asd+"dailyCreditInfo.php";
        RequestQueue queue = Volley.newRequestQueue(this);
        final String uuid = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        final String deviceid= uuid.toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, dc,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        String loginResult = response.substring(0,1);
                        // Burada escapeden kaçınmak için ilk karakteri alıyor

                        if (loginResult.equals("0")){

                            coreData.save(LoginActivity.this , "isLogged", "1");
                            coreData.save(LoginActivity.this, "emailCache", email.getText().toString());


                            Intent i = new Intent(getBaseContext(), MainActivity.class);
                            i.putExtra("dailycredit", response.substring(0,1));
                            startActivity(i);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            finish();


                        }

                       else if (loginResult.equals("1")){

                           helper.wrongPasswordToast(LoginActivity.this);
                           signin.setAlpha(1f);

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(LoginActivity.this, "Not Connection",Toast.LENGTH_LONG).show();
                signin.setAlpha(1f);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parameters = new HashMap<String, String>();
                parameters.put("uuid",deviceid);
                return parameters;
            }
        };
        queue.add(stringRequest);

    }

    private void inputEmailCache(){

        coreData.save(LoginActivity.this, "emailCache", email.getText().toString());
    }

    private void inputPasswordCache(){

        coreData.save(LoginActivity.this, "passwordCache", password.getText().toString());
    }

    private void inputEmailNull(){

        email.setHint("Email is Required");
        email.setHintTextColor(getResources().getColor(R.color.red));

    }

    private void inputPasswordNull(){

        password.setHint("Password is Required");
        password.setHintTextColor(getResources().getColor(R.color.red));

    }



}