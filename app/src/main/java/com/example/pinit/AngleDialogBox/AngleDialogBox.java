package com.example.pinit.AngleDialogBox;

import androidx.annotation.NonNull;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;

import com.example.pinit.R;

public class AngleDialogBox extends Dialog {
    public AngleDialogBoxInterface angleDialogBoxInterface;

    public AngleDialogBox(@NonNull Context context,AngleDialogBoxInterface angleDialogBoxInterface) {
        super(context);
        this.angleDialogBoxInterface = angleDialogBoxInterface;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_angle_dialog_box);

        //TODO design better angleDialogBox

        ViewGroup contentView=(ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0); //Dialog baseView
        angleDialogBoxInterface.customizeDialogBox(contentView);
    }
}
