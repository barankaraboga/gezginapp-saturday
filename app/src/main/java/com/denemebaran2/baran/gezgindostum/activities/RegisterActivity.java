package com.denemebaran2.baran.gezgindostum.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.denemebaran2.baran.gezgindostum.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtEmail,edtPassword,edtPassword2;
    private Button btnRegister;
    String userEmail,userPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtEmail = findViewById(R.id.editTextEmailLogin);
        edtPassword = findViewById(R.id.editTextPasswordLogin);
        edtPassword2 = findViewById(R.id.editTextPassword2);
        btnRegister = findViewById(R.id.buttonRegister);
        mAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register() {
        userEmail = edtEmail.getText().toString().trim();
        userPassword = edtPassword.getText().toString().trim();
        String secondPassword = edtPassword2.getText().toString().trim();
        if (!userEmail.isEmpty() && !userPassword.isEmpty()
                && !secondPassword.isEmpty()) {
                firebaseRegister();
        }else {
            Toast.makeText(getApplicationContext(),
                    "Bişiler boş kardeş !",Toast.LENGTH_LONG).show();
        }
    }

    private void firebaseRegister(){
        mAuth.createUserWithEmailAndPassword(userEmail,userPassword)
               .addOnCompleteListener(this, new
                       OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(),
                                    "Kayıt Başarılı",
                                    Toast.LENGTH_LONG).show();
                            Intent i = new Intent(
                                    RegisterActivity.this,
                                    LoginActivity.class);
                            startActivity(i);
                        }
                   }
               }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if(e instanceof FirebaseException) {
                    if(((FirebaseAuthException) e)
                            .getErrorCode().equals("ERROR_WEAK_PASSWORD")) {
                        Toast.makeText(getApplicationContext(),"Şifre Zayıf Dostum"
                        ,Toast.LENGTH_LONG).show();
                    }else if(((FirebaseAuthException) e)
                            .getErrorCode().equals("ERROR_INVALID_EMAIL")) {
                        Toast.makeText(getApplicationContext(),"Mailin Geçersiz"
                                ,Toast.LENGTH_LONG).show();
                    }else if(((FirebaseAuthException) e)
                            .getErrorCode().equals("ERROR_EMAIL_ALREADY_IN_USE")) {
                        Toast.makeText(getApplicationContext(),"Bu email zaten var"
                                ,Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
 }
