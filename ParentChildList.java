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

public class ParentChildList extends AppCompatActivity {
    String student[];
    public static  String Password = "";
    public static  String Email = "";
    SharedPreferences sharedpreferences;
    int position;
    String childId,pass,email;
    Intent in1;

    ArrayList<Student> actorsList;
    StudentAdapter adapter;
    String address = "http://saikapyar.in/learningPAthSchool/listParentStudent.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_information);
        getSupportActionBar().setTitle("Student Infomation");

        sharedpreferences = this.getSharedPreferences("notification", MODE_PRIVATE);
        Email = sharedpreferences.getString(BackgroundWork.username, "");
        Password = sharedpreferences.getString(BackgroundWork.password, "");
        // Toast.makeText(getApplicationContext(),Email,Toast.LENGTH_LONG).show();
        final GetStu stu = new GetStu();
        stu.execute();
        actorsList = new ArrayList<Student>();
        ListView listview = (ListView) findViewById(R.id.list4);
        adapter = new StudentAdapter(getBaseContext(), R.layout.listings4, actorsList);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                in1 = new Intent(getApplicationContext(), ParentChildDetail.class);

                in1.putExtra("StudentNamee",actorsList.get(position).getName());
                in1.putExtra("StudentImagee",actorsList.get(position).getImage());
                in1.putExtra("StudentClasss",actorsList.get(position).getDob());
                in1.putExtra("StudentSectionn",actorsList.get(position).getDescription());
                in1.putExtra("StudentRollNumberr",actorsList.get(position).getCountry());

                childId = actorsList.get(position).getSpouse();
                in1.putExtra("SendchildId", childId);

                //Toast.makeText(getApplicationContext(),"Hello"+kla+"hello" ,Toast.LENGTH_LONG).show();
                ParentChildList.this.startActivity(in1);

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
            dialog = new ProgressDialog(ParentChildList.this);
            dialog.setMessage("Loading, please wait");
            dialog.setTitle("Connecting server");
            dialog.show();
            dialog.setCancelable(false);
        }


        protected void onPostExecute(String result) {
            dialog.cancel();
            adapter.notifyDataSetChanged();
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
            String post_data = URLEncoder.encode("Email", "UTF-8") +"="+ URLEncoder.encode(Email, "UTF-8") +"&"+
                    URLEncoder.encode("loginPass", "UTF-8") +"="+ URLEncoder.encode(Password, "UTF-8");
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

            JSONObject  jsonRootObject = new JSONObject(data);
            JSONArray jsonArray = jsonRootObject.optJSONArray("result");
            //JSONArray jsonArray = new JSONArray(data);
            // JSONObject jsonObject = null;

            student = new String[jsonArray.length()];
            for(int i=0; i<jsonArray.length();i++)
            {

                // String name = jsonArray.getJSONObject(i).getString("CountryNames");
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Student stu = new Student();

                stu.setName(jsonObject.getString("StudentName"));
                stu.setDob(jsonObject.getString("ClassName"));
                stu.setImage(jsonObject.getString("Image"));
                stu.setDescription(jsonObject.getString("StudentSection"));
                stu.setCountry(jsonObject.getString("StudentRollNumber"));
                stu.setSpouse(jsonObject.getString("ID"));


                // stu.setHeight(jsonObject.getString("ClassName"));
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
