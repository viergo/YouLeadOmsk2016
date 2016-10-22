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

import ru.stairenx.viergo.youleadomsk.OrgItem;
import ru.stairenx.viergo.youleadomsk.R;
import ru.stairenx.viergo.youleadomsk.database.DataBaseAction;

/**
 * Created by viergo on 30.09.16.
 */
public class AllOrgsAdapter extends ArrayAdapter<OrgItem> {

    public AllOrgsAdapter(Context context, List<OrgItem> list) {
        super(context, R.layout.fragment_all_orgs,list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrgItem item = (OrgItem) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_all_orgs, null);
        }
        ((TextView) convertView.findViewById(R.id.cv_nameOrgs)).setText(item.getName());
        ((TextView) convertView.findViewById(R.id.cv_positionOrgs)).setText(item.getPosition());
        ((TextView) convertView.findViewById(R.id.cv_eduOrgs)).setText(item.getEdu());

        Picasso.with(DataBaseAction.getContext())
                .load(item.getImg())
                .error(R.drawable.plug)
                .placeholder(R.drawable.plug)
                .into((ImageView) convertView.findViewById(R.id.cv_photoOrgs));
        return convertView;
    }
}
