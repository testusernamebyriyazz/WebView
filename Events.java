package com.socialsoft4u.karan.webview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.squareup.picasso.Downloader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import static android.R.attr.checked;
import static android.R.attr.id;

public class Events extends AppCompatActivity {
    String cla1,cla2,cla3;
    ArrayList<Actors> actorsList;

    private RadioGroup radioGroup;
    RadioButton radiobutto1,radiobutton2,radiobutton3;
    ActorAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
        radiobutto1 = (RadioButton)findViewById(R.id.radioAndroid);
        radiobutto1.setChecked(true);

        radiobutton2 = (RadioButton)findViewById(R.id.radioiPhone);
        radiobutton3 = (RadioButton)findViewById(R.id.radioWindows);
        setValue();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.radioAndroid:
                       setValue();
                        break;
                    case R.id.radioiPhone:
                        ClassFunction();
                        break;
                    case R.id.radioWindows:
                        SectionFunctio();
                        break;

                }
            }
        });


    }
    public void setValue(){
        actorsList = new ArrayList<Actors>();

        new Events.JSONAsyncTask().execute("http://saikapyar.in/learningPAthSchool/fetchDataFrom_LearningPathSchool_Table.php");

        ListView listview = (ListView) findViewById(R.id.list3);


        adapter = new ActorAdapter(getApplicationContext(), R.layout.listings, actorsList);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent in1 = new Intent(Events.this, ParticularEvent.class);
                in1.putExtra("ProductName",actorsList.get(position).getName());
                in1.putExtra("ProductRetailer",actorsList.get(position).getCountry());
                in1.putExtra("ProductImg",actorsList.get(position).getImage());
                in1.putExtra("ProductPrice",actorsList.get(position).getDob());
                in1.putExtra("ProductSalePrice",actorsList.get(position).getHeight());

                // Toast.makeText(getApplicationContext(), actorsList.get(position).getName(), Toast.LENGTH_LONG).show();
                Events.this.startActivity(in1);

            }
        });

    }
    public void ClassFunction(){
        actorsList = new ArrayList<Actors>();

        new Events.JSONAsyncTask().execute("http://saikapyar.in/learningPAthSchool/class.php");

        ListView listview = (ListView) findViewById(R.id.list3);


        adapter = new ActorAdapter(getApplicationContext(), R.layout.listings, actorsList);

        listview.setAdapter(adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent in1 = new Intent(Events.this, ParticularEvent.class);
                in1.putExtra("ProductName",actorsList.get(position).getName());
                in1.putExtra("ProductRetailer",actorsList.get(position).getCountry());
                in1.putExtra("ProductImg",actorsList.get(position).getImage());
                in1.putExtra("ProductPrice",actorsList.get(position).getDob());
                in1.putExtra("ProductSalePrice",actorsList.get(position).getHeight());

                // Toast.makeText(getApplicationContext(), actorsList.get(position).getName(), Toast.LENGTH_LONG).show();
                Events.this.startActivity(in1);


            }
        });

    }
    public void SectionFunctio(){
        actorsList = new ArrayList<Actors>();

        new Events.JSONAsyncTask().execute("http://saikapyar.in/learningPAthSchool/section.php");

        ListView listview = (ListView) findViewById(R.id.list3);


        adapter = new ActorAdapter(getApplicationContext(), R.layout.listings, actorsList);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent in1 = new Intent(Events.this, ParticularEvent.class);
                in1.putExtra("ProductName",actorsList.get(position).getName());
                in1.putExtra("ProductRetailer",actorsList.get(position).getCountry());
                in1.putExtra("ProductImg",actorsList.get(position).getImage());
                in1.putExtra("ProductPrice",actorsList.get(position).getDob());
                in1.putExtra("ProductSalePrice",actorsList.get(position).getHeight());

                // Toast.makeText(getApplicationContext(), actorsList.get(position).getName(), Toast.LENGTH_LONG).show();
                Events.this.startActivity(in1);


            }
        });

    }

    class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(Events.this);
            dialog.setMessage("Loading, please wait");
            dialog.setTitle("Connecting server");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            try {

                //------------------>>
                HttpGet httppost = new HttpGet(urls[0]);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);

                // StatusLine stat = response.getStatusLine();
                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);


                    JSONObject jsono = new JSONObject(data);
                    JSONArray jarray = jsono.getJSONArray("result");
                    //Toast.makeText(getBaseContext(),"hello"+jarray,Toast.LENGTH_SHORT).show();

                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject object = jarray.getJSONObject(i);

                        Actors actor = new Actors();

                        actor.setName(object.getString("EventName"));
                        actor.setDob(object.getString("EventDate"));
                        actor.setCountry(object.getString("EventData"));
                        actor.setHeight(object.getString("Flag"));
                        actor.setImage(object.getString("Images"));
                        //actor.setSpouse(object.getString("SubCatsID"));
                        // actor.setDescription(object.getString("ProductID"));
                        // actor.setChildren(object.getString("Ratings"));

                        actorsList.add(actor);


                    }
                    return true;
                }

                //------------------>>

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

        protected void onPostExecute(Boolean result) {
            dialog.cancel();
            adapter.notifyDataSetChanged();
            /*if (result == false) {

                Toast.makeText(getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();
            }*/
        }


    }
}
