package com.example.tiktaktoe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomView extends View {

    private float startX, stopX, startY, stopY;

    public void setStartX(float startX) {
        this.startX = startX;
    }

    public void setStopX(float stopX) {
        this.stopX = stopX;
    }

    public void setStartY(float startY) {
        this.startY = startY;
    }

    public void setStopY(float stopY) {
        this.stopY = stopY;
    }


    private Paint paint;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(startX, startY, stopX, stopY, paint);


    }

    private void initPaint() {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(16);
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.black));
    }
}
