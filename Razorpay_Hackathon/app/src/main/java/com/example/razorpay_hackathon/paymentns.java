package com.example.razorpay_hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class paymentns extends AppCompatActivity {

    private TextView name_tv1, amt_tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentns);


        SharedPreferences namedata = getApplicationContext().getSharedPreferences("PD", 0);
        String name = namedata.getString("phone_number", null);
        String amount = namedata.getString("amount", null);


        name_tv1 = (TextView) findViewById(R.id.nametv);
        name_tv1.setText(name);
        name_tv1.setPaintFlags(name_tv1.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));

        amt_tv1 = (TextView) findViewById(R.id.amttv);
        amt_tv1.setText(amount);


        amt_tv1.setPaintFlags(amt_tv1.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));


        Button lol = (Button) findViewById(R.id.submit);
        lol.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(paymentns.this, mainpage.class);
                paymentns.this.startActivity(registerIntent);
            }
        });
    }

    public void onBackPressed() {
        Intent registerIntent = new Intent(paymentns.this, MainActivity2.class);
        paymentns.this.startActivity(registerIntent);
        super.onBackPressed();
    }
}