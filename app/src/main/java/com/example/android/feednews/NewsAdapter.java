package com.example.android.feednews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Context context, ArrayList<News> news) {
        super(context, 0, news);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_item, parent, false);
        }
        News currentNews = getItem(position);

        TextView title = (TextView) listItemView.findViewById(R.id.news_title);
        title.setText(currentNews.getTitle());

        TextView description = (TextView) listItemView.findViewById(R.id.news_description);
        description.setText(currentNews.getDescription());

        TextView author = (TextView) listItemView.findViewById(R.id.news_author);
        author.setText(currentNews.getAuthor());

        TextView date = (TextView) listItemView.findViewById(R.id.news_hoursPast);
        date.setText(currentNews.getHoursPast());

        ImageView image = (ImageView) listItemView.findViewById(R.id.news_image);
        Picasso.with(getContext()).load(currentNews.getImageResource()).into(image);

        TextView section = (TextView) listItemView.findViewById(R.id.news_category);
        section.setText(currentNews.getCategory());

        return listItemView;
    }
}
