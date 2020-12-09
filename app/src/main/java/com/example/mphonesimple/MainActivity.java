package com.example.mphonesimple;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.MultiAutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    static int progress;
    int progressStatus = 0;
    Handler handler = new Handler();

    String[] gender ={"Laki-laki", "Perempuan"};
    String[] Hobi ={"Mancing", "Makan", "Membaca"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView textEmail = (TextView) findViewById(R.id.textEmail);
        textEmail.setText(getIntent().getStringExtra("Email"));


        AutoCompleteTextView editTextGender = (AutoCompleteTextView) findViewById(R.id.EditGender);
        MultiAutoCompleteTextView editTextHobi = (MultiAutoCompleteTextView) findViewById(R.id.EditHobi);

        ArrayAdapter<String> adapterSingle = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,gender);

        ArrayAdapter<String> adapterMulti = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,Hobi);

        editTextGender.setThreshold(1);
        editTextGender.setAdapter(adapterSingle);


        //multiauto
        editTextHobi.setAdapter(adapterMulti);
        editTextHobi.setThreshold(1);
        editTextHobi.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        ImageButton imageButtonAkun = (ImageButton) findViewById(R.id.akunButton);
        imageButtonAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AkunActivity.class);
                intent.putExtra("Nama", textEmail.getText().toString());
                startActivity(intent);
            }
        });

        ProgressBar progressDoalog = (ProgressBar) findViewById(R.id.progressBar);
        progressDoalog.setVisibility(View.INVISIBLE);
        ImageButton imageButtonPicture = (ImageButton) findViewById(R.id.PictureButton);
        imageButtonPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDoalog.setVisibility(View.VISIBLE);

                progress = 0;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        /*lakukan sesuatu disini*/
                        while (progressStatus < 10) {
                            progressStatus = doSomeWork();
                        }

                        /*sembunyikan progress bar*/
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                /*---0 - Visible; 4 - Invisible; 8 - Gone--*/
                                progressDoalog.setVisibility(View.GONE);
                                Intent intent = new Intent(MainActivity.this, ShowPictureActivity.class);
                                //intent.putExtra("Nama", textEmail.getText().toString());
                                startActivity(intent);
                            }
                        });
                    }

                    private int doSomeWork() {
                        try {
                            Thread.sleep(250);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return ++progress;
                    }
                }).start();
            }
        });


        ImageButton imageButtonAbout = (ImageButton) findViewById(R.id.AboutButton);
        imageButtonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                //intent.putExtra("Nama", textEmail.getText().toString());
                startActivity(intent);
            }
        });



    }
}