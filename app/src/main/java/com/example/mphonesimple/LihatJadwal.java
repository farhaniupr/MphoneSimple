package com.example.mphonesimple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LihatJadwal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_jadwal);

        Button buttonLanjutkan = (Button) findViewById(R.id.buttonLanjutkan);

        buttonLanjutkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LihatJadwal.this, MataKuliahActivity.class);
                startActivity(intent);
            }
        });
    }
}