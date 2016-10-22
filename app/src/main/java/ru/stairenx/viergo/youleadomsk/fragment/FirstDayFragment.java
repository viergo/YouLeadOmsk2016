package ru.stairenx.viergo.youleadomsk.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import ru.stairenx.viergo.youleadomsk.Constants;
import ru.stairenx.viergo.youleadomsk.ProgramItem;
import ru.stairenx.viergo.youleadomsk.R;
import ru.stairenx.viergo.youleadomsk.adapter.ProgramAdapter;
import ru.stairenx.viergo.youleadomsk.database.DataBaseAction;

/**
 * Created by viergo on 24.09.16.
 */
public class FirstDayFragment extends AbstractTabFragment {

    private ArrayList<ProgramItem> program;
    private static final int LAYOUT = R.layout.fragment_first_day_view;
    private ProgramAdapter programAdapter;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        listView = (ListView) view.findViewById(R.id.firstday);
        DataBaseAction.initContext(getContext().getApplicationContext());
       // getProgram();
        program = DataBaseAction.getProgramDay("firstday");
        programAdapter = new ProgramAdapter(getContext().getApplicationContext(),program);
        initProgram();
        initNotyfi();
        return view;
    }

    public static FirstDayFragment getInstance(Context context) {
        Bundle args = new Bundle();
        FirstDayFragment fragment = new FirstDayFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(Constants.TAB_ITEM_FIRST);

        return fragment;
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
}
