package ru.stairenx.viergo.youleadomsk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.stairenx.viergo.youleadomsk.ItemPack.SpeakerItem;
import ru.stairenx.viergo.youleadomsk.R;
import ru.stairenx.viergo.youleadomsk.database.DataBaseAction;

/**
 * Created by viergo on 29.09.16.
 */
public class AllSpeakerAdapter extends ArrayAdapter<SpeakerItem> {

    public AllSpeakerAdapter(Context context, List<SpeakerItem> list) {
        super(context, R.layout.fragment_full_info_speaker,list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SpeakerItem item = (SpeakerItem) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_full_info_speaker, null);
        }
        ((TextView) convertView.findViewById(R.id.cv_nameSpeaker)).setText(item.getSp_name());
        ((TextView) convertView.findViewById(R.id.cv_titleSpeaker)).setText(item.getSp_title());
        ((TextView) convertView.findViewById(R.id.cv_infoSpeaker)).setText(item.getSp_info());

                Picasso.with(DataBaseAction.getContext())
                        .load(item.getSp_img())
                        .error(R.drawable.plug)
                        .placeholder(R.drawable.plug)
                        .into((ImageView) convertView.findViewById(R.id.cv_photoSpeaker));
        return convertView;
    }
}
