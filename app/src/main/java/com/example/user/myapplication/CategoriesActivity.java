package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CategoriesActivity extends AppCompatActivity {

    Button btn;
    String buttonText1;
    public  static final String Extra_message="com.example.user.myapplication.ItemsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

    }

    public void category_button(View view)
    {

        Button btn = (Button)view;
        buttonText1 = btn.getText().toString();
        Intent intent = new Intent(this, ItemsActivity.class);

        intent.putExtra(Extra_message,buttonText1);

        startActivity(intent);
//        btn = (Button) findViewById(R.id.button3);
//        String BBQbtn = (String) btn.getText();
    }
    public void onClick(View v){


    }
}

