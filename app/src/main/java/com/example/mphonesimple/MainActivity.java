package com.example.mphonesimple;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

    public static String id = "notifikasi";
    NotificationManager mNotificationManager;


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

        ImageButton imageButtonWebview = (ImageButton) findViewById(R.id.buttonWebView);
        imageButtonWebview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                //intent.putExtra("Nama", textEmail.getText().toString());
                startActivity(intent);
            }
        });

        ImageButton imageButtonMap= (ImageButton) findViewById(R.id.ButtonMap);
        imageButtonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        ImageButton imageButtonReminder= (ImageButton) findViewById(R.id.jadwalButton);
        imageButtonReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LihatJadwal.class);
                startActivity(intent);
            }
        });

        createchannel();
        makenoti("Selamat Datang di Aplikasi Kami", 1);
    }

    private void createchannel() {
        mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

// The user-visible name of the channel.
        CharSequence name = getString(R.string.channel_name);
// The user-visible description of the channel.
        String description = getString(R.string.channel_description);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;  //which is high on the phone.  high is urgent on the phone.  low is medium, so none is low?
        NotificationChannel mChannel = new NotificationChannel(id, name, importance);
// Configure the notification channel.
        mChannel.setDescription(description);
        mChannel.enableLights(true);
// Sets the notification light color for notifications posted to this
// channel, if the device supports this feature.
        mChannel.setLightColor(Color.RED);
        mChannel.enableVibration(true);
        mChannel.setShowBadge(true);
        mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        mNotificationManager.createNotificationChannel(mChannel);
    }


    public void makenoti(String message, int msgcount) {

        //Notification noti = new NotificationCompat.Builder(getApplicationContext())

        Notification noti = new Notification.Builder(getApplicationContext(), MainActivity.id)
                .setSmallIcon(R.mipmap.icon_app)
                //.setWhen(System.currentTimeMillis())  //When the event occurred, now, since noti are stored by time.
                .setChannelId(MainActivity.id)
                .setContentTitle("Notifikasi")   //Title message top row.
                .setContentText(message)  //message when looking at the notification, second row
                .setAutoCancel(true)   //allow auto cancel when pressed.
                .build();  //finally build and return a Notification.

        //Show the notification
        mNotificationManager.notify(1, noti);

    }

}