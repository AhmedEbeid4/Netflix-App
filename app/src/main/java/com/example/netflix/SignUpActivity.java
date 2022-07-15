package com.example.netflix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        actions();
    }
    private void actions(){
        loginTextView();
        signUpBtn();
    }
    private void signUpBtn(){
        TextInputEditText nameF=(TextInputEditText) findViewById(R.id.nameField);
        TextInputEditText EmailF=(TextInputEditText) findViewById(R.id.sign_email_field);
        TextInputEditText PhoneF=(TextInputEditText) findViewById(R.id.phone_number);
        TextInputEditText passF=(TextInputEditText) findViewById(R.id.sign_password_field);
        TextInputEditText ConfPassF=(TextInputEditText) findViewById(R.id.sign_confitm_password_field);
        TextView button=(TextView) findViewById(R.id.signUpBTN);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =nameF.getText().toString();
                String email=EmailF.getText().toString();
                String PhoneNum="+201"+PhoneF.getText().toString();
                System.out.println(PhoneNum);
                String password=passF.getText().toString();
                String confPass=ConfPassF.getText().toString();
                if(name.length()>0 && email.length()>0 && PhoneNum.length()>0 && password.length()>0 && confPass.length()>0) {
                    boolean a,a1,a2,a3;
                    a=email.contains("@");
                    if(!a){
                        Toast.makeText(SignUpActivity.this,"Invalid Email",Toast.LENGTH_SHORT).show();
                    }
                    a1=PhoneNum.charAt(4)=='1'||PhoneNum.charAt(4)=='0'||PhoneNum.charAt(4)=='2'||PhoneNum.charAt(4)=='5';
                    if(!a1){
                        Toast.makeText(SignUpActivity.this,"Invalid Number",Toast.LENGTH_SHORT).show();
                    }
                    a2=isValidPass(password);
                    if(!a2){
                        Toast.makeText(SignUpActivity.this,"Invalid Password , The password should contain 3 upper chars, 4 lower chars , 2 numbers and 1 other like ^&$@",Toast.LENGTH_SHORT).show();
                    }
                    a3=password.equals(confPass);
                    if(!a3){
                        Toast.makeText(SignUpActivity.this,"The password and the text in confirm password field should be the same",Toast.LENGTH_LONG).show();
                    }
                    if (a&&a1&&a2&&a3){
                        userTable db = new userTable(SignUpActivity.this);
                        int id;
                        if (db.getIDS().size() !=0){
                            id=db.getIDS().get(db.getIDS().size()-1)+1;
                        }else {
                            id=1;
                        }
                        String operation=db.insert(new User(
                                   id,
                                   name,
                                   email,
                                   PhoneNum,
                                   password
                        ));
                        Toast.makeText(SignUpActivity.this,operation,Toast.LENGTH_SHORT).show();

                        if(operation.equals("Successful operation")){
                            goToLoginPage();
                        }
                    }
                }else {
                    Toast.makeText(SignUpActivity.this,"Fill The Blanks Please!",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void loginTextView(){
        TextView loginTextView=(TextView) findViewById(R.id.loginView);
        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLoginPage();
            }
        });
    }
    private void goToLoginPage(){
        startActivity(new Intent(this,Login.class));
    }
    private Boolean isValidPass(String pass){
        // The password should contain 3 upper chars, 4 lower chars , 2 numbers and 1 other like ^&$@
        int numberOfUpper=0;
        int numberOfLower=0;
        int numberOfNumbers=0;
        int Other=0;
        for (int i =0 ; i<pass.length();i++){
            if (isAlpha(pass.charAt(i))){
                if (isUpper(pass.charAt(i))){
                    numberOfUpper++;
                }else {
                    numberOfLower++;
                }
            }else if (isNum(pass.charAt(i))){
                numberOfNumbers++;
            }else {
                Other++;
            }
        }
        return numberOfUpper==3 && numberOfLower ==4 && numberOfNumbers==2 && Other==1;
    }
    private Boolean isUpper(char a){
        return Character.toUpperCase(a) ==a;
    }
    private Boolean isAlpha(char a){
        a=Character.toUpperCase(a);
        return a>='A' && a<='Z';
    }
    private Boolean isNum(char a){
        return a>='0' && a<='9';
    }
}