package com.jeremyleonardo.ezyfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TopUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);
        getSupportActionBar().setTitle("EzyFood : TopUp");

        setWalletBalance();
    }

    private void setWalletBalance(){
        TextView tvWallet = findViewById(R.id.tvWallet);
        tvWallet.setText("Rp "+String.valueOf(PreferenceHelper.getWalletBalance(this)));
    }

    public void topUp(View view){
        EditText etTopUp = findViewById(R.id.etTopUpAmount);

        int val = 0;
        do {
            try {
                val = Integer.parseInt(etTopUp.getText().toString());
            } catch (Exception e) {
                Toast.makeText(this, "Nominal TopUp harus diisi", Toast.LENGTH_LONG).show();
                return;
            }
        } while (val == 0);
        PreferenceHelper.topUp(this, val);
        Toast.makeText(this, "Berhasil TopUp", Toast.LENGTH_LONG).show();
        setWalletBalance();

    }

    public void openCart(View view){
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }
}