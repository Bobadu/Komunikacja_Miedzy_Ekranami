package com.example.zamowienie;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.summary), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvName = findViewById(R.id.tvName);
        TextView tvSize = findViewById(R.id.tvSize);
        TextView tvCheese = findViewById(R.id.tvCheese);
        Button btnBack = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(MainActivity.EXTRA_NAME)) {
            String name = intent.getStringExtra(MainActivity.EXTRA_NAME);
            String size = intent.getStringExtra(MainActivity.EXTRA_SIZE);
            boolean cheese = intent.getBooleanExtra(MainActivity.EXTRA_CHEESE, false);

            tvName.setText(getString(R.string.summary_name, name));
            tvSize.setText(getString(R.string.summary_size, size));
            tvCheese.setText(cheese ? R.string.summary_cheese_yes : R.string.summary_cheese_no);
        }

        btnBack.setOnClickListener(v -> finish());
    }
}
