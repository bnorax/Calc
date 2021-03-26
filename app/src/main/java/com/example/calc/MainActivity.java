package com.example.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
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

    TextWatcher tw = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mHandler.removeCallbacks(runText);
        }

        @Override
        public void afterTextChanged(Editable s) {

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
        Button butMult = findViewById(R.id.buttonMultiply);
        Button butDiv = findViewById(R.id.buttonDivide);
        Button butComma = findViewById(R.id.buttonComma);

        Button butOB = findViewById(R.id.buttonOB);
        Button butCB = findViewById(R.id.buttonCB);
        Button butSin = findViewById(R.id.buttonSin);
        Button butCos = findViewById(R.id.buttonCos);
        Button butC = findViewById(R.id.buttonC);

        txt = findViewById(R.id.editTextNumber);
        txt.addTextChangedListener(tw);

        txt.setMovementMethod(new ScrollingMovementMethod());
        txt.setHorizontallyScrolling(true);

        butOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resStr = resStr.concat("(");
                txt.setText(resStr);
            }
        });

        butCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resStr = resStr.concat(")");
                txt.setText(resStr);
            }
        });

        butC.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(!resStr.isEmpty()) {
                    resStr = resStr.substring(0, resStr.length() - 1);
                    txt.setText(resStr);
                }
            }
        });
        butC.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                resStr = "";
                txt.setText(resStr);
                return true;
            }
        });

        but1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                resStr = resStr.concat("1");
                txt.setText(resStr);
            }
        });

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
        butDiv.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(resStr.endsWith("/")){
                    return;
                }
                resStr = resStr.concat("/");
                txt.setText(resStr);
            }
        });
        butComma.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(resStr.endsWith("+")){
                    return;
                }
                resStr = resStr.concat("+");
                txt.setText(resStr);
            }
        });
        butMult.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(resStr.endsWith("*")){
                    return;
                }
                resStr = resStr.concat("*");
                txt.setText(resStr);
            }
        });
        butSin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                /*if(resStr.endsWith("+")){
                    return;
                }*/
                resStr = resStr.concat("sin(");
                txt.setText(resStr);
            }
        });
        butCos.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                /*if(resStr.endsWith("+")){
                    return;
                }*/
                resStr = resStr.concat("cos(");
                txt.setText(resStr);
            }
        });
        butEquals.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int countOB = resStr.length() - resStr.replace("(", "").length(),
                        countCB = resStr.length() - resStr.replace(")", "").length();
                if(countCB<countOB){
                    for(int i = 0; i< countOB-countCB; i++){
                        resStr = resStr.concat(")");
                    }
                }
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