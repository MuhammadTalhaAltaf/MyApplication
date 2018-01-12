package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Users extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
    }

    public void ChefButton(View view)
    {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }

    public void AdminButton(View view)
    {
        Intent intent = new Intent(this, AdminRoles.class);
        startActivity(intent);
        finish();
    }
}
