package ru.stairenx.viergo.youleadomsk;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import ru.stairenx.viergo.youleadomsk.database.DataBaseAction;
import ru.stairenx.viergo.youleadomsk.server.ConnectServer;
import ru.stairenx.viergo.youleadomsk.server.ServerAction;

/**
 * Created by viergo on 23.08.16.
 */
public class StartActivity extends AppCompatActivity {

    private int con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        new ConnectSTX().execute();
    }


    private boolean connect(){
        String answer = ConnectServer.getJSON("http://stairenx.ru/res/api/youlead/method/connect.php");
        if (answer != null) {
            try {
                JSONObject jo = new JSONObject(answer);
                if(jo.getString("connect").equals("true")){
                    return true;
                }
            } catch (JSONException e) {
                e.getMessage();
            }
        }
        return false;
    }

    private class ConnectSTX extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... params) {
            con = 0;
            for(int i=0;i<=10;i++){
                if(connect()){
                    con++;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            DataBaseAction.initContext(getApplicationContext());
            if(con>=7){
                try {
                    if(DataBaseAction.getLogin()!=0){
                        startActivity(new Intent(StartActivity.this,MainActivity.class));
                        StartActivity.this.finish();
                    }else {
                        startActivity(new Intent(StartActivity.this, LoginActivity.class));
                        StartActivity.this.finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                DataBaseAction.initContext(getApplicationContext());
                ServerAction.getProgram();
                ServerAction.getNews();
            }else{
                Toast.makeText(StartActivity.this, "Требуется интернет подключение. Повторите попытку", Toast.LENGTH_LONG).show();
            }
        }
    }

}
