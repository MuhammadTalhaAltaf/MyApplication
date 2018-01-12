package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ManageInventory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_inventory);

    }

    public void Inventory(View view) {
        Intent intent = new Intent(this, Inventory.class);
        startActivity(intent);
        finish();
    }

    public void AddInventoryItem(View view) {
        Intent intent = new Intent(this, AddInventoryItem.class);
        startActivity(intent);
        finish();
    }

    public void UpdateInventoryItem(View view) {
        Intent intent = new Intent(this, UpdateInventoryItem.class);
        startActivity(intent);
        finish();
    }

    public void DeleteInventoryItem(View view) {
        Intent intent = new Intent(this, DeleteInventoryItem.class);
        startActivity(intent);
        finish();
    }
}
