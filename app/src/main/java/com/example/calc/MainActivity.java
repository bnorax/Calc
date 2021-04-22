package com.example.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.Expression;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;


public class MainActivity extends AppCompatActivity {
    DecimalFormat decimalFormat;
    String resStr = "";
    TextView txt;
    final Handler mHandler = new Handler();
    Runnable runText = new Runnable() {
        public void run() {
            resStr = "";
            MainActivity.this.runOnUiThread(() -> txt.setText(""));
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

    boolean checkOps(){
        if(resStr.length()<1){
            return true;
        }
        if(resStr.endsWith(".")){
            resStr = resStr.substring(0, resStr.length()-1);
            txt.setText(resStr);
        }
        if(resStr.endsWith("-")){
            resStr = resStr.substring(0, resStr.length()-1);
            txt.setText(resStr);
        }
        if(resStr.endsWith("/")){
            resStr = resStr.substring(0, resStr.length()-1);
            txt.setText(resStr);
        }
        if(resStr.endsWith("*")){
            resStr = resStr.substring(0, resStr.length()-1);
            txt.setText(resStr);
        }
        if(resStr.endsWith("+")){
            resStr = resStr.substring(0, resStr.length()-1);
            txt.setText(resStr);
        }
        return true;
    }

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

        butOB.setOnClickListener(v -> {
            resStr = resStr.concat("(");
            txt.setText(resStr);
        });
        butCB.setOnClickListener(v-> {
            resStr = resStr.concat(")");
            txt.setText(resStr);
        });
        butC.setOnClickListener(v->{
            if(!resStr.isEmpty()) resStr = resStr.substring(0, resStr.length() - 1);
            txt.setText(resStr);
        });
        butC.setOnLongClickListener(v -> {
            resStr = "";
            txt.setText(resStr);
            return true;
        });
        but1.setOnClickListener(v -> {
            resStr = resStr.concat("1");
            txt.setText(resStr);
        });
        but1.setOnClickListener(v -> {
            resStr = resStr.concat("1");
            txt.setText(resStr);
        });
        but2.setOnClickListener(v -> {
            resStr = resStr.concat("2");
            txt.setText(resStr);
        });
        but3.setOnClickListener(v -> {
            resStr = resStr.concat("3");
            txt.setText(resStr);
        });
        but4.setOnClickListener(v -> {
            resStr = resStr.concat("4");
            txt.setText(resStr);
        });
        but5.setOnClickListener(v -> {
            resStr = resStr.concat("5");
            txt.setText(resStr);
        });
        but6.setOnClickListener(v -> {
            resStr = resStr.concat("6");
            txt.setText(resStr);
        });
        but7.setOnClickListener(v -> {
            resStr = resStr.concat("7");
            txt.setText(resStr);
        });
        but8.setOnClickListener(v -> {
            resStr = resStr.concat("8");
            txt.setText(resStr);
        });
        but9.setOnClickListener(v -> {
            resStr = resStr.concat("9");
            txt.setText(resStr);
        });
        but0.setOnClickListener(v -> {
            resStr = resStr.concat("0");
            txt.setText(resStr);
        });
        butMinus.setOnClickListener(v -> {
            if(!checkOps()) {
                return;
            }
            resStr = resStr.concat("-");
            txt.setText(resStr);
        });
        butPlus.setOnClickListener(v -> {
            if(!checkOps()) {
                return;
            }
            resStr = resStr.concat("+");
            txt.setText(resStr);
        });
        butDiv.setOnClickListener(v -> {
            if(!checkOps()) {
                return;
            }
            resStr = resStr.concat("/");
            txt.setText(resStr);
        });
        butComma.setOnClickListener(v -> {
            if(!checkOps()) {
                return;
            }
            for(int i = resStr.length()-1; i >= 0; i--) {
                char c = resStr.charAt(i);
                switch (c) {
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                        resStr = resStr.concat(".");
                        txt.setText(resStr);
                    case '.':
                        return;
                }
            }
            resStr = resStr.concat(".");
            txt.setText(resStr);
        });
        butMult.setOnClickListener(v -> {
            if(!checkOps()) {
                return;
            }
            resStr = resStr.concat("*");
            txt.setText(resStr);
        });
        butSin.setOnClickListener(v -> {
            resStr = resStr.concat("sin(");
            txt.setText(resStr);
        });
        butCos.setOnClickListener(v -> {
            resStr = resStr.concat("cos(");
            txt.setText(resStr);
        });
        butEquals.setOnClickListener(v -> {
            int countOB = resStr.length() - resStr.replace("(", "").length(),
                    countCB = resStr.length() - resStr.replace(")", "").length();
            if(countCB<countOB){
                for(int i = 0; i< countOB-countCB; i++){
                    resStr = resStr.concat(")");
                }
            }
            Expression exp = new Expression(resStr);
            if(exp.checkSyntax()){
                Double tmp = exp.calculate();
                if(!tmp.isNaN()){
                    resStr = decimalFormat.format(Double.valueOf(tmp));
                    txt.setText(resStr);
                    return;
                }
            }
            txt.setText("Error");
            resStr = "";
            mHandler.postDelayed(runText, 2000);
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
        dfs.setGroupingSeparator(',');
        dfs.setDecimalSeparator('.');
        decimalFormat = new DecimalFormat("0.#####");
        decimalFormat.setDecimalFormatSymbols(dfs);
        setContentView(R.layout.activity_main);
        SetCalcButtonListeners();

    }
    @Override
    protected void onResume(){
        super.onResume();
    }
}