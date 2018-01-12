package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

public class DescriptionActivity extends AppCompatActivity {

    TextView txt, txt1;
    ElegantNumberButton btn;
    String text;
    String [] ItemData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        Intent intent = getIntent();
        text = intent.getStringExtra(ItemsActivity.Extra_message);
        ItemData=text.split("_");

        Toast.makeText(this, ItemData[0], Toast.LENGTH_SHORT).show();
        Toast.makeText(this, ItemData[1], Toast.LENGTH_SHORT).show();

        txt = (TextView) findViewById(R.id.textView8);
        txt1 = (TextView) findViewById(R.id.textView7);

        txt.setText(ItemData[0]);
        txt1.setText(ItemData[1]);


        btn = (ElegantNumberButton) findViewById(R.id.myButton);
        btn.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = btn.getNumber();
                Log.e("Num", num);
            }
        });
    }

    public void CartButton(View view)
    {
        Intent intent = new Intent(this, Cart.class);
        startActivity(intent);
        CartItems ct=new CartItems();
        ct.name=ItemData[0];
        ct.quantity=2;
        ct.price= Integer.valueOf(ItemData[1]);
        Cart.items.add(ct);
        finish();
    }
}
