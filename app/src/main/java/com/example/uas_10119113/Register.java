package com.example.uas_10119113;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private EditText nEmail, nPassword;
    private Button btn_login, btn_register;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        nEmail = (EditText) findViewById(R.id.email);
        nPassword =  (EditText) findViewById(R.id.password);
        btn_login = (Button) findViewById(R.id.btnLogin);
        btn_register = (Button) findViewById(R.id.btnRegister);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        inProgress(false);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEmpty()) return;
                inProgress(true);
                firebaseAuth.createUserWithEmailAndPassword(nEmail.getText().toString(),
                                nPassword.getText().toString()).
                        addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(Register.this, "Account Registered!",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new
                                        Intent(Register.this, Login.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                                return;
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                inProgress(false);
                                Toast.makeText(Register.this, "Register Failed!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }

    private void inProgress(boolean x){
        if(x){
            progressBar.setVisibility(View.VISIBLE);
            btn_login.setEnabled(false);
            btn_register.setEnabled(false);
        }else{
            progressBar.setVisibility(View.GONE);
            btn_login.setEnabled(true);
            btn_register.setEnabled(true);
        }
    }

    private boolean isEmpty(){
        if(TextUtils.isEmpty(nEmail.getText().toString())){
            nEmail.setError("Email is Required!");
            return true;
        }
        if(TextUtils.isEmpty(nPassword.getText().toString())){
            nPassword.setError("Password is Required!");
            return true;
        }

        return false;
    }
}

//NIM : 10119113
//Nama : Dafa Rizky Fahreza
//Kelas : IF3