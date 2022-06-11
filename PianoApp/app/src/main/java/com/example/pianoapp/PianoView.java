package com.example.pianoapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class PianoView extends View {
    public static final int NUMBER_OF_KEY = 14;
    private Paint black, white, yellow, blackLine;
    private ArrayList<Key> whites, blacks;
    private int keyWidth, keyHeight;
    private SoundManager soundManager;

    public PianoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        black = new Paint();
        black.setColor(Color.BLACK);
        black.setStyle(Paint.Style.FILL);

        blackLine = new Paint();
        blackLine.setColor(Color.BLACK);
        blackLine.setStrokeWidth(4);

        white = new Paint();
        white.setColor(Color.WHITE);
        white.setStyle(Paint.Style.FILL);

        yellow = new Paint();
        yellow.setColor(Color.YELLOW);
        yellow.setStyle(Paint.Style.FILL);

        whites = new ArrayList<Key>();
        blacks = new ArrayList<Key>();

        soundManager = SoundManager.getInstance();
        soundManager.init(context);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        keyWidth = w/NUMBER_OF_KEY;
        keyHeight = h;
        int count=1;
        for (int i=0; i< NUMBER_OF_KEY;i++)
        {
            int left = i*keyWidth;
            int right = left+ keyWidth;
            RectF rectF = new RectF(left, 0, right, h);
            whites.add(new Key(i+1, rectF));

            if( i!=0 && i!=3 && i!=7 && i!=10)
            {
                RectF rect = new RectF((float) ((float) (i - 1) * keyWidth + 0.75 * keyWidth),
                        0, (float) i * keyWidth + 0.25f * keyWidth,
                        0.67f * keyHeight);
                blacks.add(new Key(count, rect));
                count ++;
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Key k:whites)
        {
            canvas.drawRect(k.rectf, k.down?yellow:white);
        }
        for (Key k:blacks)
        {
            canvas.drawRect(k.rectf, k.down?yellow:black);
        }
        for (int i=1; i<NUMBER_OF_KEY;i++)
        {
            canvas.drawLine(i*keyWidth,
                    0,
                    i*keyWidth,
                    keyHeight,
                    blackLine);
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        boolean isDownAction = action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE;
        for(int touchIndex =0 ; touchIndex< event.getPointerCount(); touchIndex++)
        {
            float x = event.getX(touchIndex);
            float y = event.getY(touchIndex);
            for(Key k:blacks)
            {
                if (k.rectf.contains(x,y))
                {
                    k.down = isDownAction;
                    x = -1;
                    y = -1;
                }
                else
                {
                    k.down = false;
                }
            }
            for(Key k:whites)
            {
                if (k.rectf.contains(x,y))
                {
                    k.down = isDownAction;
                }
                else
                {
                    k.down = false;
                }
            }
            for(Key k:whites)
            {
                if(k.down)
                {
                    switch (k.sound)
                    {
                        case 1:
                            soundManager.playSound(R.raw.c3);
                            break;
                        case 2:
                            soundManager.playSound(R.raw.d3);
                            break;
                        case 3:
                            soundManager.playSound(R.raw.e3);
                            break;
                        case 4:
                            soundManager.playSound(R.raw.f3);
                            break;
                        case 5:
                            soundManager.playSound(R.raw.g3);
                            break;
                        case 6:
                            soundManager.playSound(R.raw.a3);
                            break;
                        case 7:
                            soundManager.playSound(R.raw.b3);
                            break;
                        case 8:
                            soundManager.playSound(R.raw.c4);
                            break;
                        case 9:
                            soundManager.playSound(R.raw.d4);
                            break;
                        case 10:
                            soundManager.playSound(R.raw.e4);
                            break;
                        case 11:
                            soundManager.playSound(R.raw.f4);
                            break;
                        case 12:
                            soundManager.playSound(R.raw.g4);
                            break;
                        case 13:
                            soundManager.playSound(R.raw.a4);
                            break;
                        case 14:
                            soundManager.playSound(R.raw.b4);
                            break;
                    }
                }
            }
            for(Key k:blacks)
            {
                if(k.down)
                {
                    switch (k.sound)
                    {
                        case 1:
                            soundManager.playSound(R.raw.db3);
                            break;
                        case 2:
                            soundManager.playSound(R.raw.eb3);
                            break;
                        case 3:
                            soundManager.playSound(R.raw.gb3);
                            break;
                        case 4:
                            soundManager.playSound(R.raw.ab3);
                            break;
                        case 5:
                            soundManager.playSound(R.raw.bb3);
                            break;
                        case 6:
                            soundManager.playSound(R.raw.db4);
                            break;
                        case 7:
                            soundManager.playSound(R.raw.eb4);
                            break;
                        case 8:
                            soundManager.playSound(R.raw.gb4);
                            break;
                        case 9:
                            soundManager.playSound(R.raw.ab4);
                            break;
                        case 10:
                            soundManager.playSound(R.raw.bb4);
                            break;
                    }
                }
            }
        }
        invalidate();
        return true;
    }
//    private void releaseKey(final Key k)
//    {
//        getHandler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                k.down = false;
//                getHandler().sendEmptyMessage(0);
//            }
//        }, 100);
//    }

}
