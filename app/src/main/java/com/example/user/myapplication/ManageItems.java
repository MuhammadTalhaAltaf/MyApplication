package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ManageItems extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_items);
    }

    public void AddItemButton(View view)
    {
        Intent intent = new Intent(this, AddItem.class);
        startActivity(intent);
        finish();
    }

    public void UpdateItemButton(View view)
    {
        Intent intent = new Intent(this, UpdateItem.class);
        startActivity(intent);
        finish();
    }

    public void DeleteItemButton(View view)
    {
        Intent intent = new Intent(this, DeleteItem.class);
        startActivity(intent);
        finish();
    }
}
