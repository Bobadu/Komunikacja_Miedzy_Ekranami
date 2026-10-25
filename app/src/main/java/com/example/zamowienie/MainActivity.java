package com.example.zamowienie;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "NAZWA_KLIENTA";
    public static final String EXTRA_SIZE = "ROZMIAR";
    public static final String EXTRA_CHEESE = "DODATKOWY_SER";

    private EditText etName;
    private RadioGroup rgSize;
    private CheckBox cbCheese;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etName = findViewById(R.id.etName);
        rgSize = findViewById(R.id.rgSize);
        cbCheese = findViewById(R.id.cbCheese);
        Button btnSummary = findViewById(R.id.btnSummary);

        btnSummary.setOnClickListener(v -> openSummary());
    }

    private void openSummary() {
        String name = etName.getText().toString().trim();
        if (name.isEmpty()) {
            etName.setError(getString(R.string.error_name));
            etName.requestFocus();
            return;
        }

        RadioButton rbSize = findViewById(rgSize.getCheckedRadioButtonId());
        String size = rbSize.getText().toString();
        boolean cheese = cbCheese.isChecked();

        Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
        intent.putExtra(EXTRA_NAME, name);
        intent.putExtra(EXTRA_SIZE, size);
        intent.putExtra(EXTRA_CHEESE, cheese);
        startActivity(intent);
    }
}
