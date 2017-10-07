package com.socialsoft4u.karan.webview;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

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

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Jai on 11-09-2017.
 */

public class BackgroundWork extends AsyncTask<String,Void,String>{

    Context context;
    String type,pin,address2,getStudentInfo,getlogin,getloginUser,geturl,loginEmail,loginPass,productID,productName,productRetailer,productImg,fullname,emailid,phone,Password,confmpassword;
    String name1,data1,date1,flag1,urlAdminEventInsert,urlAdminNotificationInsert;
    String notiName,contNoti;
    //for shared preference....................
    public static  String username = "username",tempuser;
    public static String password = "userpass",temppass;
    SharedPreferences sharedpreferences;


    public BackgroundWork(Context ctx,String email, String pass){
        context = ctx;
        this.tempuser=email;
        this.temppass=pass;
    }

    public BackgroundWork(Context ctx,String name, String data, String date,String flag){
        context = ctx;
        this.name1=name;
        this.data1=data;
        this.date1=date;
        this.flag1=flag;
    }



    @Override
    protected String doInBackground(String... params) {
        type = params[0];



        geturl = "http://saikapyar.in/mystore/insertdata.php";
        getlogin = "http://saikapyar.in/mystore/productIdInsert.php";
        getloginUser = "http://saikapyar.in/learningPAthSchool/parent_login_page.php";
        getStudentInfo ="http://saikapyar.in/learningPAthSchool/info_child.php";
        address2 = "http://saikapyar.in/learningPAthSchool/AttendanceStatus.php";
        urlAdminEventInsert ="http://saikapyar.in/learningPAthSchool/admin_event_insert.php";
        urlAdminNotificationInsert ="http://saikapyar.in/learningPAthSchool/admin_insert_notification.php";


        if(type.equals("register")) {
          productID  = params[1];
          productName = params[2];
          productRetailer = params[3];
          productImg = params[4];

              try {
                  URL url = new URL(geturl);
                  HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                  httpURLConnection.setRequestMethod("POST");
                  httpURLConnection.setDoOutput(true);
                  OutputStream outputStream = httpURLConnection.getOutputStream();
                  BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                  String post_data = URLEncoder.encode("productname", "UTF-8") + "=" + URLEncoder.encode(productName, "UTF-8") + "&" +
                                      URLEncoder.encode("productRetailer", "UTF-8") + "=" + URLEncoder.encode(productRetailer, "UTF-8") + "&" +
                                      URLEncoder.encode("ProductImgage", "UTF-8") + "=" + URLEncoder.encode(productImg, "UTF-8")+"&"+
                                      URLEncoder.encode("ProductId", "UTF-8") +"="+ URLEncoder.encode(productID, "UTF-8");// +"&"+
                       // URLEncoder.encode("Pin", "UTF-8") +"="+ URLEncoder.encode(pin, "UTF-8");
                  bufferedWriter.write(post_data);
                  bufferedWriter.flush();
                  bufferedWriter.close();
                  InputStream inputStream = httpURLConnection.getInputStream();
                  BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                  String result = "";
                  String line = "";
                  while ((line = bufferedReader.readLine()) != null) {
                      result += line;
                  }
                  bufferedReader.close();
                  inputStream.close();
                  httpURLConnection.disconnect();
                  return result;

              } catch (MalformedURLException e) {
                  e.printStackTrace();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }


          if (type.equals("CreateAccount")) {

              fullname = params[1];
              emailid = params[2];
              phone = params[3];
              Password = params[4];
              confmpassword = params[5];
               pin = params[6];


              try {
                  URL url = new URL(getlogin);
                  HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                  httpURLConnection.setRequestMethod("POST");
                  httpURLConnection.setDoOutput(true);
                  OutputStream outputStream = httpURLConnection.getOutputStream();
                  BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                  String post_data = URLEncoder.encode("fullname", "UTF-8") + "=" + URLEncoder.encode(fullname, "UTF-8") + "&" +
                          URLEncoder.encode("emailid", "UTF-8") + "=" + URLEncoder.encode(emailid, "UTF-8") + "&" +
                          URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8") + "&" +
                          URLEncoder.encode("ppassword", "UTF-8") + "=" + URLEncoder.encode(Password, "UTF-8") + "&" +
                          URLEncoder.encode("conformpassword", "UTF-8") + "=" + URLEncoder.encode(confmpassword, "UTF-8")+ "&" +
                          URLEncoder.encode("Pin", "UTF-8") +"="+ URLEncoder.encode(pin, "UTF-8");
                  bufferedWriter.write(post_data);
                  bufferedWriter.flush();
                  bufferedWriter.close();
                  InputStream inputStream = httpURLConnection.getInputStream();
                  BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                  String result = "";
                  String line = "";
                  while ((line = bufferedReader.readLine()) != null) {
                      result += line;
                  }
                  bufferedReader.close();
                  inputStream.close();
                  httpURLConnection.disconnect();
                  return result;

              } catch (MalformedURLException e) {
                  e.printStackTrace();
              } catch (IOException e) {
                  e.printStackTrace();
              }


          }
        if(type.equals("login")){

            loginEmail = params[1];
            loginPass = params[2];


            try {
                URL url = new URL(getloginUser);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("Email", "UTF-8") +"="+ URLEncoder.encode(loginEmail, "UTF-8") +"&"+
                        URLEncoder.encode("loginPass", "UTF-8") +"="+ URLEncoder.encode(loginPass, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        if(type.equals("studentInfo")){

            loginEmail = params[1];
            loginPass = params[2];


            try {
                URL url = new URL(getStudentInfo);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("Email", "UTF-8") +"="+ URLEncoder.encode(loginEmail, "UTF-8") +"&"+
                        URLEncoder.encode("loginPass", "UTF-8") +"="+ URLEncoder.encode(loginPass, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        /*here Single id send to the server*/

        if(type.equals("info")){

            String sinfo =  params[1];


            try {
                URL url = new URL(address2);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postdata = URLEncoder.encode("var","UTF-8") +"="+ URLEncoder.encode(sinfo,"UTF-8");
                bufferedWriter.write(postdata);
                bufferedWriter.flush();
                bufferedWriter.close();

                InputStream inputStream1 = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream1,"iso-8859-1"));

                String  result = "";
                String  line = "";
                while ((line= bufferedReader.readLine())!=null){
                    result+=line;

                }

                bufferedReader.close();
                httpURLConnection.disconnect();
                bufferedReader.close();
                return  result;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
/*here Admin insert event */

        if(type.equals("AdminEventInsert")){

            String Ename1 =  params[1];
            String Edata1 =  params[2];
            String Edate1 =  params[3];
            String Eflag1 =  params[4];


            try {
                URL url = new URL(urlAdminEventInsert);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postdata = URLEncoder.encode("Ename","UTF-8") +"="+ URLEncoder.encode(Ename1,"UTF-8")+"&"+
                        URLEncoder.encode("Edata","UTF-8") +"="+ URLEncoder.encode(Edata1,"UTF-8")+"&"+
                        URLEncoder.encode("Edate","UTF-8") +"="+ URLEncoder.encode(Edate1,"UTF-8")+"&"+
                        URLEncoder.encode("Eflag","UTF-8") +"="+ URLEncoder.encode(Eflag1,"UTF-8");
                bufferedWriter.write(postdata);
                bufferedWriter.flush();
                bufferedWriter.close();

                InputStream inputStream1 = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream1,"iso-8859-1"));

                String  result = "";
                String  line = "";
                while ((line= bufferedReader.readLine())!=null){
                    result+=line;

                }

                bufferedReader.close();
                httpURLConnection.disconnect();
                bufferedReader.close();
                return  result;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
/*notification content sent here*/
        if(type.equals("Notification")){

            String notiname =  params[1];
            String contentNoti =  params[2];


            try {
                URL url = new URL(urlAdminNotificationInsert);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postdata = URLEncoder.encode("Notiname","UTF-8") +"="+ URLEncoder.encode(notiname,"UTF-8")+"&"+
                        URLEncoder.encode("Noticontent","UTF-8") +"="+ URLEncoder.encode(contentNoti,"UTF-8");

                bufferedWriter.write(postdata);
                bufferedWriter.flush();
                bufferedWriter.close();

                InputStream inputStream1 = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream1,"iso-8859-1"));

                String  result = "";
                String  line = "";
                while ((line= bufferedReader.readLine())!=null){
                    result+=line;

                }

                bufferedReader.close();
                httpURLConnection.disconnect();
                bufferedReader.close();
                return  result;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return null;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }



    @Override
    protected void onPostExecute(String result) {

        String tempres[] = result.split(",");
       // Toast.makeText(context, tempres[1], Toast.LENGTH_LONG).show();

        if(tempres[0].equals("login success"))
        {
            sharedpreferences = context.getSharedPreferences("notification", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            //for shared preference....................

            editor.putString(username,tempuser);
            editor.putString(password,temppass);
            editor.commit();

            switch (tempres[1])
            {
                case "1":
                    if (loginEmail.equals("admin@gmail.com") && loginPass.equals("admin")){
                        Intent admin = new Intent(context,AdminPlatform.class);
                        admin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(admin);
                    }

                    break;

                case "2":

                    Intent intent = new Intent(context, TeacherPlatform.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                    break;

                case "3":
                    Intent intent1 = new Intent(context, ParentPlatform.class);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent1);

                    break;

            }

        }
      /*  else if(result.equals("login success")){

            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            context.startActivity(new Intent(context,SingleProductShow.class));
        }*/
        else {

            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
