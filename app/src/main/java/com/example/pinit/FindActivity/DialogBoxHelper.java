package com.example.pinit.FindActivity;

import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.pinit.AngleDialogBox.AngleDialogBox;
import com.example.pinit.AngleDialogBox.DialogBoxButtonAction;
import com.example.pinit.AngleDialogBox.DialogBoxViewCustomizer;
import com.example.pinit.R;

/*
*
*   A helper class built on the existing AngleDialogBox for the FindActivity
*
* */

public class DialogBoxHelper {
    private Context context;
    private ViewGroup dialogBoxBaseView;
    private AngleDialogBox dialogBox;

    public DialogBoxHelper(Context context) {
        this.context=context;
    }

    public void showDialogBoxForFindActivity(){
        dialogBox=new AngleDialogBox(
                context,
                new DialogBoxViewCustomizer() {
                    @Override
                    public void customizeDialogBox(ViewGroup dialogBaseView) {
                        LinearLayout rowOne= dialogBaseView.findViewById(R.id.dialogBoxRowOne);
                        rowOne.removeViewAt(1);

                        LinearLayout rowTwo= dialogBaseView.findViewById(R.id.dialogBoxRowTwo);
                        rowTwo.removeViewAt(1);
                        dialogBoxBaseView=dialogBaseView;
                    }
                },
                new DialogBoxButtonAction() {
                    @Override
                    public void onDialogBoxButtonClick() {
                        //Get edit texts
                        LinearLayout rowOne =dialogBoxBaseView.findViewById(R.id.dialogBoxRowOne);
                        String pitchString=((EditText)rowOne.getChildAt(1)).getText().toString();
                        if(pitchString.length()==0){
                            Toast.makeText(context, "Enter Pitch Angle", Toast.LENGTH_LONG).show();
                            return;
                        }
                        int pitch = Integer.parseInt(pitchString);

                        LinearLayout rowTwo= dialogBoxBaseView.findViewById(R.id.dialogBoxRowTwo);
                        String rollString=((EditText)rowTwo.getChildAt(1)).getText().toString();
                        if(rollString.length()==0){
                            Toast.makeText(context, "Enter Roll Angle", Toast.LENGTH_LONG).show();
                            return;
                        }
                        int roll = Integer.parseInt(rollString);

                        //Verify
                        if(pitch<0 || pitch>360){
                            Toast.makeText(context, "Invalid Pitch Angle", Toast.LENGTH_LONG).show();
                            return;
                        }
                        else if(roll<0 || roll>180){
                            Toast.makeText(context, "Invalid Roll Angle", Toast.LENGTH_LONG).show();
                            return;
                        }

                        //Open new activity
                        dialogBox.dismiss();
                        Intent intent = new Intent(context, FindActivity.class);
                        context.startActivity(intent);
                    }
                }
        );
        dialogBox.show();
    }

    public void triggerDialogboxClick(){
        dialogBox.dialogBoxButtonAction.onDialogBoxButtonClick();
    }

}