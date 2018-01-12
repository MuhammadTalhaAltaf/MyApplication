package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Inventory extends AppCompatActivity {

    DatabaseReference databaseItems;
    ListView ListViewItems;

    Array[] itemsData=new Array[2];

    List<InventoryItem> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        databaseItems = FirebaseDatabase.getInstance().getReference("InventoryItems");

        ListViewItems = (ListView) findViewById(R.id.dataListView);

        itemList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

//        if() {

        databaseItems.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                itemList.clear();

                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {

                    InventoryItem item = itemSnapshot.getValue(InventoryItem.class);

                    itemList.add(item);

                }

                InventoryItemList adapter = new InventoryItemList(Inventory.this, itemList);

                ListViewItems.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

//        ListViewItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Item item = itemList.get(i);
//
//                ShowDescription(item.getItemDesc(), item.getItemPrice());
//                return false;
//            }
//
//        });
    }
}
