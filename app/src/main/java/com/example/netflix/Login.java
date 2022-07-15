package com.example.netflix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    private static User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        actions();
    }
    private void actions(){
        signUp();
        goToHome();
    }
    private void signUp() {
        TextView signUpBtn=(TextView) findViewById(R.id.sign_up_view);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,SignUpActivity.class));
                overridePendingTransition(0,0);
            }
        });
    }
    private void goToHome(){
        TextView loginBtn=(TextView) findViewById(R.id.loginBtn);
        TextInputEditText emailField=(TextInputEditText) findViewById(R.id.emailField);
        TextInputEditText passwordField=(TextInputEditText) findViewById(R.id.passField);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emailField.getText().toString().length()>0 && passwordField.getText().toString().length()>0){
                    String email=emailField.getText().toString();
                    String password=passwordField.getText().toString();
                    userTable db = new userTable(Login.this);
                    User u =Find(db.getData(),email,password);
                    if (u != null) {
                        startActivity(new Intent(Login.this,HomeActivity.class));
                        overridePendingTransition(0,0);
                    }else {
                        Toast.makeText(Login.this,"Not Found! ",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Login.this,"Fill The Blanks Please !",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private User Find(ArrayList<User> users,String email,String password){
        User u =null;
        for (int i =0 ; i<users.size();i++){
            if (email.equals(users.get(i).getEmail()) && password.equals(users.get(i).getPassword())){
                u=users.get(i);
                break;
            }
        }
        return u;
    }
    @Override
    public void onBackPressed() {
    }
}