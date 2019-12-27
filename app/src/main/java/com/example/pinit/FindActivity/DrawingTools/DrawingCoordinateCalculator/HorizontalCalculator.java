package com.example.pinit.FindActivity.DrawingTools.DrawingCoordinateCalculator;

public class HorizontalCalculator {
    private int targetOffsetAzimuth;

    private float halfScreenWidth;
    private float scalingFactor;

    public HorizontalCalculator(int screenWidth,int offsetAzimuth) {
        this.targetOffsetAzimuth=offsetAzimuth;

        this.halfScreenWidth=screenWidth/2;
        this.scalingFactor=(halfScreenWidth/180);
    }

     public int getXAxisCoordinate(int currentOffsetAzimuth){
         int rightDisplacement;
         int leftDisplacement;
         if(currentOffsetAzimuth<targetOffsetAzimuth){
            rightDisplacement=targetOffsetAzimuth-currentOffsetAzimuth;
            leftDisplacement=(360-targetOffsetAzimuth)+currentOffsetAzimuth;
         }
         else{
             leftDisplacement=currentOffsetAzimuth-targetOffsetAzimuth;
             rightDisplacement=(360-currentOffsetAzimuth)+targetOffsetAzimuth;
         }

         if(rightDisplacement<leftDisplacement){
             //Go right
             return (int)((scalingFactor*rightDisplacement)+halfScreenWidth);
         }
         else{
             //Go left
             return (int)(halfScreenWidth-(scalingFactor*leftDisplacement));
         }
    }
}
