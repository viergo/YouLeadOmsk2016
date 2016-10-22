package ru.stairenx.viergo.youleadomsk.authClient;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;

import ru.stairenx.viergo.youleadomsk.Constants;
import ru.stairenx.viergo.youleadomsk.server.ConnectServer;


/**
 * Created by viergo on 30.03.16.
 */
public class Authorization {

    private  static String jsonAuth;
    private static String serverLogin;
    private static String serverPass;
    private static String LOGG = "LOG^^^^^^^^^^";

    public static boolean entering(String login, String pass){
        String password;
        String post_url = new String();
        password = Hash.md5Custom(pass);

        try {
            post_url = Constants.AUTHORIZATION+"?action=auth&login="
                    + URLEncoder.encode(String.valueOf(login.toString()), "UTF-8")
                    + "&pass="
                    + URLEncoder.encode(String.valueOf(password.toString()), "UTF-8");
        } catch (IOException ignored) {
            ignored.getMessage();
        }
        RequesteServerAuth rs = new RequesteServerAuth();
        rs.execute(post_url);
       // Log.d(LOGG, "Полученные данные " + serverLogin + " " + serverPass);
       // Log.d(LOGG, "Данные из введенных полей "+login+" "+password);
        if(login.equals(String.valueOf(serverLogin))
                & password.equals(String.valueOf(serverPass))){
           // Log.d(LOGG, "Все верно");
            return  true;
        }else{
           // Log.d(LOGG, "Данные не верны");
            return false;
        }
    }

    private  static class RequesteServerAuth extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String post_url = new String();
            for(String post : params) {
                post_url = post;
            }
            //Log.d(TAGG, post_url);
           jsonAuth = ConnectServer.getJSON(post_url);
            return jsonAuth;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONArray ja = new JSONArray(jsonAuth);
                JSONObject jo;
                int i = 0;
                while (i < ja.length()) {
                    jo = ja.getJSONObject(i);
                   serverLogin = jo.getString("phone");
                   serverPass = jo.getString("pass");
                    i++;
                }
            } catch (JSONException e) {
                e.getMessage();
            }
        }
    }
}
