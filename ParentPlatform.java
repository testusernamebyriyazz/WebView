package com.socialsoft4u.karan.webview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class ParentPlatform extends AppCompatActivity {


    LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_platform);

        linearLayout1= (LinearLayout)findViewById(R.id.pEvent);
        linearLayout2=(LinearLayout)findViewById(R.id.pChildinfo);
        linearLayout3 = (LinearLayout)findViewById(R.id.pdemo);
        linearLayout4=(LinearLayout)findViewById(R.id.pdemo2);

        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent event = new Intent(getApplicationContext(),Events.class);
                ParentPlatform.this.startActivity(event);
            }
        });
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent student = new Intent(getApplicationContext(),ParentChildList.class);
                ParentPlatform.this.startActivity(student);
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

}
