package ru.stairenx.viergo.youleadomsk.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ru.stairenx.viergo.youleadomsk.Constants;
import ru.stairenx.viergo.youleadomsk.ProgramItem;
import ru.stairenx.viergo.youleadomsk.R;
import ru.stairenx.viergo.youleadomsk.adapter.ProgramAdapter;
import ru.stairenx.viergo.youleadomsk.database.DataBaseAction;
import ru.stairenx.viergo.youleadomsk.server.ConnectServer;

/**
 * Created by viergo on 24.09.16.
 */
public class SecondDayYouthSpeakFragment extends AbstractTabFragment{

    private ArrayList<ProgramItem> program;
    private static final int LAYOUT = R.layout.fragment_first_day_view;
    private ProgramAdapter programAdapter;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        listView = (ListView) view.findViewById(R.id.firstday);
        //getProgram();
        program = DataBaseAction.getProgramDay("seconddayyouth");
        programAdapter = new ProgramAdapter(getContext().getApplicationContext(),program);
        initProgram();
        initNotyfi();
        return view;
    }

    public static SecondDayYouthSpeakFragment getInstance(Context context) {
        Bundle args = new Bundle();
        SecondDayYouthSpeakFragment fragment = new SecondDayYouthSpeakFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(Constants.TAB_ITEM_SECOND_YOUTH);

        return fragment;
    }

    private void getProgram(){
        if(program==null) {
            DataBaseAction.initContext(getContext().getApplicationContext());
            new GETJSON().execute();
        }
    }
    private void initProgram(){
        listView.setAdapter(programAdapter);
    }

    private void initNotyfi(){
        programAdapter.notifyDataSetChanged();
        listView.setAdapter(programAdapter);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private class GETJSON extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            String answrJSON;
            answrJSON = ConnectServer.getJSON(Constants.GET_PROGRAM_SECOND_DAY_YOUTH);
            Log.d("*(*(*(*(*(*(*", answrJSON);
            if(answrJSON!=null){
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
}
