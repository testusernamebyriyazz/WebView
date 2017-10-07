package com.socialsoft4u.karan.webview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by DELL on 8/30/2017.
 */

public class StudentAdapter extends ArrayAdapter<Student> {
    ArrayList<Student> actorList;
    LayoutInflater vi;
    int Resource;
    ViewHolder holder;

    public StudentAdapter(Context context, int resource, ArrayList<Student> objects) {
        super(context, resource, objects);
        vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        actorList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // convert view = design
        View v = convertView;
        if (v == null) {
            holder = new ViewHolder();
            v = vi.inflate(Resource, null);
            holder.imageview = (ImageView) v.findViewById(R.id.studentImage);
            holder.tvName = (TextView) v.findViewById(R.id.studentName);
            holder.tvDescription = (TextView) v.findViewById(R.id.studentSection);
            holder.tvDOB = (TextView) v.findViewById(R.id.studentPresent);
         /*   holder.tvCountry = (TextView) v.findViewById(R.id.tvCountry);
            holder.tvHeight = (TextView) v.findViewById(R.id.tvHeight);
            holder.tvSpouse = (TextView) v.findViewById(R.id.tvSpouse);
            holder.tvChildren = (TextView) v.findViewById(R.id.tvChildren);*/
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.imageview.setImageResource(R.drawable.logoschool);
        new DownloadImageTask(holder.imageview).execute(actorList.get(position).getImage());
        holder.tvName.setText(actorList.get(position).getName());
        holder.tvDescription.setText(actorList.get(position).getDescription());
        holder.tvDOB.setText(actorList.get(position).getDob());
        //  holder.tvCountry.setText(actorList.get(position).getCountry());
        //    holder.tvHeight.setText(actorList.get(position).getHeight());
        //  holder.tvSpouse.setText("Price: " + actorList.get(position).getSpouse());
        //holder.tvChildren.setText("Children: " + actorList.get(position).getChildren());
        return v;

    }

    static class ViewHolder {
        public ImageView imageview;
        public TextView tvName;
        public TextView tvDescription;
        public TextView tvDOB;
        public TextView tvCountry;
        public TextView tvHeight;
        public TextView tvSpouse;
        // public TextView tvChildren;

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}