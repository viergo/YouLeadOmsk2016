package ru.stairenx.viergo.youleadomsk.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ru.stairenx.viergo.youleadomsk.ProgramItem;
import ru.stairenx.viergo.youleadomsk.R;

/**
 * Created by viergo on 25.09.16.
 */
public class ProgramAdapter extends ArrayAdapter<ProgramItem> {

    public ProgramAdapter(Context context, ArrayList<ProgramItem> list) {
        super(context, R.layout.castom_program_day,list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProgramItem item = (ProgramItem) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.castom_program_day, null);
        }
        ((TextView) convertView.findViewById(R.id.cv_time)).setText(item.getTime());
        ((TextView) convertView.findViewById(R.id.cv_program)).setText(item.getProgram());
        ((TextView) convertView.findViewById(R.id.cv_title)).setText(item.getTitle());
        ((TextView) convertView.findViewById(R.id.cv_speaker)).setText(item.getSpeaker());
        convertView.findViewById(R.id.color_cv_program).setBackgroundColor(Color.parseColor(item.getBackground()));
        return convertView;
    }
}
