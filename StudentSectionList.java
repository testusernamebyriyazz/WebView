package com.socialsoft4u.karan.webview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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

public class StudentSectionList extends AppCompatActivity {
    String student[];
    public static  String Password = "password";
    public static  String Email = "jassii@gmail.com";
    SharedPreferences sharedpreferences;

    ArrayList<Actors> actorsList;
    AdapterTeacherStudent adapter;
    String address = "http://saikapyar.in/learningPAthSchool/get_sec_student.php";
    String classid;
    String section;
    Button teacherstutsub;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_section_list);

         intent = getIntent();
         section = intent.getStringExtra("sectionnamesend");

         Toast.makeText(getApplicationContext()," Section :- "+section,Toast.LENGTH_LONG).show();
        /*here get Section from Section list class !!!!!!*/



        final  GetStuu getStug = new GetStuu();
        getStug.execute();
        actorsList = new ArrayList<Actors>();

        ListView listView = (ListView)findViewById(R.id.studentSectionlist);
        adapter = new AdapterTeacherStudent(getApplicationContext(),R.layout.listing5,actorsList);
        listView.setAdapter(adapter);

        teacherstutsub = (Button)findViewById(R.id.teachertstudntsubmitButton);
        teacherstutsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Don't have Link ",Toast.LENGTH_SHORT).show();
            }
        });
    }

    class GetStuu extends AsyncTask<Void,Integer,String> {

        ProgressDialog dialog;

        @Override
        protected String doInBackground(Void... voids) {
            String result = downloadData2();

            parse(result);
            return result;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           /* dialog = new ProgressDialog(Attendence.this);
            dialog.setMessage("Loading, please wait");
            dialog.setTitle("Connecting server");
            dialog.show();
            dialog.setCancelable(false);*/
        }


        protected void onPostExecute(String result) {
            // dialog.cancel();
            adapter.notifyDataSetChanged();
        //    Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            // Toast.makeText(getApplicationContext(),Email,Toast.LENGTH_LONG).show();
          //  Toast.makeText(getApplicationContext(),Password,Toast.LENGTH_LONG).show();

            //Toast.makeText(getApplicationContext(), "Unable to fetch data from server"+result, Toast.LENGTH_LONG).show();

        }


    }
    private  String downloadData2()
    {
        InputStream inputStream =null;
        String line = null;
        try
        {
            URL url = new URL(address);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("sectionget", "UTF-8") +"="+ URLEncoder.encode(section, "UTF-8");

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            if(bufferedReader !=null)
            {
                while((line = bufferedReader.readLine())!=null)
                {
                    stringBuffer.append(line+"\n");
                }
            }
            else
            {
                return null;
            }
            return stringBuffer.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(inputStream !=null)
            {
                try
                {
                    inputStream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            }
        }
        return null;
    }
    private  int parse(String data)
    {
        try
        {

            JSONObject jsonRootObject = new JSONObject(data);
            JSONArray jsonArray = jsonRootObject.optJSONArray("result");
            //JSONArray jsonArray = new JSONArray(data);
            // JSONObject jsonObject = null;

            student = new String[jsonArray.length()];
            for(int i=0; i<jsonArray.length();i++)
            {

                // String name = jsonArray.getJSONObject(i).getString("CountryNames");
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Actors stuu = new Actors();

                stuu.setName(jsonObject.getString("StudentName"));
                stuu.setDob(jsonObject.getString("StudentRollNumber"));
                // actor.setCountry(jsonObject.getString("EventData"));
                // actor.setHeight(jsonObject.getString("Flag"));
                // actor.setImage(jsonObject.getString("Images"));
                //actor.setSpouse(object.getString("SubCatsID"));
                // actor.setDescription(object.getString("ProductID"));
                // actor.setChildren(object.getString("Ratings"));

                actorsList.add(stuu);
            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
