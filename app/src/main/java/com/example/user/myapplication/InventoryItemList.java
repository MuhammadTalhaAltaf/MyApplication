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
 * Created by USER on 07-Jan-18.
 */

public class InventoryItemList extends ArrayAdapter<InventoryItem> {

    private Activity context;
    private List<InventoryItem> itemList;

    public InventoryItemList(Activity context, List<InventoryItem> itemList){
        super(context, R.layout.activity_inventory,itemList);
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
   // @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflator = context.getLayoutInflater();

        View item_listView = inflator.inflate(R.layout.activity_items, null, true);

        TextView textViewName = (TextView) item_listView.findViewById(R.id.textView);
        TextView textViewPrice = (TextView) item_listView.findViewById(R.id.textView3);

        InventoryItem item = itemList.get(position);

        textViewName.setText(item.getItemName());
        textViewPrice.setText(item.getItemPrice());

        return item_listView;
    }
}
