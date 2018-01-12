package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AdminRoles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_roles);
    }

    public void ItemBtn(View view)
    {
        Intent intent = new Intent(this, ManageItems.class);
        startActivity(intent);
        finish();
    }

    public void ChefBtn(View view)
    {
        Intent intent = new Intent(this, RegisterChef.class);
        startActivity(intent);
        finish();
    }

    public void InventoryBtn(View view)
    {
        Intent intent = new Intent(this, ManageInventory.class);
        startActivity(intent);
        finish();
    }
}
