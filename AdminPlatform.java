package com.socialsoft4u.karan.webview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class AdminPlatform extends AppCompatActivity {

    LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_platform);

        linearLayout1 = (LinearLayout)findViewById(R.id.AdminEvent);
        linearLayout2 = (LinearLayout)findViewById(R.id.ViewAllStudent);
        linearLayout3 = (LinearLayout)findViewById(R.id.Notification);
        linearLayout4 = (LinearLayout)findViewById(R.id.Adminmember);

        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent adminEvent  = new Intent(getApplicationContext(),AdminEventInsert.class);
                AdminPlatform.this.startActivity(adminEvent);
            }
        });
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent adminNotification = new Intent(getApplicationContext(),AdminNotificationInsert.class);
                AdminPlatform.this.startActivity(adminNotification);
            }
        });
    }
}
