package com.example.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String resStr = "nores";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button but1 = findViewById(R.id.button1);
        EditText txt = findViewById(R.id.editTextTextPersonName);
        //Button but2 = findViewById(R.id.button2);
        but1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                resStr.concat("123");
                txt.setText(resStr, TextView.BufferType.EDITABLE);
            }
        });
    }
    @Override
    protected void onResume(){
        super.onResume();
    }
}