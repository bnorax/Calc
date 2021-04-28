package com.example.calc;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.calc.data.Database;

public class Login extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Database db = Room.databaseBuilder(getApplicationContext(),
                Database.class, "db").build();


    }
    void startCalcActivity(){
        Intent startCalc = new Intent(Login.this, MainActivity.class);
        startActivity(startCalc);
    }
}
