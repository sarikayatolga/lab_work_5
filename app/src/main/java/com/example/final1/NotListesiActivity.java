package com.example.final1;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NotListesiActivity extends AppCompatActivity {

    private ListView listViewNotlar;
    private ArrayList<String> notlar;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_listesi);

        listViewNotlar = findViewById(R.id.listViewNotlar);
        notlar = new ArrayList<>();

        dosyadanoku();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notlar);
        listViewNotlar.setAdapter(adapter);
    }

    private void dosyadanoku() {
        try {
            File dir = new File(getFilesDir(), "notes");
            if (dir.exists()) {
                File[] files = dir.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.getName().endsWith(".txt")) {
                            FileInputStream fis = new FileInputStream(file);
                            InputStreamReader isr = new InputStreamReader(fis);
                            BufferedReader br = new BufferedReader(isr);
                            StringBuilder sb = new StringBuilder();
                            String line;
                            while ((line = br.readLine()) != null) {
                                sb.append(line).append("\n");
                            }
                            fis.close();
                            String title = file.getName().substring(0, file.getName().length() - 4);
                            notlar.add(title + " ### " + sb.toString().trim());
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Hata: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
