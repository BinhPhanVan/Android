package com.example.pianoapp;

import android.graphics.RectF;

public class Key {
    public int sound;
    public RectF rectf;
    public boolean down;

    public Key(int sound, RectF rectf) {
        this.sound = sound;
        this.rectf = rectf;
    }

}
