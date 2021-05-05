package com.example.calc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.ListFragment;

import androidx.fragment.app.*;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flGallery, Gallery.class, null)
                .commit();

    }
    @Override
    protected void onResume(){
        super.onResume();
    }
}