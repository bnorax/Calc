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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.Task;

import org.w3c.dom.Text;

import java.util.List;

public class Login extends AppCompatActivity {
    private int RC_SIGN_IN = 0;
    GoogleSignInClient mGoogleSignInClient;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null){
            Intent startCalc = new Intent(Login.this, MainActivity.class);
            startActivity(startCalc);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        }
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
        SignInButton googleButton = findViewById(R.id.googleButton);
        TextView login = findViewById(R.id.logLogin);
        TextView password = findViewById(R.id.logPass);
        TextView error = findViewById(R.id.logError);

        googleButton.setOnClickListener(v->{
            switch (v.getId()) {
                case R.id.googleButton:
                    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                    startActivityForResult(signInIntent, RC_SIGN_IN);
                    break;
            }
        });

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
