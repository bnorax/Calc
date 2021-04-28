package com.example.calc;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.calc.data.Database;
import com.example.calc.data.User;

import org.w3c.dom.Text;

import java.util.List;

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
                Database.class, "db").allowMainThreadQueries().build();
        User userS = new User();
        userS.login = "123";
        userS.password = "123";
        db.userDao().addUser(userS);
        ButtonsListener(db);
    }
    void ButtonsListener(Database db){
        Button loginButton = findViewById(R.id.logLogButton);
        Button logRegButton = findViewById(R.id.logRegButton);
        TextView login = findViewById(R.id.logLogin);
        TextView password = findViewById(R.id.logPass);
        TextView error = findViewById(R.id.logError);

        loginButton.setOnClickListener(v->{
            User user = db.userDao().getUserByLogin(login.getText().toString());
            if(user != null){
                if(user.password.contentEquals(password.getText())){
                    Intent startCalc = new Intent(Login.this, MainActivity.class);
                    startActivity(startCalc);
                    return;
                }
                error.setText("Wrong password");
                return;
            }
            error.setText("Wrong login");
        });
        logRegButton.setOnClickListener(v->{
            Intent startRegActivity = new Intent(Login.this, Register.class);
            startActivity(startRegActivity);
        });
    };
}
