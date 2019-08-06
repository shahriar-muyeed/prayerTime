package com.example.prayer;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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

public class mapLocator extends AsyncTask<Void, Void, Void> {

    String data;
    public  static String name;
    Double lat;
    Double lon;
    public static String parseddata;
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        mapFragment.s=parseddata;
    }

    protected Void doInBackground(Void... voids) {
        try {
            URL url= new URL("https://api.myjson.com/bins/dj11t");
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

                lat = (Double) jo.get("latitude");
                lon = (Double) jo.get("longitude");
                parseddata=lat.toString()+" "+lon.toString();
            }
            Log.d("mapinit", parseddata);

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
