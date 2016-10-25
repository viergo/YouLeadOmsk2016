package ru.stairenx.viergo.youleadomsk.server;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import ru.stairenx.viergo.youleadomsk.Constants;
import ru.stairenx.viergo.youleadomsk.database.CreateDataBase;
import ru.stairenx.viergo.youleadomsk.database.DataBaseAction;

/**
 * Created by viergo on 26.09.16.
 */
public class ServerAction {

    private static String phone;
    private static HttpURLConnection conn;
    private static Integer res;

    public static void getNews(){
        new GETJSON_NEWS().execute();
    }

    public static void getProgram() {
        DataBaseAction.initContext(DataBaseAction.getContext());
        DataBaseAction.dropAllTable();
        new GETJSON_FIRST().execute();
        new GETJSON_SECOND_CAREER().execute();
        new GETJSON_SECOND_YOUTH().execute();
    }

    public static void getLogin(String num){
        phone = num;
        new GETJSON_LOGIN_DATA().execute();
    }

    public static void sendImage(Object object){
        new SEND_IMAGE().execute();
    }

    private static class GETJSON_FIRST extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            String answrJSON;
            answrJSON = ConnectServer.getJSON(Constants.GET_PROGRAM_FIRST_DAY);
            Log.d("*(*(*(*(*(*(*", answrJSON);
            if (answrJSON != null) {
                try {
                    JSONArray ja = new JSONArray(answrJSON);
                    JSONObject jo;
                    int i = 0;
                    while (i < ja.length()) {
                        jo = ja.getJSONObject(i);
                        DataBaseAction.addProgramDay(
                                "firstday",
                                jo.getString("time"),
                                jo.getString("program"),
                                jo.getString("title"),
                                jo.getString("speaker"),
                                jo.getString("background")
                        );
                        i++;
                    }

                } catch (JSONException e) {
                    e.getMessage();
                }
            }
            return null;
        }
    }
    private static class GETJSON_SECOND_CAREER extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            String answrJSON;
            answrJSON = ConnectServer.getJSON(Constants.GET_PROGRAM_SECOND_DAY_CAREER);
            Log.d("*(*(*(*(*(*(*", answrJSON);
            if (answrJSON != null) {
                try {
                    JSONArray ja = new JSONArray(answrJSON);
                    JSONObject jo;
                    int i = 0;
                    while (i < ja.length()) {
                        jo = ja.getJSONObject(i);
                        DataBaseAction.addProgramDay(
                                "seconddaycareer",
                                jo.getString("time"),
                                jo.getString("program"),
                                jo.getString("title"),
                                jo.getString("speaker"),
                                jo.getString("background")
                        );
                        i++;
                    }

                } catch (JSONException e) {
                    e.getMessage();
                }
            }
            return null;
        }
    }
    private static class GETJSON_SECOND_YOUTH extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            String answrJSON;
            answrJSON = ConnectServer.getJSON(Constants.GET_PROGRAM_SECOND_DAY_YOUTH);
            Log.d("*(*(*(*(*(*(*", answrJSON);
            if (answrJSON != null) {
                try {
                    JSONArray ja = new JSONArray(answrJSON);
                    JSONObject jo;
                    int i = 0;
                    while (i < ja.length()) {
                        jo = ja.getJSONObject(i);
                        DataBaseAction.addProgramDay(
                                "seconddayyouth",
                                jo.getString("time"),
                                jo.getString("program"),
                                jo.getString("title"),
                                jo.getString("speaker"),
                                jo.getString("background")
                        );
                        i++;
                    }

                } catch (JSONException e) {
                    e.getMessage();
                }
            }
            return null;
        }
    }
    private static class GETJSON_NEWS extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            String answrJSON;
            answrJSON = ConnectServer.getJSON(Constants.GET_NEWS+DataBaseAction.getLastNewsId());
            Log.d("*(*(*(*(*(*(*", answrJSON);
            if (answrJSON != null) {
                try {
                    JSONArray ja = new JSONArray(answrJSON);
                    JSONObject jo;
                    int i = 0;
                    while (i < ja.length()) {
                        jo = ja.getJSONObject(i);
                        DataBaseAction.addNews(
                                Integer.parseInt(jo.getString(CreateDataBase.COLUMN_ID)),
                                jo.getString(CreateDataBase.COLUMN_NEWS_IMAGE_AUTHOR),
                                jo.getString(CreateDataBase.COLUMN_NEWS_AUTHOR),
                                jo.getString(CreateDataBase.COLUMN_NEWS_DATE),
                                jo.getString(CreateDataBase.COLUMN_NEWS_TEXT),
                                jo.getString(CreateDataBase.COLUMN_NEWS_IMG)
                        );
                        Log.d("********","Запись в локальную базу прошла успешно");
                        i++;
                    }
                } catch (JSONException e) {
                    e.getMessage();
                }
            }
            return null;
        }
    }

    private static class GETJSON_LOGIN_DATA extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            String answrJSON;
            answrJSON = ConnectServer.getJSON(Constants.GET_USERS+"?phone="+phone);
            Log.d("Запись Пользователя", answrJSON);
            if (answrJSON != null) {
                try {
                    JSONArray ja = new JSONArray(answrJSON);
                    JSONObject jo;
                    int i = 0;
                    while (i < ja.length()) {
                        jo = ja.getJSONObject(i);
                        DataBaseAction.addLogin(
                                phone,
                                jo.getString("name"),
                                jo.getString("img"),
                                jo.getString("email"),
                                jo.getString("info")
                        );
                        i++;
                    }

                } catch (JSONException e) {
                    e.getMessage();
                }
            }
            return null;
        }
    }

    public static void addNews(String imgAuthor, String author, String date,String text, String img){
        String post_url = new String();
        try {
            post_url = Constants.ADD_NEWS+ "?action=insert&imgAuthor="
                    + URLEncoder.encode(String.valueOf(imgAuthor.toString()), "UTF-8")
                    + "&author="
                    + URLEncoder.encode(String.valueOf(author.toString()), "UTF-8")
                    +"&dateNews="
                    + URLEncoder.encode(String.valueOf(date.toString()), "UTF-8")
                    +"&textNews="
                    + URLEncoder.encode(String.valueOf(text.toString()), "UTF-8")
                    +"&img="
                    + URLEncoder.encode(String.valueOf(img.toString()), "UTF-8");
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
            Log.d("^^^^^^^$$%%", post_url);
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

    private static class SEND_IMAGE extends AsyncTask<String,Void,Void>{
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
                conn.setConnectTimeout(1000); // ждем 10сек
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
