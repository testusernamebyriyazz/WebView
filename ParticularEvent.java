package com.socialsoft4u.karan.webview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ParticularEvent extends AppCompatActivity {

    ImageView imageView;
    TextView productname,productretailer,productprice,productsaleprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particular_event);

        Intent intent = getIntent();
        String ProductName = intent.getStringExtra("ProductName");
        String ProductRetailer = intent.getStringExtra("ProductRetailer");
        String ProductImg = intent.getStringExtra("ProductImg");
        String ProductPrice = intent.getStringExtra("ProductPrice");
        String ProductSalePrice = intent.getStringExtra("ProductSalePrice");

        productname = (TextView)findViewById(R.id.ename);
        productretailer = (TextView)findViewById(R.id.edata);
       // productsaleprice = (TextView)findViewById(R.id.e);
        productprice = (TextView)findViewById(R.id.edate);
        imageView = (ImageView)findViewById(R.id.eimage);

        productname.setText(ProductName);
        productretailer.setText(ProductRetailer);
        productprice.setText(ProductPrice);
      //  productsaleprice.setText(ProductSalePrice);
        Picasso.with(getApplicationContext()).load(ProductImg).into(imageView);

    }
}
