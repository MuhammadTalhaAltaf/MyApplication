package com.example.user.myapplication;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by USER on 24-Dec-17.
 */

public class ItemList extends ArrayAdapter<Item> {

    private Activity context;
    private List<Item> itemList;

    public ItemList(Activity context, List<Item> itemList){
        super(context, R.layout.activity_items, itemList);
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflator = context.getLayoutInflater();

        View item_listView = inflator.inflate(R.layout.activity_items, null, true);

        TextView textViewName = (TextView) item_listView.findViewById(R.id.textView);
        TextView textViewPrice = (TextView) item_listView.findViewById(R.id.textView3);

        Item item = itemList.get(position);

        textViewName.setText(item.getItemName());
        textViewPrice.setText(item.getItemPrice());

        return item_listView;
    }
}
