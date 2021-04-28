package com.example.calc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.calc.data.Database;
import com.example.calc.data.User;

import org.apache.commons.validator.routines.EmailValidator;

public class Register extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        Database db = Room.databaseBuilder(getApplicationContext(),
                Database.class, "db").allowMainThreadQueries().build();
        ButtonsListener(db);
    }
    void CleanUpTextViews(){
        TextView regPass1 = findViewById(R.id.regPass1);
        TextView regPass2 = findViewById(R.id.regPass2);
        regPass1.setText("");
        regPass2.setText("");
    }
    void ButtonsListener(Database db){
        Button regButton = findViewById(R.id.regButton);
        TextView regLogin = findViewById(R.id.regLogin);
        TextView regEmail = findViewById(R.id.regEmail);
        TextView regPass1 = findViewById(R.id.regPass1);
        TextView regPass2 = findViewById(R.id.regPass2);
        TextView regError = findViewById(R.id.regError);

        regButton.setOnClickListener(v->{
            if(regLogin.getText().length()==0 ||
                    regEmail.getText().length() == 0 ||
                    regPass1.getText().length() == 0 ||
                    regPass2.getText().length() == 0)
            {
                regError.setText("All fields need to be filled");
                return;
            }
            if(regPass1.getText() != regPass2.getText()){
                regError.setText("Passwords does not match");
                CleanUpTextViews();
                return;
            }
            String email = regEmail.getText().toString();
            EmailValidator emailValidator = EmailValidator.getInstance();
            if(!emailValidator.isValid(email)){
                regError.setText("Email is not valid");
                CleanUpTextViews();
                return;
            }
            if(db.userDao().getUserByEmail(email) !=null){
                regError.setText("User with that email already exists");
                CleanUpTextViews();
                return;
            }
            if(db.userDao().getUserByLogin(regLogin.getText().toString())!=null){
                regError.setText("User with that login already exists");
                CleanUpTextViews();
                return;
            }
            User userRegAttempt = new User();
            userRegAttempt.login = regLogin.getText().toString();
            userRegAttempt.password = regPass1.getText().toString();
            userRegAttempt.email =  email;
            db.userDao().addUser(userRegAttempt);
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
}
