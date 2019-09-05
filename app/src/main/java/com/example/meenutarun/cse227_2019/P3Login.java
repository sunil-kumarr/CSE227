package com.example.meenutarun.cse227_2019;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class P3Login extends AppCompatActivity {


    EditText etLoginemail, etLoginpass;

    FirebaseAuth auth;
    DatabaseReference root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p3_login);
        etLoginemail = (EditText) findViewById(R.id.etloginemail);
        etLoginpass = (EditText) findViewById(R.id.etloginpassword);

        auth = FirebaseAuth.getInstance();
        root = FirebaseDatabase.getInstance().getReference();

    }


    public void doLogin(View view)
    {
        String email = etLoginemail.getText().toString();
        String pass = etLoginpass.getText().toString();

        if(TextUtils.isEmpty(email))
        {
            etLoginemail.setError("Email is required");
            etLoginemail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            etLoginemail.setError("Not Valid Email Required");
            etLoginemail.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(pass))
        {
            etLoginpass.setError("Pass is required");
            etLoginpass.requestFocus();
            return;
        }
        if(pass.length() <6)
        {
            etLoginemail.setError("Minimum 6 character requird");
            etLoginpass.requestFocus();
            return;
        }
        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    Toast.makeText(P3Login.this,"Login Sucessful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(P3Login.this,P3UserActiity.class));
                    finish();
                }
                else
                {
                    if(task.getException() instanceof FirebaseAuthInvalidUserException)
                    {
                        Toast.makeText(P3Login.this,"User is not register", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(P3Login.this,P3AuthAndLoginFirebase.class));
                        finish();
                    }
                }
            }
        });

    }

    public void doRegister(View view)
    {
        Intent i = new Intent(this,P3AuthAndLoginFirebase.class);
        startActivity(i);
        //finish();
    }
}
