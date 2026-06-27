package com.example.practica16fixed;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etText;
    private TextView tvSaved;
    private SharedPreferences sPref;
    private final String SAVED_TEXT = "saved_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etText = findViewById(R.id.etText);
        tvSaved = findViewById(R.id.tvSaved);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnLoad = findViewById(R.id.btnLoad);


        btnSave.setOnClickListener(this);
        btnLoad.setOnClickListener(this);


        loadText();
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.btnSave) {
            saveText();
        } else if (id == R.id.btnLoad) {
            loadText();
        }
    }

    private void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, etText.getText().toString());
        ed.apply();

        Toast.makeText(this, "Текст сохранён", Toast.LENGTH_SHORT).show();
    }


    private void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        tvSaved.setText(savedText);

        Toast.makeText(this, "Текст загружен", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        saveText();
    }
}