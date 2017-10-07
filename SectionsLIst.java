package com.socialsoft4u.karan.webview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class SectionsLIst extends AppCompatActivity {
    String student[];
    public static  String Password = "password";
    public static  String Email = "jassii@gmail.com";
    SharedPreferences sharedpreferences;

    ArrayList<Actors> actorsList;
    AdapterSection adapter;
    String address = "http://saikapyar.in/learningPAthSchool/get_sec.php";
    String classid;

    String klaa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sections_list);

        Intent intent = getIntent();
        classid = intent.getStringExtra("classid");
       // Toast.makeText(getApplicationContext(),Email,Toast.LENGTH_LONG).show();

        final GetStu stu = new GetStu();
        stu.execute();

        actorsList = new ArrayList<Actors>();
        ListView listview = (ListView) findViewById(R.id.list4);
        adapter = new AdapterSection(getBaseContext(), R.layout.section_single, actorsList);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            Intent intent1 = new Intent(getApplicationContext(),StudentSectionList.class);

                //intent1.putExtra("section",actorsList.get(position).getName());
                klaa = actorsList.get(position).getName();
                intent1.putExtra("sectionnamesend", klaa);
            // Toast.makeText(getApplicationContext(),"he "+klaa,Toast.LENGTH_LONG).show();
                SectionsLIst.this.startActivity(intent1);
            }
        });
    }

    class GetStu extends AsyncTask<Void,Integer,String> {

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
          //  Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            // Toast.makeText(getApplicationContext(),Email,Toast.LENGTH_LONG).show();
         //   Toast.makeText(getApplicationContext(),Password,Toast.LENGTH_LONG).show();

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
            String post_data = URLEncoder.encode("email", "UTF-8") +"="+ URLEncoder.encode(Email, "UTF-8") +"&"+
                    URLEncoder.encode("classid", "UTF-8") +"="+ URLEncoder.encode(classid, "UTF-8");
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
                Actors stu = new Actors();

                stu.setName(jsonObject.getString("SecName"));
                //stu.setDob(jsonObject.getString("ClassID"));
                // actor.setCountry(jsonObject.getString("EventData"));
                // actor.setHeight(jsonObject.getString("Flag"));
                // actor.setImage(jsonObject.getString("Images"));
                //actor.setSpouse(object.getString("SubCatsID"));
                // actor.setDescription(object.getString("ProductID"));
                // actor.setChildren(object.getString("Ratings"));

                actorsList.add(stu);
            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

}

