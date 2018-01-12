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

public class AddItem extends AppCompatActivity {


    EditText editTextName, editTextPrice, editTextDesc;
    Button buttonAddItem;

    DatabaseReference databaseItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        databaseItems = FirebaseDatabase.getInstance().getReference("BBQ");

        editTextName = (EditText) findViewById(R.id.editText);
        editTextPrice = (EditText) findViewById(R.id.editText16);
        editTextDesc = (EditText) findViewById(R.id.editText13);

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
        String price = editTextPrice.getText().toString().trim();
        String desc = editTextDesc.getText().toString().trim();

        if(!TextUtils.isEmpty(name) || !TextUtils.isEmpty(price) || !TextUtils.isEmpty(desc)){

            String id =databaseItems.push().getKey();

            Item item = new Item(id, name, price, desc);

            databaseItems.child(id).setValue(item);

            Toast.makeText(this, "Item Added Successfully", Toast.LENGTH_SHORT).show();
        }
        else{

            Toast.makeText(this, "Enter details first", Toast.LENGTH_SHORT).show();
        }
    }
}
