package com.example.pinit.PinActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pinit.AngleDialogBox.AngleDialogBox;
import com.example.pinit.AngleDialogBox.AngleDialogBoxInterface;
import com.example.pinit.SensorManager.SensorAngleResult;
import com.example.pinit.SensorManager.SensorConstants;
import com.example.pinit.SensorManager.SensorManager;
import com.example.pinit.R;

public class PinActivity extends AppCompatActivity {
    AngleDialogBox dialogBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //Hide action bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); //Hide title bar
        setContentView(R.layout.activity_pin);
    }

    public void onCaptureButtonClick(View v){
        new SensorManager(this, true, new SensorAngleResult() {

            @Override
            public void passResult(final int azimuth,final int roll,int pitch) {

                //Check pitch (Phone tilt)
                if(Math.abs(pitch)> SensorConstants.pitchLimit){
                    Toast.makeText(PinActivity.this, R.string.alertTextViewInvalidPitch, Toast.LENGTH_SHORT).show();
                    return;
                }
                //Check roll (Beyond hemisphere)
                else if(roll>SensorConstants.rollLimit){
                    Toast.makeText(PinActivity.this, R.string.alertTextViewInvalidRoll, Toast.LENGTH_SHORT).show();
                    return;
                }

                //Display dialog box
                dialogBox = new AngleDialogBox(
                        PinActivity.this,
                        new AngleDialogBoxInterface() {
                            @Override
                            public void onDialogBoxButtonClick() {
                                dialogBox.dismiss();
                            }

                            @Override
                            public void customizeDialogBox(ViewGroup dialogBaseView) {
                                LinearLayout rowOne= dialogBaseView.findViewById(R.id.dialogBoxRowOne);
                                rowOne.removeViewAt(2);
                                TextView azimuthTextView= (TextView)rowOne.getChildAt(1);
                                azimuthTextView.setText(String.valueOf(azimuth+180));

                                LinearLayout rowTwo= dialogBaseView.findViewById(R.id.dialogBoxRowTwo);
                                rowTwo.removeViewAt(2);
                                TextView rollTextView= (TextView)rowTwo.getChildAt(1);
                                rollTextView.setText(String.valueOf(roll+180));
                            }
                        }
                );
                dialogBox.show();
            }
        });
    }

    public void onDialogButtonClick(View view){
        dialogBox.angleDialogBoxInterface.onDialogBoxButtonClick();
    }

}
