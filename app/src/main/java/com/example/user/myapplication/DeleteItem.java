package com.example.user.myapplication;

import android.content.ClipData;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DeleteItem extends AppCompatActivity {

    DatabaseReference databaseItems;
    ListView ListViewItems;

    List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_item);

        databaseItems = FirebaseDatabase.getInstance().getReference("Items");

        ListViewItems = (ListView) findViewById(R.id.dataListView);

        itemList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseItems.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                itemList.clear();

                for(DataSnapshot itemSnapshot : dataSnapshot.getChildren()){

                    Item item = itemSnapshot.getValue(Item.class);

                    itemList.add(item);

                }

                ItemList adapter = new ItemList(DeleteItem.this, itemList);

                ListViewItems.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        ListViewItems.setOnItemClickListener((AdapterView, View, i, l));

        ListViewItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item item = itemList.get(i);

                ShowDeleteDialog(item.getItemId(), item.getItemName());
                return false;
            }

        });
    }


    private void ShowDeleteDialog(final String ItemId, String ItemName){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflator = getLayoutInflater();

        final View dialogView = inflator.inflate(R.layout.delete_dialog, null);

        dialogBuilder.setView(dialogView);

        final Button buttonDelete = (Button) dialogView.findViewById(R.id.button1);

        dialogBuilder.setTitle(ItemName);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        buttonDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                deleteItem(ItemId);
            }
        });

    }

    private boolean deleteItem (String Id){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Items").child(Id);

        databaseReference.removeValue();

        Toast.makeText(this, "Item Deleted", Toast.LENGTH_LONG).show();

        return true;
    }

}
