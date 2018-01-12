package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {
    TextView txt;
    DatabaseReference databaseItems;
    public static int totalPrice=0;
public static List<CartItems> items=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ListView lv = (ListView) findViewById(R.id.lv);

        ArrayAdapter<CartItems> adapter = new ArrayAdapter<CartItems>(this,
                android.R.layout.simple_list_item_1, items);


        lv.setAdapter(adapter);

        txt=(TextView)findViewById(R.id.textView2);
        calculatePrice();
        //Toast.makeText(this, totalPrice, Toast.LENGTH_SHORT).show();
        txt.setText(String.valueOf(totalPrice));
        //txt.setText(();
        totalPrice=0;


    }

    public void finalOrder(View view){
        databaseItems = FirebaseDatabase.getInstance().getReference("Orders");

        for(int i=0;i<items.size();i++){
            String id =databaseItems.push().getKey();
            databaseItems.child(id).setValue(items.get(i));
        }
    }

    public void CategoriesButton(View view)
    {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);


    }

    public void calculatePrice(){
        for(int i=0;i<items.size();i++){
            totalPrice+= items.get(i).price;
        }
    }
}
