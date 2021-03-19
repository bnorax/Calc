package com.example.calc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity {
    String resStr = "";
    Double tempRes;
    TextView txt;
    final Handler mHandler = new Handler();
    Runnable runText = new Runnable() {
        public void run() {
            resStr = "";
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    txt.setText("");
                }
            });
        }
    };
    TextWatcher textW = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
           mHandler.removeCallbacks(runText);
        }

        @Override
        public void afterTextChanged(Editable s) {
           mHandler.postDelayed(runText, 3000);
        }
    };

    void SetCalcButtonListeners(){
        Button but1 = findViewById(R.id.button1);
        Button but2 = findViewById(R.id.button2);
        Button but3 = findViewById(R.id.button3);
        Button but4 = findViewById(R.id.button4);
        Button but5 = findViewById(R.id.button5);
        Button but6 = findViewById(R.id.button6);
        Button but7 = findViewById(R.id.button7);
        Button but8 = findViewById(R.id.button8);
        Button but9 = findViewById(R.id.button9);
        Button but0 = findViewById(R.id.button0);
        Button butPlus = findViewById(R.id.buttonplus);
        Button butMinus = findViewById(R.id.buttonminus);
        Button butEquals = findViewById(R.id.buttonequals);

        txt = findViewById(R.id.editTextNumber);
       // txt.addTextChangedListener(textW);
        txt.setMovementMethod(new ScrollingMovementMethod());
        txt.setHorizontallyScrolling(true);

        but1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                resStr = resStr.concat("1");
                txt.setText(resStr);
            }
        });
        but2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                resStr = resStr.concat("2");
                txt.setText(resStr);
            }
        });
        but3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                resStr = resStr.concat("3");
                txt.setText(resStr);
            }
        });
        but4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                resStr = resStr.concat("4");
                txt.setText(resStr);
            }
        });
        but5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                resStr = resStr.concat("5");
                txt.setText(resStr);
            }
        });
        but6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                resStr = resStr.concat("6");
                txt.setText(resStr);
            }
        });
        but7.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                resStr = resStr.concat("7");
                txt.setText(resStr);
            }
        });
        but8.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                resStr = resStr.concat("8");
                txt.setText(resStr);
            }
        });
        but9.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                resStr = resStr.concat("9");
                txt.setText(resStr);
            }
        });
        but0.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                resStr = resStr.concat("0");
                txt.setText(resStr);
            }
        });
        butMinus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(resStr.endsWith("-")){
                    return;
                }
                resStr = resStr.concat("-");
                txt.setText(resStr);
            }
        });
        butPlus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(resStr.endsWith("+")){
                    return;
                }
                resStr = resStr.concat("+");
                txt.setText(resStr);
            }
        });
        butEquals.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Expression exp = new Expression(resStr);
                if(exp.checkSyntax()){
                    Double tmp;
                    tmp = exp.calculate();
                    resStr = tmp.toString();
                    txt.setText(resStr);
                    return;
                }
                txt.setText("Error");
                resStr = "";
                mHandler.postDelayed(runText, 2000);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SetCalcButtonListeners();
    }
    @Override
    protected void onResume(){
        super.onResume();
    }
}