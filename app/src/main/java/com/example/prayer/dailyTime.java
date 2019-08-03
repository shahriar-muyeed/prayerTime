package com.example.prayer;

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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class dailyTime extends AsyncTask< Void, Void, Void> {
    String data;
    String date="";
    public static String fajar="";
    String juhur="";
    String asar="";
    String mugrib="";
    String esha="";


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        timingFragment.fazar.setText(this.fajar);
        timingFragment.juhur.setText(this.juhur);
        timingFragment.asar.setText(this.asar);
        timingFragment.mugrib.setText(this.mugrib);
        timingFragment.esha.setText(this.esha);
        timingFragment.date.setText(this.date);


    }

    @Override
    protected Void doInBackground(Void... voids) {


        try {
            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            String formattedDate = df.format(c);
            String jsonUrl="https://muslimsalat.com/Dhaka/"+formattedDate+".json?key=9e57091c26ea14abe86138673a6dc0c8";
            URL url= new URL(jsonUrl);
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
            JSONArray ja=jsnobject.getJSONArray("items");
            //JSONArray ja=new JSONArray(data);
            for (int i=0;i<ja.length();i++ )
            {
                JSONObject jo=(JSONObject)ja.getJSONObject(i);
                date = (String) jo.get("date_for");
                fajar = (String) jo.get("fajr");
                juhur = (String) jo.get("dhuhr");
                asar = (String) jo.get("asr");
                mugrib = (String) jo.get("maghrib");
                esha = (String) jo.get("isha");
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

        finally {
            //timingFragment.f.concat(""+this.fajar);

        }

        return null;

    }

    public String getData() {
        return this.fajar;
    }
}

