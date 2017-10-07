package com.socialsoft4u.karan.webview;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class RegisterationForm extends AppCompatActivity {
    EditText name,email,phone,pass,c_pass;
    Button btn_signup;

    BroadcastReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration_form);
        name = (EditText)findViewById(R.id.fullName);
        email = (EditText)findViewById(R.id.userEmailId);
        phone = (EditText)findViewById(R.id.mobileNumber);
        pass = (EditText)findViewById(R.id.password);
        c_pass = (EditText)findViewById(R.id.confirmPassword);

        btn_signup = (Button)findViewById(R.id.signUpBtn);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Name = name.getText().toString();
                String Email = email.getText().toString();
                String Phone = phone.getText().toString();
                String Password = pass.getText().toString();
                String C_Password = c_pass.getText().toString();
                String type = "CreateAccount";

                int randomPIN = (int)(Math.random()*900000)+100000;
                final String val = ""+randomPIN;



                final AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setTitle("Verify Phone Number");
                builder.setMessage("An sms will be sent to the number " + Phone + " for verification. Charges will apply as per your plan");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(final DialogInterface dialog, int which) {
                                //code to send sms here with the code value
                                final ProgressDialog progressdialog = ProgressDialog.show(getApplicationContext(), "Waiting for SMS", "Please hold on");


                                final CountDownTimer timer = new CountDownTimer(120000, 1000) {
                                    @Override
                                    public void onTick(long millisUntilFinished) {
                                        Log.v("ranjapp", "Ticking " + millisUntilFinished / 1000);
                                        progressdialog.setMessage("Waiting for the message " + millisUntilFinished / 1000);
                                    }

                                    @Override
                                    public void onFinish() {
                                        getApplicationContext().unregisterReceiver(receiver);
                                        progressdialog.dismiss();

                                    }
                                }.start();

                                receiver = new BroadcastReceiver() {
                                    @Override
                                    public void onReceive(Context context, Intent intent) {
                                        Bundle bundle = intent.getExtras();
                                        if (bundle != null) {
                                            if (readSMS(intent, val)) {
                                                Log.v("ranjapp", "SMS read");
                                                timer.cancel();
                                                try {
                                                    getApplicationContext().unregisterReceiver(receiver);
                                                } catch (Exception e) {
                                                }
                                            }
                                        }
                                    }
                                };
                                getApplicationContext().registerReceiver(receiver, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
                            }
                        }

                );
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener()

                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }

                );
                builder.show();



                Toast.makeText(getApplicationContext(),val,Toast.LENGTH_SHORT).show();
                //BackgroundWork backgroundWork = new BackgroundWork(getApplicationContext());
                //backgroundWork.execute(type,Name,Email,Phone,Password,C_Password,val);

            }
        });

    }

    boolean readSMS(Intent intent, String code) {
        try {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (int i = 0; i < pdusObj.length; i++) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();
                    if (message.contains(String.valueOf(code)))
                        return true;
                }
            }
        } catch (Exception e) {
            Log.v("ranjapp", "Exception here " + e.toString());
            return false;
        }
        return false;
    }
    public static void sendSMS(Context context, String incomingNumber, String sms) {
     //DateTimeFormatter dtfOut = DateTimeFormat.forPattern("YYYY-MM-dd HH:MM:SS");
        SmsManager smsManager = SmsManager.getDefault();                                      //send sms
        try {
         ArrayList<String> parts = smsManager.divideMessage(sms);
            smsManager.sendMultipartTextMessage(incomingNumber, null, parts, null, null);

        /*  RecContDBHelper recContDBHelper = new RecContDBHelper(context);
          recContDBHelper.insertRecord(new ContactData("", incomingNumber, dtfOut.print(MutableDateTime.now())));*/
          Log.v("ranjith", "Sms to be sent is " + sms);
        } catch (Exception e) {
            Log.v("ranjith", e + "");
        }
    }
}
