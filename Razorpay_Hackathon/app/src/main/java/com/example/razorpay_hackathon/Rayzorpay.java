package com.example.razorpay_hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Rayzorpay extends AppCompatActivity {

    private TextView name_tv1, pay_tv1, amt_tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rayzorpay);

        SharedPreferences namedata = getApplicationContext().getSharedPreferences("PD", 0);
        String name = namedata.getString("phone_number", null);
        String paym = namedata.getString("payment_id", null);
        String amount = namedata.getString("amount", null);

        name_tv1 = (TextView) findViewById(R.id.nametv1);
        name_tv1.setText(name);

        pay_tv1 = (TextView) findViewById(R.id.paytv1);
        pay_tv1.setText(paym);

        amt_tv1 = (TextView) findViewById(R.id.amttv1);
        amt_tv1.setText(amount);

        Button lol = (Button) findViewById(R.id.next);
        lol.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(Rayzorpay.this, mainpage.class);
                Rayzorpay.this.startActivity(registerIntent);
            }
        });

    }

    public void onBackPressed() {
        Intent registerIntent = new Intent(Rayzorpay.this, MainActivity2.class);
        Rayzorpay.this.startActivity(registerIntent);
        super.onBackPressed();
    }
}