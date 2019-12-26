package com.example.pinit.FindActivity.DrawingTools;

import android.view.View;

import com.example.pinit.FindActivity.DrawingTools.DrawingCoordinateCalculator.HorizontalCalculator;

public class DrawingManager {

    private HorizontalCalculator horizontalCalculator;

    private DrawingCanvas canvas;
    private boolean isCanvasReady=false;

    public DrawingManager(DrawingCanvas canvas, final int offsetAzimuth, int offsetRoll) {
        this.canvas=canvas;

        canvas.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                //Called once canvas is rendered
                DrawingManager.this.canvas.removeOnLayoutChangeListener(this);

                DrawingManager.this.isCanvasReady=true;
                horizontalCalculator = new HorizontalCalculator(view.getWidth(),offsetAzimuth);
            }
        });

    }

    public void drawCircle(int currentOffsetAzimuth,int currentOffsetRoll){
        if(isCanvasReady) {
            canvas.drawCircle(horizontalCalculator.getXAxisCoordinate(currentOffsetAzimuth), canvas.getHeight() / 2);
        }
    }


}
