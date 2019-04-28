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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button btnGiris,btnRegister;
    private EditText  edtPassword,edtEmail;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnGiris = findViewById(R.id.buttonLogin);
        edtEmail = findViewById(R.id.editTextEmailLogin);
        edtPassword = findViewById(R.id.editTextPasswordLogin);
        mAuth = FirebaseAuth.getInstance();
        btnRegister = findViewById(R.id.buttonRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           Intent i = new Intent(LoginActivity.this
                   ,RegisterActivity.class);
           startActivity(i);

                 }
    });
        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email =  edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                // edtEmail.getText().toString().trim().isEmpty()
                if(!email.isEmpty() && !password.isEmpty()) {
                    login(email,password);
                }else {
                    Toast.makeText(getApplicationContext(),
                            "Kardeş boş buralar",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void login(String email,String password){

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this,
                        new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent i = new Intent(
                                    LoginActivity.this,
                                    MainActivity.class
                            );
                            startActivity(i);
                         //   finish();
                        }
                    }
                }).addOnFailureListener(this,
                new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
}
