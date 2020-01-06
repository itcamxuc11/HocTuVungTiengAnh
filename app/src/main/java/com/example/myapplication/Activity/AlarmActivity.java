package com.example.myapplication.Activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.receiver.AlarmReceiver;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {


    private TimePicker timePicker;
    private TextView txtGioDat;
    private Button btnDatGio, btnHuyHenGio;

    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat sdf = new SimpleDateFormat("HH : mm");
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        addControls();
        addEvents();
    }

    private void addControls() {
        timePicker = findViewById(R.id.timePicker);
//        timePicker.setIs24HourView(true);
        txtGioDat = findViewById(R.id.txtGioDat);
        btnDatGio = findViewById(R.id.btnDatGio);
        btnHuyHenGio = findViewById(R.id.btnHuyDatGio);
        txtGioDat.setText(sdf.format(calendar.getTime()));
        alarmManager  = (AlarmManager) getSystemService(ALARM_SERVICE);

    }

    private void addEvents() {
        final Intent intent = new Intent(AlarmActivity.this, AlarmReceiver.class);
        btnDatGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.set(calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                calendar.set(calendar.MINUTE, timePicker.getCurrentMinute());
                intent.putExtra("BatTat", "on");
                pendingIntent = PendingIntent.getBroadcast(
                        AlarmActivity.this,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY, pendingIntent);
                txtGioDat.setText(sdf.format(calendar.getTime()));
                timePicker.setVisibility(View.GONE);
                btnDatGio.setVisibility(View.GONE);
                btnHuyHenGio.setVisibility(View.VISIBLE);
            }
        });
        btnHuyHenGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("BatTat", "off");
                sendBroadcast(intent);
                timePicker.setVisibility(View.VISIBLE);
                btnDatGio.setVisibility(View.VISIBLE);
                btnHuyHenGio.setVisibility(View.GONE);
            }
        });
    }
}
