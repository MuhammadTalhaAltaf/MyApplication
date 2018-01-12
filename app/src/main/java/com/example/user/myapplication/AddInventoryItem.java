package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddInventoryItem extends AppCompatActivity {

    EditText editTextName, editTextStock, editTextSupplier, editTextPrice;
    Button buttonAddItem;

    DatabaseReference databaseItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inventory_item);

        databaseItems = FirebaseDatabase.getInstance().getReference("InventoryItems");

        editTextName = (EditText) findViewById(R.id.editText16);
        editTextStock = (EditText) findViewById(R.id.editText2);
        editTextSupplier= (EditText) findViewById(R.id.editText13);
        editTextPrice = (EditText) findViewById(R.id.editText14);

        buttonAddItem = (Button) findViewById(R.id.button3);

        buttonAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
    }

    private void addItem(){
        String name = editTextName.getText().toString().trim();
        String stock = editTextStock.getText().toString().trim();
        String supplier = editTextSupplier.getText().toString().trim();
        String price = editTextPrice.getText().toString().trim();

        if(!TextUtils.isEmpty(name) || !TextUtils.isEmpty(stock) || !TextUtils.isEmpty(supplier) || !TextUtils.isEmpty(price)){

            String id = databaseItems.push().getKey();

            InventoryItem item = new InventoryItem(id, name, stock, supplier, price);

            databaseItems.child(id).setValue(item);

            Toast.makeText(this, "Item Added Successfully", Toast.LENGTH_SHORT).show();
        }
        else{

            Toast.makeText(this, "Enter details first", Toast.LENGTH_SHORT).show();
        }
    }
}
