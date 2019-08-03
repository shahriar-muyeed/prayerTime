
package com.example.prayer;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RamadanTime extends AsyncTask< Void, Void, Void> {
    String data;
    String dataPersed="";
    String serial="";
    String date="";
    String sehriTime="";
    String ifterTime="";
        @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        ramadanFragment.data.setText(this.dataPersed);
    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            URL url= new URL("https://api.myjson.com/bins/16ar8l");
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
            String line= "a";
            while(line!=null){
                line=bufferedReader.readLine();
                data= data+line;
            }
            String crappyPrefix = "null";

            if(data.startsWith(crappyPrefix)){
                data = data.substring(crappyPrefix.length(), data.length());
            }

            JSONObject jsnobject = new JSONObject(data);
            JSONArray ja=jsnobject.getJSONArray("locations");
            //JSONArray ja=new JSONArray(data);
            for (int i=0;i<ja.length();i++ )
            {
                JSONObject jo=(JSONObject)ja.getJSONObject(i);
                serial = (String) jo.get("serial");
                date = (String) jo.get("date")+"\n"+"\n";
                sehriTime = (String) jo.get("sehriTime");
                ifterTime = (String) jo.get("ifterTime");


                dataPersed = dataPersed+serial+"   "+sehriTime+"   "+ifterTime+"   "+date;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.d("url", "URL er probl");
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("IO", "IO te problem");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("json", "json er prbl");
        }

        return null;
    }

}
