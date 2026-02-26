package com.example.kalkulatorpodrozy;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SummaryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        double d = getIntent().getDoubleExtra(MainActivity.KLUCZ_DYSTANS, 0);
        double s = getIntent().getDoubleExtra(MainActivity.KLUCZ_SPALANIE, 0);
        String p = getIntent().getStringExtra(MainActivity.KLUCZ_PALIWO);

        double cena = p.equals("LPG") ? 3.15 : 6.55;
        double suma = (d / 100) * s * cena;

        TextView tv = findViewById(R.id.tekstWynik);
        tv.setText(String.format("Dystans: %.1f km\nPaliwo: %s\nKoszt: %.2f PLN", d, p, suma));

        findViewById(R.id.btnOk).setOnClickListener(v -> {
            setResult(RESULT_OK);
            finish();
        });

        findViewById(R.id.btnAnuluj).setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}