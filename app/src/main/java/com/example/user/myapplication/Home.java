package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void CategoriesButton(View view)
    {
        Intent intent = new Intent(this, CategoriesActivity.class);
        startActivity(intent);
        finish();
    }
    public void LoginButton(View view)
    {
        Intent intent = new Intent(this, Users.class);
        startActivity(intent);
        finish();
    }
    public void ViewOrderButton(View view)
    {
        Intent intent = new Intent(this, Chef.class);
        startActivity(intent);
        finish();
    }

}
