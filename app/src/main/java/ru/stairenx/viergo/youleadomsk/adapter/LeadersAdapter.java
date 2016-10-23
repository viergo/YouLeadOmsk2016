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

import ru.stairenx.viergo.youleadomsk.ItemPack.UsersItem;
import ru.stairenx.viergo.youleadomsk.R;
import ru.stairenx.viergo.youleadomsk.database.DataBaseAction;

/**
 * Created by viergo on 06.10.16.
 */
public class LeadersAdapter extends ArrayAdapter<UsersItem>{

    public LeadersAdapter(Context context, List<UsersItem> list) {
        super(context, R.layout.castom_fragment_leaders,list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UsersItem item = (UsersItem) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.castom_fragment_leaders, null);
        }
        ((TextView) convertView.findViewById(R.id.cv_nameLeader)).setText(item.getName());
        ((TextView) convertView.findViewById(R.id.cv_infoLeader)).setText(item.getInfo());
        Picasso.with(DataBaseAction.getContext())
                .load(item.getImg())
                .error(R.drawable.plug)
                .placeholder(R.drawable.plug)
                .into((ImageView) convertView.findViewById(R.id.cv_imgLeader));
        return convertView;
    }
}
