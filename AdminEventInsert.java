package com.socialsoft4u.karan.webview;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AdminEventInsert extends AppCompatActivity {
    EditText getEventName,getEventData,getEventflag;
    Button AdminEventSendButton;
    Button eventdatebuttonn;
    TextView getEventDate;


    Calendar calender = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_event_insert);

        getEventName = (EditText)findViewById(R.id.EventName);
        getEventData = (EditText)findViewById(R.id.EventData);
        getEventflag = (EditText)findViewById(R.id.EventFlag);


        getEventDate = (TextView)findViewById(R.id.Eventdate);
        eventdatebuttonn =(Button)findViewById(R.id.eventdatebutton);

        eventdatebuttonn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AdminEventInsert.this,listener,calender.get(Calendar.YEAR),calender.get(Calendar.MONTH),calender.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        AdminEventSendButton = (Button)findViewById(R.id.AdminEventSend);
        AdminEventSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = getEventName.getText().toString();
                String data = getEventData.getText().toString();
                String date = getEventDate.getText().toString();
                String flag = getEventflag.getText().toString();


                String type = "AdminEventInsert";

                BackgroundWork backgroundWorkb = new BackgroundWork(getApplicationContext(),name,data,date,flag);
                backgroundWorkb.execute(type,name,data,date,flag);

            }
        });


    }

    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            view.setMinDate(System.currentTimeMillis() - 1000);
            int  y = year;
            int m = month;
            int day = dayOfMonth;


            Calendar calender = Calendar.getInstance();
            calender.set(y,m,day);
            SimpleDateFormat format = new SimpleDateFormat("dd-MMMM-yyyy");
            String strdate = format.format(calender.getTime());
            getEventDate.setText(strdate);
        }
    };

}
