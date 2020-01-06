package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.Fragment.FragmentSearchActivity;
import com.example.myapplication.R;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void onTopic(View view){
        Intent intent = new Intent(StartActivity.this, MainActivity.class);
        startActivity(intent);

    }

    public void onSearch2(View view){
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        FragmentSearchActivity fragmentSearchActivity= new FragmentSearchActivity();
        fragmentTransaction.replace(R.id.framecontent,fragmentSearchActivity);
        fragmentTransaction.commit();
    }

    public void onHomeWork(View view){
        Intent intent = new Intent(StartActivity.this, HomeWorkActivity.class);
        startActivity(intent);
    }
    public void onDailyWord(View view){
        Intent intent = new Intent(StartActivity.this, DailyWordActivity.class);
        startActivity(intent);
    }

    public void onAlarm(View view){
        Intent intent = new Intent(StartActivity.this, AlarmActivity.class);
        startActivity(intent);
    }

}
