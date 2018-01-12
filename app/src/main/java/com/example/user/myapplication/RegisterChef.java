package com.example.user.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class RegisterChef extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    private EditText mEmailRegistration, mPasswordRegistration, mAddressRegistration, mNumberRegistration, mGenderRegistration, mUsernameRegistration;

    private Button mButtonRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_chef);

        mAuth = FirebaseAuth.getInstance();

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(RegisterChef.this, Home.class);
                    startActivity(intent);
                    finish();
                }
                return;
            }
        };

        mEmailRegistration = (EditText) findViewById(R.id.editText);
        mPasswordRegistration = (EditText) findViewById(R.id.editText2);
        mAddressRegistration = (EditText) findViewById(R.id.editText13);
        mNumberRegistration = (EditText) findViewById(R.id.editText14);
        mGenderRegistration = (EditText) findViewById(R.id.editText15);
        mUsernameRegistration = (EditText) findViewById(R.id.editText16);


        mButtonRegistration = (Button) findViewById(R.id.button3);


        mButtonRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmailRegistration.getText().toString();
                final String password = mPasswordRegistration.getText().toString();
                final String username = mUsernameRegistration.getText().toString();

                Query usernameQuery = FirebaseDatabase.getInstance().getReference().child("Users").orderByChild("username").equalTo(username);
                usernameQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getChildrenCount() > 0) {
                            Toast.makeText(RegisterChef.this, "Choose a different UserName", Toast.LENGTH_SHORT).show();
                        } else {
                            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterChef.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(RegisterChef.this, "sign up error", Toast.LENGTH_SHORT).show();
                                    } else {
                                        String user_id = mAuth.getCurrentUser().getUid();
                                        DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);

                                        String username = mUsernameRegistration.getText().toString();
                                        String address = mAddressRegistration.getText().toString();
                                        String number = mNumberRegistration.getText().toString();
                                        String gender = mGenderRegistration.getText().toString();

                                        Map newPost = new HashMap();
                                        newPost.put("username", username);
                                        newPost.put("address", address);
                                        newPost.put("number", number);
                                        newPost.put("gender", gender);

                                        current_user_db.setValue(newPost);
                                    }
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        mAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthListener);

    }
}
