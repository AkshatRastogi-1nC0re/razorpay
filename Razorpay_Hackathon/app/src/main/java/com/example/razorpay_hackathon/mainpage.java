package com.example.razorpay_hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mainpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        Button lol = (Button) findViewById(R.id.abtus);
        lol.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(mainpage.this, aboutus.class);
                mainpage.this.startActivity(registerIntent);
            }
        });

        Button lol1 = (Button) findViewById(R.id.snt);
        lol1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(mainpage.this, MainActivity2.class);
                mainpage.this.startActivity(registerIntent);
            }
        });

        Button lol2 = (Button) findViewById(R.id.exit);
        lol2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    public void onBackPressed() {
        Intent registerIntent = new Intent(mainpage.this, MainActivity2.class);
        mainpage.this.startActivity(registerIntent);
        super.onBackPressed();
    }
}