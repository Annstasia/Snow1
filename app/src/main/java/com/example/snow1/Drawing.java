package com.example.snow1;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

import androidx.annotation.Nullable;

class Snowflake {
    public class Drawing { }
    float x, y, velocity, height;
    Random rand = new Random();
    public Snowflake(float height, float width) {

        x = rand.nextInt((int)width);
        y = rand.nextInt((int)width);
        velocity = rand.nextFloat();
        this.height = height;

        // 1) сгенерировать случайные координаты и скорость
        // в пределах от 0 до 1
    }


    public void fall() {
        y += velocity;
        y %= height;
        // 2) предусмотреть перерождение снежинки
    }
}

public class Drawing extends View {
    float radius = 10, height = 1354, width = 720;
    Snowflake[] snowflakes;
    Paint p = new Paint();
    float w, h;
    public Drawing(Context context) {
        super(context);
    }

    public Drawing(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        Log.d("WIDTH", "" + height);
        snowflakes = new Snowflake[15];
        // падение снежинок при касании
        for (int i = 0; i < snowflakes.length; i++) {
            snowflakes[i] = new Snowflake(height, width);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLACK);
        p.setColor(Color.parseColor("#FFFFFF"));
        for (int i = 0; i < snowflakes.length; i++) {
            canvas.drawCircle(snowflakes[i].x, snowflakes[i].y, radius, p);
        }

        // 3) нарисовать всё снежинки
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        for (int i = 0; i < 15; i++) {
            snowflakes[i].fall();
        }

        invalidate();
        return true;

    }
}