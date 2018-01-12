package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ItemsActivity extends AppCompatActivity {

    public  static final String Extra_message="com.example.user.myapplication.DescriptionActivity";

    DatabaseReference databaseItems;
    ListView ListViewItems;
//    EditText inputSearch;

//    ArrayAdapter<String> adapter;

//    TextView txt;

    Array[] itemsData=new Array[2];

    List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        Intent intent = getIntent();
        String text = intent.getStringExtra(CategoriesActivity.Extra_message);

        //databaseItems = FirebaseDatabase.getInstance().getReference("Items");
//

        if (text.matches("BBQ")) {
            Toast.makeText(getApplicationContext(), "BBQ", Toast.LENGTH_SHORT).show();
            databaseItems = FirebaseDatabase.getInstance().getReference("BBQ");
        }
        if (text.matches("CHINESE")) {
            Toast.makeText(getApplicationContext(), "CHINESE", Toast.LENGTH_SHORT).show();
            databaseItems = FirebaseDatabase.getInstance().getReference("Chineese");
        }
        if (text.matches("SEA FOOD")) {
            Toast.makeText(getApplicationContext(), "SEA FOOD", Toast.LENGTH_SHORT).show();
            databaseItems = FirebaseDatabase.getInstance().getReference("SeaFood");
        }
        if (text.matches("FAST FOOD")) {
            databaseItems = FirebaseDatabase.getInstance().getReference("FastFood");
        }
//        databaseSeaFood = FirebaseDatabase.getInstance().getReference("SeaFood");
//
//        databaseChineese = FirebaseDatabase.getInstance().getReference("Chineese");
//
//        databaseFastFood = FirebaseDatabase.getInstance().getReference("FastFood");
//
//        databaseBBQ = FirebaseDatabase.getInstance().getReference("BBQ");

        ListViewItems = (ListView) findViewById(R.id.dataListView);

//        inputSearch = (EditText) findViewById(R.id.editText4);

//        txt = (TextView) findViewById(R.id.textView);

//        adapter = new ArrayAdapter<String>(this, txt, )

        itemList = new ArrayList<>();

    }


    public void button(View view) {
        Intent intent = new Intent(this, DescriptionActivity.class);
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

                    Item item = itemSnapshot.getValue(Item.class);

                    itemList.add(item);

                }

                ItemList adapter = new ItemList(ItemsActivity.this, itemList);

                ListViewItems.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        ListViewItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item item = itemList.get(i);

                ShowDescription(item.getItemDesc(), item.getItemPrice());
                return false;
            }

        });
    }
    public String ItemData=null;
    public void ShowDescription(String itemDesc, String itemPrice) {
        Intent intent = new Intent(this, DescriptionActivity.class);
        ItemData+=itemDesc+"_"+itemPrice;
        intent.putExtra(Extra_message,ItemData);
//        intent.putExtra(Extra_message,itemPrice);

        startActivity(intent);
    }

}



