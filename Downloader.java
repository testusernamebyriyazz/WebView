package com.socialsoft4u.karan.webview;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

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

/**
 * Created by JAI PRAKASH on 12/16/2016.
 */

public class Downloader extends AsyncTask<Void,Integer,String>
{
    Context context;
    String address;
    ListView listView;
    String cat;
    ProgressDialog progressDialog;

    public Downloader(Context context, String address, ListView listView, String cat) {
        this.context = context;
        this.address = address;
        this.listView = listView;
        this.cat=cat;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Loading Data");
        progressDialog.setMessage("Please wait.....");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(Void... params) {

            String data= downloadData2();
            return data;


    }



    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressDialog.dismiss();
        if(s !=null)
        {

            Parser parser = new Parser(context,s,listView,cat);
            parser.execute();


        }

        else
        {
            Toast.makeText(context,"Unable to Load Data", Toast.LENGTH_LONG).show();
        }
    }

  /*  private  String downloadData()
    {
        InputStream inputStream =null;
        String line = null;
        try
        {
            URL url= new URL(address);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
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
*/
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
            String post_data =URLEncoder.encode("cat", "UTF-8") +"="+ URLEncoder.encode(cat, "UTF-8");
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


}
