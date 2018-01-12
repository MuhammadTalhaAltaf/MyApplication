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

public class UpdateItem extends AppCompatActivity {

    DatabaseReference databaseItems;
    ListView ListViewItems;

    List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);

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

                ItemList adapter = new ItemList(UpdateItem.this, itemList);

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

                ShowUpdateDialog(item.getItemId(), item.getItemName(), item.getItemPrice(), item.getItemDesc());
                return false;
            }

        });
    }


    private void ShowUpdateDialog(final String ItemId, String ItemName, String ItemPrice, String ItemDesc){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflator = getLayoutInflater();

        final View dialogView = inflator.inflate(R.layout.update_dialog, null);

        dialogBuilder.setView(dialogView);

        final EditText editTextName = (EditText) dialogView.findViewById(R.id.editText16);
        final EditText editPrice = (EditText) dialogView.findViewById(R.id.editText13);
        final EditText editDesc = (EditText) dialogView.findViewById(R.id.editText3);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.button7);

        dialogBuilder.setTitle("updating Item"+ ItemName);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String name = editTextName.getText().toString().trim();
                String price = editPrice.getText().toString().trim();
                String desc = editDesc.getText().toString().trim();

                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(price) || TextUtils.isEmpty(desc)){
                    editTextName.setError("Name Required");
                    editPrice.setError("Price Required");
                    editDesc.setError("Description Required");
                    return;
                }

                updateItem(ItemId, name, price, desc);

                alertDialog.dismiss();
            }
        });

    }

    private boolean updateItem (String Id, String Name, String Price, String Desc){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Items").child(Id);

        Item item = new Item(Id, Name, Price, Desc);
        databaseReference.setValue(item);

        Toast.makeText(this, "Item Updated", Toast.LENGTH_LONG).show();

        return true;
    }
}
