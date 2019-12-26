package com.example.pinit.PinActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pinit.AngleDialogBox.AngleDialogBox;
import com.example.pinit.AngleDialogBox.AngleDialogBoxInterface;
import com.example.pinit.PinActivity.SensorManager.SensorAngleResult;
import com.example.pinit.PinActivity.SensorManager.SensorManager;
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
            public void passResult(final int pitch,final int roll) {

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
                                //TODO Check azimuth and roll angle
                                LinearLayout rowOne= dialogBaseView.findViewById(R.id.dialogBoxRowOne);
                                rowOne.removeViewAt(2);
                                TextView pitchTextView= (TextView)rowOne.getChildAt(1);
                                pitchTextView.setText(String.valueOf(pitch+180));

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
