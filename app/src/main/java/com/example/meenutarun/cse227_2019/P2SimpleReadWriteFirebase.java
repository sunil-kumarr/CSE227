package com.example.meenutarun.cse227_2019;
// go to Tools--> Firebase--> RealtimeDatabase--> Connect Firebase --> then add Realtime database to your app
//to read and write data in database
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class P2SimpleReadWriteFirebase extends AppCompatActivity {
DatabaseReference ref;
    ListView lv;
    ArrayList<String> al;
    ArrayAdapter<String> ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p2simplereadwritefirebase);
    ref=FirebaseDatabase.getInstance().getReference();
        lv = (ListView) findViewById(R.id.lv1);
        al = new ArrayList<>();
        ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,al);
        lv.setAdapter(ad);
        //fixed values write in Database
        ref.child("Name").setValue("Amarider");
        ref.child("Phone Number").setValue("1234");
        ref.child("Profession").setValue("Astt. Professor");
        ref.child("Name").setValue("Amarider1");


    }
    public void doRead(View view)
    {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    String s1 = "";
                    Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                    for (DataSnapshot ds : iterable) {
                        Toast.makeText(P2SimpleReadWriteFirebase.this, "" + ds.getValue(), Toast.LENGTH_SHORT).show();
                        s1 = s1 + ds.getValue();
                    }
                        al.add(s1);
                    }
                    ad.notifyDataSetChanged();

                }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}