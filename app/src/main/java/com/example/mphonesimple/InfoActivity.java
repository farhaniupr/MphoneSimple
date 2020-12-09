package com.example.mphonesimple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InfoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TickerView tickerView, tickerUSD, tickerText;
    private char[] alphabetlist;
    private TextView textViewInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        tickerView = (TickerView) findViewById(R.id.ticker_number);
        //tickerUSD = (TickerView) findViewById(R.id.ticker_usd);
        //tickerText = (TickerView) findViewById(R.id.ticker_text);

        alphabetlist = new char[53];
        alphabetlist[0] = TickerUtils.EMPTY_CHAR;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 26; j++) {
                // Add all lowercase characters first, then add the uppercase characters.
                alphabetlist[1 + i * 26 + j] = (char) ((i == 0) ? j + 97 : j + 65);
            }
        }

        tickerView.setCharacterList(TickerUtils.getDefaultNumberList());
        //tickerUSD.setCharacterList(TickerUtils.getDefaultListForUSCurrency());
        //tickerText.setCharacterList(alphabetlist);

        //Default data
        tickerView.setText("1");
        //tickerUSD.setText("$5,200");
        setRandomText();
        //setRandomCurrency();

        final Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Pengunjung");
        categories.add("Mahasiswa");
        categories.add("Dosen");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

    }

    public void setRandomText(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Random r = new Random();
                        int low = 1000;
                        int high = 10000;
                        final int result = r.nextInt(high - low) + low;
                        tickerView.setText("" + result);
                        handler.postDelayed(this, 1500);
                    }
                });
            }
        }, 2000);
    }

    public void setRandomCurrency() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Random r = new Random();
                        int low = 1000;
                        int high = 10000;
                        final int result = r.nextInt(high - low) + low;
                        NumberFormat formatter = new DecimalFormat("#,###,###");
                        String usdString = formatter.format(Integer.parseInt(String.valueOf(result)));
                        tickerUSD.setText("Rp." + usdString);
                        tickerText.setText(generateChars(r, alphabetlist, 6));

                        handler.postDelayed(this, 1500);
                    }
                });
            }
        }, 2000);
    }

    private String generateChars(Random random, char[] list, int numDigits) {
        final char[] result = new char[numDigits];
        for (int i = 0; i < numDigits; i++) {
            result[i] = list[random.nextInt(list.length)];
        }
        return new String(result);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String itemselected = parent.getItemAtPosition(position).toString();

        textViewInfo = (TextView) findViewById(R.id.textTicker);
        if (itemselected == "Pengunjung") {
            textViewInfo.setText("Ticker Jumlah Pengunjung");
        } else
            if (itemselected == "Mahasiswa") {
            textViewInfo.setText("Ticker Jumlah Mahasiswa");
        } else  if (itemselected == "Dosen") {
                textViewInfo.setText("Ticker Jumlah Dosen");
            }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { }
}