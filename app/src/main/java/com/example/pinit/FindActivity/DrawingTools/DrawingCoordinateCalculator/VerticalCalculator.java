package com.example.pinit.FindActivity.DrawingTools.DrawingCoordinateCalculator;

public class VerticalCalculator {
    private int targetOffsetRoll;

    private float halfScreenHeight;
    private float scalingFactor;

    public VerticalCalculator(int screenHeight, int targetOffsetRoll) {
        this.targetOffsetRoll = targetOffsetRoll;

        this.halfScreenHeight=screenHeight/2;
        this.scalingFactor=halfScreenHeight/180;
    }

    public int getYAxisCoordinate(int currentOffsetRoll){
        int displacement = currentOffsetRoll-targetOffsetRoll;

        if(displacement<0){
            //Go down
            return (int)(halfScreenHeight+(Math.abs(displacement)*scalingFactor));
        }
        else{
            //Go up
            return (int)(halfScreenHeight-(displacement*scalingFactor));
        }
    }
}
