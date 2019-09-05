package com.example.meenutarun.cse227_2019;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class P3AuthAndLoginFirebase extends AppCompatActivity {
    FirebaseAuth auth;
    EditText etname, etemail,etpass,etrepass;
    DatabaseReference root ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p3_auth_and_login_firebase);
        etname = (EditText) findViewById(R.id.etname);
        etemail = (EditText) findViewById(R.id.etemail);
        etpass = (EditText) findViewById(R.id.etpassword);
        etrepass =(EditText) findViewById(R.id.etrepassword);

        auth = FirebaseAuth.getInstance();

        root = FirebaseDatabase.getInstance().getReference();

    }

    public void doRegister(View view)
    {
        final String name = etname.getText().toString();
        String email = etemail.getText().toString();
        String pass = etpass.getText().toString();

        if(TextUtils.isEmpty(email))
        {
            etemail.setError("Email is required");
            etemail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            etemail.setError("Valid Email is required");
            etemail.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(pass))
        {
            etpass.setError("Pass is required");
            etpass.requestFocus();
            return;
        }
        if(pass.length() <6)
        {
            etpass.setError("Minimum 6 character requird");
            etpass.requestFocus();
            return;
        }
        Toast.makeText(P3AuthAndLoginFirebase.this, "Before Create User",Toast.LENGTH_SHORT).show();
        /*auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                Toast.makeText(P3AuthAndLoginFirebase.this, "Create User",Toast.LENGTH_SHORT).show();
                if(task.isSuccessful())
                {
                    Toast.makeText(P3AuthAndLoginFirebase.this,"hello",Toast.LENGTH_SHORT).show();
                    String uid = auth.getCurrentUser().getUid();
                    root.child(uid).setValue(name);
                    Toast.makeText(P3AuthAndLoginFirebase.this,"User Account created", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(P3AuthAndLoginFirebase.this,P3Login.class));
                }
                else
                {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(P3AuthAndLoginFirebase.this,"User Already Existes", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(P3AuthAndLoginFirebase.this,P3AuthAndLoginFirebase.class));

                    }
                    else
                    {
                        Toast.makeText(P3AuthAndLoginFirebase.this,"Errrrrrrrroooooooorrrrrrrrrr", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(P3AuthAndLoginFirebase.this,"failureeeeeeeeee", Toast.LENGTH_SHORT).show();

            }
        });

*/
        auth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(P3AuthAndLoginFirebase.this, "Create User",Toast.LENGTH_SHORT).show();

                        //checking if success
                        if(task.isSuccessful()){
                            //display some message here
                            Toast.makeText(P3AuthAndLoginFirebase.this,"Successfully registered",Toast.LENGTH_LONG).show();
                        }else{
                            //display some message here
                            Toast.makeText(P3AuthAndLoginFirebase.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        //progressDialog.dismiss();
                    }
                });


    }
}

