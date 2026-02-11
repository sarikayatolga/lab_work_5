package com.example.final1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText etBaslik, etIcerik;
    private Button btnKaydet, btnListele;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etBaslik = findViewById(R.id.etBaslik);
        etIcerik = findViewById(R.id.etIcerik);
        btnKaydet = findViewById(R.id.btnKaydet);
        btnListele = findViewById(R.id.btnListele);

        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String baslik = etBaslik.getText().toString();
                String icerik = etIcerik.getText().toString();
                if (!baslik.isEmpty() && !icerik.isEmpty()) {
                    dosyayaKaydet(baslik, icerik);
                    etBaslik.setText("");
                    etIcerik.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Başlık ve içerik boş olamaz.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnListele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NotListesiActivity.class);
                startActivity(intent);
            }
        });
    }

    private void dosyayaKaydet(String title, String content) {
        try {
            File dir = new File(getFilesDir(), "notes");
            if (!dir.exists()) {
                dir.mkdir();
            }
            File file = new File(dir, title + ".txt");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(content.getBytes());
            fos.close();
            Toast.makeText(this, "Not Kaydedildi!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Hata: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}