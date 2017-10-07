package com.socialsoft4u.karan.webview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by JAI PRAKASH on 12/16/2016.
 */

public class Parser extends AsyncTask<Void,Integer,Integer> {

    Context context;
    ListView listView;
    String val,data;
    Actors customList=null;
    String[] electronics;
    String  itemValue;

    ProgressDialog progressDialog;

    public Parser(Context context,String data, ListView listView, String val) {
        this.context = context;
        this.data = data;

        this.listView = listView;
        this.val=val;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Load Data"+data);
        progressDialog.setMessage("Please Wait.....");
        progressDialog.show();
    }

    @Override
    protected Integer doInBackground(Void... params) {

            return this.parse();


    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);


        if(integer ==1)
        {

        }
        else
        {
            Toast.makeText(context,"Unable to Load Data" + data, Toast.LENGTH_SHORT).show();
        }
        progressDialog.dismiss();
    }
    private  int parse()
    {
        try
        {

            JSONObject  jsonRootObject = new JSONObject(data);
            JSONArray jsonArray = jsonRootObject.optJSONArray("result");
            //JSONArray jsonArray = new JSONArray(data);
           // JSONObject jsonObject = null;

         electronics = new String[jsonArray.length()];
            for(int i=0; i<jsonArray.length();i++)
            {

               // String name = jsonArray.getJSONObject(i).getString("CountryNames");
                JSONObject jsonObject = jsonArray.getJSONObject(i);
               String name = jsonObject.getString("SubCatsNm");

                electronics[i]=name;
            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
