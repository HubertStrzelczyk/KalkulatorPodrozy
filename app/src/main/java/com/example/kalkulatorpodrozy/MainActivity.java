package com.example.kalkulatorpodrozy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String KLUCZ_DYSTANS = "DYSTANS";
    public static final String KLUCZ_SPALANIE = "SPALANIE";
    public static final String KLUCZ_PALIWO = "PALIWO";

    private EditText poleDystans, poleSpalanie;
    private Spinner wyborPaliwa;
    private TextView tekstStatusu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        poleDystans = findViewById(R.id.poleDystans);
        poleSpalanie = findViewById(R.id.poleSpalanie);
        wyborPaliwa = findViewById(R.id.wyborPaliwa);
        tekstStatusu = findViewById(R.id.tekstStatusu);

        findViewById(R.id.przyciskDalej).setOnClickListener(v -> {
            String dStr = poleDystans.getText().toString();
            String sStr = poleSpalanie.getText().toString();

            if (dStr.isEmpty() || sStr.isEmpty()) {
                Toast.makeText(this, "Wypełnij pola!", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intencja = new Intent(this, SummaryActivity.class);
            intencja.putExtra(KLUCZ_DYSTANS, Double.parseDouble(dStr));
            intencja.putExtra(KLUCZ_SPALANIE, Double.parseDouble(sStr));
            intencja.putExtra(KLUCZ_PALIWO, wyborPaliwa.getSelectedItem().toString());

            rejestrator.launch(intencja);
        });
    }

    private final ActivityResultLauncher<Intent> rejestrator = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    tekstStatusu.setText("Status: Obliczenia zaakceptowane ✅");
                } else {
                    tekstStatusu.setText("Status: Obliczenia anulowane ❌");
                }
            }
    );
}