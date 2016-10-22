package ru.stairenx.viergo.youleadomsk.authClient;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import ru.stairenx.viergo.youleadomsk.Constants;

/**
 * Created by viergo on 30.03.16.
 */
public class Registration {

    private static HttpURLConnection conn;
    private static Integer res;

    public static void regAcount(String login, String pass, String name, String email, String info){
        String post_url = new String();
        String img = "http://stairenx.ru/res/api/youlead/img/plug.png";
        try {
            post_url = Constants.SIGNUP+ "?action=insert&phone="
                    + URLEncoder.encode(String.valueOf(login.toString()), "UTF-8")
                    + "&pass="
                    + URLEncoder.encode(String.valueOf(Hash.md5Custom(pass).toString()), "UTF-8")
                    +"&email="
                    + URLEncoder.encode(String.valueOf(email.toString()), "UTF-8")
                    +"&name="
                    + URLEncoder.encode(String.valueOf(name.toString()), "UTF-8")
                    +"&img="
                    + URLEncoder.encode(String.valueOf(img.toString()), "UTF-8")
                    +"&info="
                    + URLEncoder.encode(String.valueOf(info.toString()), "UTF-8");
        } catch (IOException e) {
            e.getMessage();
        }
        RequesteServerReg rsr = new RequesteServerReg();
        rsr.execute(post_url);
    }


    private static class RequesteServerReg extends AsyncTask<String,Void,Void>{
        @Override
        protected Void doInBackground(String... params) {
            String post_url = new String();
            for(String post : params) {
                post_url = post;
            }
            //Log.d(TAGG, post_url);
            try {
                URL url = new URL(post_url);
                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(100); // ждем 10сек
                conn.setRequestMethod("POST");
                conn.setRequestProperty("User-Agent", "Mozilla/5.0");
                conn.connect();
                res = conn.getResponseCode();
            }catch (IOException e){
                e.getMessage();
            }

            return null;
        }
    }
}
