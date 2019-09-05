package com.example.meenutarun.cse227_2019;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class P3UserActiity extends AppCompatActivity {
    FirebaseAuth auth;
    DatabaseReference root;
    FirebaseUser user;
    ImageView iv;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p3_user_actiity);
        iv =(ImageView) findViewById(R.id.ivuser);
        tv = (TextView) findViewById(R.id.tvuser);

        auth = FirebaseAuth.getInstance();
        root = FirebaseDatabase.getInstance().getReference();

        user = auth.getCurrentUser();
        if(user!= null)
        {
            displayDetails();
        }

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImage();
            }
        });

    }

    public void openImage()
    {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode ==1 && resultCode == RESULT_OK)
        {
            if(data.getData() != null)
            {
                Uri uri = data.getData();
                iv.setImageURI(uri);
            }
        }
    }

    public void displayDetails()
    {
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                DataSnapshot ds = dataSnapshot.child(auth.getCurrentUser().getUid());
                String name = (String) ds.getValue();

                tv.setText("\n"+name);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
