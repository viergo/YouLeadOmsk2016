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

import ru.stairenx.viergo.youleadomsk.NewsItem;
import ru.stairenx.viergo.youleadomsk.R;
import ru.stairenx.viergo.youleadomsk.database.DataBaseAction;

/**
 * Created by viergo on 29.09.16.
 */
public class NewsAdapter extends ArrayAdapter<NewsItem> {

    public NewsAdapter(Context context, List<NewsItem> list) {
        super(context, R.layout.castom_fragment_news, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsItem news = (NewsItem) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.castom_fragment_news, null);
        }
        ((TextView) convertView.findViewById(R.id.newsAuthorName)).setText(news.getAuthor());
        ((TextView) convertView.findViewById(R.id.newsDate)).setText(news.getDate());
        ((TextView) convertView.findViewById(R.id.newsText)).setText(news.getText());
        Picasso.with(DataBaseAction.getContext())
                .load(news.getImgAuthor())
                .placeholder(R.drawable.plug)
                .into((ImageView) convertView.findViewById(R.id.newsAuthorImage));
        Picasso.with(DataBaseAction.getContext())
                .load(news.getImg())
                .placeholder(R.drawable.border_grey)
                .into((ImageView) convertView.findViewById(R.id.newsImage));
        return convertView;
    }
}
