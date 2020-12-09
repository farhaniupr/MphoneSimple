package com.example.mphonesimple;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AkunActivity extends AppCompatActivity {
    ArrayAdapter<String> m_adapter;
    ArrayList<String> m_listItems = new ArrayList<String>();
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun);

        EditText editTextName = (EditText) findViewById(R.id.editUser);
        editTextName.setText(getIntent().getStringExtra("Nama"));

        EditText editDate = (EditText) findViewById(R.id.editdatetime);
        EditText editTime = (EditText) findViewById(R.id.editTextTime2);

        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(AkunActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                editDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        editTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(AkunActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                editTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });

        ListView listView = (ListView) findViewById(R.id.listView) ;

        m_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, m_listItems);
        listView.setAdapter(m_adapter);
        Button buttonkirim = (Button) findViewById(R.id.kirim);
        buttonkirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AkunActivity.this);
                builder.setTitle("Dialog Standar");
                builder.setMessage("Kirim ke List View?");
                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                String datetime = editDate.getText().toString()+","+editTime.getText().toString();

                                if(null!=datetime&&datetime.length()>0) {
                                    m_listItems.add(datetime);

                                    m_adapter.notifyDataSetChanged();
                                    //((customlistview) (((ListView) findViewById(R.id.listView)).getAdapter())).notifyDataSetChanged();
                                }

                            }
                        });
                builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),
                                android.R.string.no, Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setCancelable(false);
                builder.show();

            }
        });

        //((ListView)findViewById(R.id.listView)).setAdapter(new customlistview(null, this));
        //m_adapter = new ArrayAdapter<String>(this, R.layout.rowlistview, m_listItems);
        //listView.setAdapter(m_adapter);
    }
}