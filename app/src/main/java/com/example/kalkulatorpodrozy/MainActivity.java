package com.example.kalkulatorpodrozy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_DYSTANS = "EXTRA_DYSTANS";
    public static final String EXTRA_SPALANIE = "EXTRA_SPALANIE";
    public static final String EXTRA_CZY_PREMIUM = "EXTRA_CZY_PREMIUM";

    private EditText poleDystans;
    private EditText poleSpalanie;
    private CheckBox opcjaPaliwoPremium;
    private TextView etykietaStatusu;

    private final ActivityResultLauncher<Intent> uruchamiaczPodsumowania = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            wynik -> {
                if (wynik.getResultCode() == RESULT_OK) {
                    etykietaStatusu.setText("Status: Zatwierdzono");
                    Toast.makeText(this, "Dane zapisane", Toast.LENGTH_SHORT).show();
                } else if (wynik.getResultCode() == RESULT_CANCELED) {
                    etykietaStatusu.setText("Status: Anulowano");
                    Toast.makeText(this, "Odrzucono zmiany", Toast.LENGTH_SHORT).show();
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        poleDystans = findViewById(R.id.poleDystans);
        poleSpalanie = findViewById(R.id.poleSpalanie);
        opcjaPaliwoPremium = findViewById(R.id.opcjaPaliwoPremium);
        etykietaStatusu = findViewById(R.id.etykietaStatusu);
        Button przyciskDalej = findViewById(R.id.przyciskDalej);

        przyciskDalej.setOnClickListener(v -> {
            if (walidujDane()) {
                wyslijDane();
            }
        });
    }

    private boolean walidujDane() {
        if (poleDystans.getText().toString().isEmpty()) {
            poleDystans.setError("Wpisz dystans");
            return false;
        }
        if (poleSpalanie.getText().toString().isEmpty()) {
            poleSpalanie.setError("Wpisz spalanie");
            return false;
        }
        return true;
    }

    private void wyslijDane() {
        Intent intencja = new Intent(this, SummaryActivity.class);
        intencja.putExtra(EXTRA_DYSTANS, poleDystans.getText().toString());
        intencja.putExtra(EXTRA_SPALANIE, poleSpalanie.getText().toString());
        intencja.putExtra(EXTRA_CZY_PREMIUM, opcjaPaliwoPremium.isChecked());
        uruchamiaczPodsumowania.launch(intencja);
    }
}