package com.example.meenutarun.cse227_2019;

/**
 * Created by MeenuTarun on 7/30/2019.
 */

public class P1Model
 {

    public static final int TEXT_TYPE=0;
    public static final int IMAGE_TYPE=1;
    public static final int AUDIO_TYPE=2;

    public int type;
    public int data;
    public String text;

    public P1Model(int type, String text, int data)
    {
        this.type=type;
        this.data=data;
        this.text=text;
    }
}
