package com.example.meenutarun.cse227_2019;
/*We’ll be implementing three view types (text, image, audio) that are
inflated by three different layouts. Each has its own implementation
 specified in the adapter class.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class P1RecyclerViewLayouts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p1_recycler_view_layouts);



        ArrayList<P1Model> list= new ArrayList();
        list.add(new P1Model(P1Model.TEXT_TYPE,"Hello. This is the Text-only View Type. Nice to meet you",0));
        list.add(new P1Model(P1Model.IMAGE_TYPE,"Hi. I display a cool image too besides the omnipresent TextView.",R.drawable.sound));
        list.add(new P1Model(P1Model.AUDIO_TYPE,"Hey. Pressing the FAB button will playback an audio file on loop.",R.raw.sun));
        list.add(new P1Model(P1Model.IMAGE_TYPE,"Hi again. Another cool image here. Which one is better?",R.drawable.snow));

        P1MultiViewTypeAdapter adapter = new P1MultiViewTypeAdapter(list,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
    }
}
