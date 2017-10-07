package com.socialsoft4u.karan.webview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class ParentChildDetail extends AppCompatActivity {
    TextView stname1, stroll1, stsec1,stclass1;
    TextView  presenttt, absentttt;
    ImageView stimg1;
    //CircleImageView studentimg;
    String urlAttendenceStatus = "http://saikapyar.in/learningPAthSchool/AttendanceStatus.php";
    String student[];

    JSONObject jsonRootObject;
    JSONArray jsonArray;

    JSONObject jsonObject;
    Intent intent;
    String result, getchildId, name, img, stclass, sect, roll;

    ArrayList<Student> actorsList;
    StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_child_detail);


        getSupportActionBar().setTitle("Student Detail");
        intent = getIntent();

        getchildId = intent.getStringExtra("SendchildId");

        name = intent.getStringExtra("StudentNamee");
        roll = intent.getStringExtra("StudentRollNumberr");
        img = intent.getStringExtra("StudentImagee");
        stclass = intent.getStringExtra("StudentClasss");
        sect = intent.getStringExtra("StudentSectionn");

        stname1 = (TextView) findViewById(R.id.Studentname);
        stroll1 = (TextView) findViewById(R.id.studentRoll);
        stimg1 = (ImageView) findViewById(R.id.studentimg);
        stclass1 = (TextView) findViewById(R.id.Studentclass);
        stsec1 = (TextView) findViewById(R.id.studentsec);
        // stat = (TextView) findViewById(R.id.statuss);

        // dayofmonth = (TextView)findViewById(R.id.dayOfMonth);
        presenttt = (TextView) findViewById(R.id.present);
        absentttt = (TextView) findViewById(R.id.absent);

        final GetStuInfo stuinfo = new GetStuInfo();
        stuinfo.execute();
       /* Toast.makeText(getApplicationContext(),"hello"+status,Toast.LENGTH_SHORT).show();*/
        Picasso.with(getApplicationContext()).load(img).into(stimg1);

        stname1.setText(name);
        stroll1.setText(roll);
        stclass1.setText(stclass);
        stsec1.setText(sect);


    }

    class GetStuInfo extends AsyncTask<Void, Integer, String> {

        ProgressDialog dialog;

        @Override
        protected String doInBackground(Void... voids) {
            String result = downloadData3();

            parse3(result);
            return result;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        /*    dialog = new ProgressDialog(ParentChildDetail.this);
            dialog.setMessage("Loading, please wait");
            dialog.setTitle("Connecting server");
            dialog.show();
            dialog.setCancelable(false);*/
        }


        protected void onPostExecute(String result) {
            // dialog.cancel();
            // adapter.notifyDataSetChanged();
            // Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            // Toast.makeText(getApplicationContext(),Email,Toast.LENGTH_LONG).show();
            // Toast.makeText(getApplicationContext(),Password,Toast.LENGTH_LONG).show();
            // Toast.makeText(getApplicationContext(), "Unable to fetch data from server"+result, Toast.LENGTH_LONG).show();

        }


    }

    private String downloadData3() {
        InputStream inputStream = null;
        String line = null;
        try {
            URL url = new URL(urlAttendenceStatus);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("sendChildIdToApi", "UTF-8") + "=" + URLEncoder.encode(getchildId, "UTF-8");

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            if (bufferedReader != null) {
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line + "\n");
                }
            } else {
                return null;
            }
            return stringBuffer.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return null;
    }

    private  int parse3(String result)
    {
        try
        {

            JSONObject jsonRootObject = new JSONObject(result);
            JSONArray jsonArray = jsonRootObject.optJSONArray("result");

            JSONObject jsonObject = jsonArray.getJSONObject(0);

            presenttt.setText(jsonObject.getString("Present"));
            absentttt.setText(jsonObject.getString("Absent"));


            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
