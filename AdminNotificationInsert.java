package com.socialsoft4u.karan.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdminNotificationInsert extends AppCompatActivity {
    EditText notificationName,NotificationContent;
    Button notificationButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notification_insert);

        notificationName = (EditText)findViewById(R.id.notiName);
        NotificationContent = (EditText)findViewById(R.id.Noticontent);

        notificationButton = (Button)findViewById(R.id.AdminNotiContent);
        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nameNoti = notificationName.getText().toString();
                String contentNoti = NotificationContent.getText().toString();

                String type = "Notification";

                BackgroundWork backgroundWork = new BackgroundWork(AdminNotificationInsert.this,nameNoti,contentNoti);
                backgroundWork.execute(type,nameNoti,contentNoti);
            }
        });

    }
}
