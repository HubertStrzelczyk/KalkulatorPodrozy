package com.example.kalkulatorpodrozy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        TextView etykietaPodsumowania = findViewById(R.id.etykietaPodsumowania);
        Button przyciskPotwierdz = findViewById(R.id.przyciskPotwierdz);
        Button przyciskAnuluj = findViewById(R.id.przyciskAnuluj);

        Intent intencja = getIntent();
        String dystans = intencja.getStringExtra(MainActivity.EXTRA_DYSTANS);
        String spalanie = intencja.getStringExtra(MainActivity.EXTRA_SPALANIE);
        boolean czyPremium = intencja.getBooleanExtra(MainActivity.EXTRA_CZY_PREMIUM, false);

        String tekstPodsumowania = "Dystans: " + dystans + " km\n" +
                "Spalanie: " + spalanie + " l/100km\n" +
                "Paliwo Premium: " + (czyPremium ? "Tak" : "Nie");

        etykietaPodsumowania.setText(tekstPodsumowania);

        przyciskPotwierdz.setOnClickListener(v -> {
            setResult(RESULT_OK);
            finish();
        });

        przyciskAnuluj.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}