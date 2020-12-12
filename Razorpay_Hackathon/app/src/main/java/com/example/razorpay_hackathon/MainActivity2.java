package com.example.razorpay_hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import com.razorpay.Checkout;

import org.json.JSONObject;

public class MainActivity2 extends Activity implements PaymentResultListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText ph, amt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ph = (EditText) findViewById(R.id.editTextPhone2);
        amt = (EditText) findViewById(R.id.editTextNumber);

        Button lol = (Button) findViewById(R.id.submit);
        lol.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final String phnum = ph.getText().toString();
                final String amount = amt.getText().toString();

                SharedPreferences paymentdata = getApplicationContext().getSharedPreferences("PD", 0);
                SharedPreferences.Editor editor = paymentdata.edit();

                editor.putString("phone_number", phnum);
                editor.putString("amount", amount);

                editor.apply();
                startPayment();
            }
        });

    }

    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;


        final Checkout co = new Checkout();

        ph = (EditText) findViewById(R.id.editTextPhone2);
        amt = (EditText) findViewById(R.id.editTextNumber);

        String email = "";
        String name = "";

        SharedPreferences paymentdata = getApplicationContext().getSharedPreferences("PD", 0);
        String phoneNum  = paymentdata.getString("phone_number", null);
        String amt  = paymentdata.getString("amount", null);
        int amon = Integer.parseInt(amt);
        amon = amon*100;
        String amount = ""+amon;


        double lol = Double.parseDouble(phoneNum);

        if(lol == 9540014357L){
            name = "Akshat Rastogi";
            email = "akshat28vivek@gmail.com";
        }else if(lol == 8433098945L){
            name = "Samarth Goyal";
            email = "goyalsamarth090@gmail.com";
        }

        SharedPreferences loool = getApplicationContext().getSharedPreferences("PD", 0);
        SharedPreferences.Editor editor = loool.edit();
        editor.putString("name", name);
        editor.apply();

        try {
            JSONObject options = new JSONObject();
            options.put("name", name);
            options.put("description", "Demoing Charges");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", amount);

            JSONObject preFill = new JSONObject();
            preFill.put("name", name);
            preFill.put("email", email);
            preFill.put("contact", phoneNum);

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    /**
     * The name of the function has to be
     * onPaymentSuccess
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    @SuppressWarnings("unused")

    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {
            Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
            SharedPreferences loool = getApplicationContext().getSharedPreferences("PD", 0);
            SharedPreferences.Editor editor = loool.edit();
            editor.putString("payment_id", razorpayPaymentID);
            editor.apply();


            Intent registerIntent = new Intent(MainActivity2.this, Rayzorpay.class);
            MainActivity2.this.startActivity(registerIntent);


        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
            Intent registerIntent = new Intent(MainActivity2.this, paymentns.class);
            MainActivity2.this.startActivity(registerIntent);
        }
    }

    /**
     * The name of the function has to be
     * onPaymentError
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    @SuppressWarnings("unused")
    @Override
    public void onPaymentError(int code, String response) {
        try {
            Toast.makeText(this, "Payment failed: " + code + " " + response, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
            Intent registerIntent = new Intent(MainActivity2.this, paymentns.class);
            MainActivity2.this.startActivity(registerIntent);
        }
    }

    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}