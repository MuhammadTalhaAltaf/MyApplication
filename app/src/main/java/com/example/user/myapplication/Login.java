package com.example.user.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener firebaseAuthListner;

    private EditText userid, password;
    private Button loginbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userid = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);

        loginbtn = (Button) findViewById(R.id.button);

        auth = FirebaseAuth.getInstance();
        firebaseAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null)
                {
                    startActivity(new Intent(Login.this, Chef.class));
                }
            }
        };

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignIn();

            }
        });

    }
    private void startSignIn(){
        String email = userid.getText().toString();
        String pass = password.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pass))
        {
            Toast.makeText(Login.this, "Fields are empty", Toast.LENGTH_SHORT).show();
        }
        else
        {
            auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful())
                    {
                        Toast.makeText(Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
    @Override
    protected void onStart(){
        super.onStart();
        auth.addAuthStateListener(firebaseAuthListner);
    }

    @Override
    protected void onStop() {
        super.onStop();
        auth.removeAuthStateListener(firebaseAuthListner);
    }
}
