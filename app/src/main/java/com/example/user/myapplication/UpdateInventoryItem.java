package com.example.user.myapplication;

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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UpdateInventoryItem extends AppCompatActivity {

    DatabaseReference databaseItems;
    ListView ListViewItems;

    List<InventoryItem> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_inventory_item);

        databaseItems = FirebaseDatabase.getInstance().getReference("InventoryItems");

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

                    InventoryItem item = itemSnapshot.getValue(InventoryItem.class);

                    itemList.add(item);

                }

                InventoryItemList adapter = new InventoryItemList(UpdateInventoryItem.this, itemList);

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
                InventoryItem item = itemList.get(i);

                ShowUpdateDialog(item.getItemId(), item.getItemName(), item.getItemStock(), item.getItemSupplier(), item.getItemPrice());
                return false;
            }

        });
    }
    private void ShowUpdateDialog(final String ItemId, String ItemName, String ItemStock, String ItemSupplier, String ItemPrice){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflator = getLayoutInflater();

        final View dialogView = inflator.inflate(R.layout.inventroy_update_dialog, null);

        dialogBuilder.setView(dialogView);

        final EditText editTextName = (EditText) dialogView.findViewById(R.id.editText16);
        final EditText editStock = (EditText) dialogView.findViewById(R.id.editText13);
        final EditText editSupplier = (EditText) dialogView.findViewById(R.id.editText2);
        final EditText editPrice = (EditText) dialogView.findViewById(R.id.editText3);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.button7);

        dialogBuilder.setTitle("updating Item"+ ItemName);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String name = editTextName.getText().toString().trim();
                String stock = editStock.getText().toString().trim();
                String supplier = editSupplier.getText().toString().trim();
                String price = editPrice.getText().toString().trim();

                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(stock) || TextUtils.isEmpty(supplier) || TextUtils.isEmpty(price)){
                    editTextName.setError("Name Required");
                    editStock.setError("Stock Required");
                    editSupplier.setError("Supplier Required");
                    editPrice.setError("Price Required");
                    return;
                }

                updateItem(ItemId, name, stock, supplier, price);

                alertDialog.dismiss();
            }
        });

    }

    private boolean updateItem (String Id, String Name, String Stock, String Supplier, String Price){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("InventoryItems").child(Id);

        InventoryItem item = new InventoryItem(Id, Name, Stock, Supplier, Price);
        databaseReference.setValue(item);

        Toast.makeText(this, "Item Updated", Toast.LENGTH_LONG).show();

        return true;
    }
}
