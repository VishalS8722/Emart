package com.example.emart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.security.PrivateKey;

public class lgpage extends AppCompatActivity {
    TextView t1;
    Button loginbutton;
    private EditText email;
    private EditText password;
    FirebaseAuth firebaseAuth;
    private String emailpattern ="[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lgpage);
        t1=findViewById(R.id.textView2);
        email = findViewById(R.id.editTextTextEmailAddress);
        password=findViewById(R.id.editTextTextPassword);
        loginbutton=findViewById(R.id.button4);
        firebaseAuth=FirebaseAuth.getInstance();
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l_s_intent = new Intent(lgpage.this,sgpage.class);
                startActivity(l_s_intent);
            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkinputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               checkinputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkemailandpassword();
            }
        });

    }
    private void checkemailandpassword() {
    if(email.getText().toString().matches(emailpattern)){
        if(password.length() >= 8){
            firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                     Intent mainIntent = new Intent(lgpage.this,Home_Activity.class);
                     startActivity(mainIntent);
                     finish();

                    }else{
                        String error = task.getException().getMessage();
                        Toast.makeText(lgpage.this, "error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Toast.makeText(this, "Incorrect email or password", Toast.LENGTH_SHORT).show();
        }
    }else{
        Toast.makeText(this, "Incorrect email or password", Toast.LENGTH_SHORT).show();
    }
    }
    private void checkinputs() {
        if (!TextUtils.isEmpty(email.getText())) {
            if(!TextUtils.isEmpty(password.getText()) && password.length() >=0 ){
                loginbutton.setEnabled(true);
            }else{
               loginbutton.setEnabled(false);
            }
        }else{
            loginbutton.setEnabled(false);
        }
    }
}