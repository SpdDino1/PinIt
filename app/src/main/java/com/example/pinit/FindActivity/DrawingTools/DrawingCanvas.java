package com.example.pinit.FindActivity.DrawingTools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.View;

public class DrawingCanvas extends View {
    private Bitmap bitmap;
    private Canvas myCanvas;
    private Paint paint;

    private final int CIRCLE_RADIUS = 25;

    public DrawingCanvas(Context context, AttributeSet attrs) {
        super(context,attrs);
        paint = new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        myCanvas = new Canvas(bitmap);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap,0,0,paint);
    }

    void drawCircle(int x,int y){
        myCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        myCanvas.drawCircle(x,y,CIRCLE_RADIUS,paint);
        invalidate();
    }
}