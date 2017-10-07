package com.socialsoft4u.karan.webview;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TeacherPlatform extends Activity  {

    View rootview;
    Button btn_about, getInfo,btn_motivate,btn_trainer;
    TextView display;
    ImageView fb,youtube,gpluse,linkedin,gpulse;
    Spanned Text;
    LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent);

        linearLayout1 = (LinearLayout)findViewById(R.id.attendece);
        linearLayout2= (LinearLayout)findViewById(R.id.event);
        linearLayout3= (LinearLayout)findViewById(R.id.member);
        linearLayout4= (LinearLayout)findViewById(R.id.demo);

        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeacherPlatform.this,Attendence.class);
                startActivity(intent);

            }
        });
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent event = new Intent(TeacherPlatform.this,Events.class);
                startActivity(event);
            }
        });
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }


    private void whatsapp() {
        PackageManager pm = getApplicationContext().getPackageManager();

        try
        {
            // Raise exception if whatsapp doesn't exist
            PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            waIntent.setPackage("com.whatsapp");
            waIntent.putExtra(Intent.EXTRA_TEXT, "Hi");
            startActivity(waIntent);
        }
        catch (PackageManager.NameNotFoundException e)
        {
            Toast.makeText(getApplicationContext(), "Please install whatsapp app", Toast.LENGTH_SHORT)
                    .show();
        }
    }


}
