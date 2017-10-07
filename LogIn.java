package com.socialsoft4u.karan.webview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LogIn extends AppCompatActivity {


    EditText user_name,user_pass;
    Button btn_log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        TextView textView = (TextView)findViewById(R.id.createAccount);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegisterationForm.class));
            }
        });

        user_name = (EditText)findViewById(R.id.login_emailid);
        user_pass = (EditText)findViewById(R.id.login_password);






        btn_log = (Button)findViewById(R.id.loginBtn);
        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = user_name.getText().toString();
                String Pass = user_pass.getText().toString();

                String type = "login";


                BackgroundWork backgroundWork = new BackgroundWork(getApplicationContext(),Email,Pass);
                backgroundWork.execute(type,Email,Pass);

            }
        });


    }
}
