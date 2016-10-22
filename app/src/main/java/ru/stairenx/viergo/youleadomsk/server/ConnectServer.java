package ru.stairenx.viergo.youleadomsk.server;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import ru.stairenx.viergo.youleadomsk.Constants;

/**
 * Created by viergo on 26.08.16.
 */
public class ConnectServer {

    private static HttpURLConnection httpURLConnection = null;
    private static BufferedReader reader = null;
    private static String resultJson = "";
    private static String responseServer;

    public static boolean Connect(){
       new ConnAsyncTask().execute();
        String out = "";
        if (responseServer.equals("")) {
            Log.d("TAg***","все плохо.......???");
        }else{
            try {
                JSONArray ja = new JSONArray(responseServer);
                JSONObject jo;
                int i = 0;
                while (i < ja.length()) {
                    jo = ja.getJSONObject(i);
                    out = jo.getString("connect");
                    i++;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if(out.equals("true")){
            return true;
        }else {
            return false;
        }
    }

    public static String getJSON(String textUrl){
        try{
            URL url = new URL(textUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            InputStream inputStream = httpURLConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine())!=null){
                buffer.append(line);
            }
            resultJson = buffer.toString();
        }catch (Exception e){}
        finally {
            httpURLConnection.disconnect();
        }
        return resultJson;
    }

    private static class ConnAsyncTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Void... params) {
            responseServer = getJSON(Constants.http+Constants.connect);
            return null;
        }
    }

}
