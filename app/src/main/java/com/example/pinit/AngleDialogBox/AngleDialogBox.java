package com.example.pinit.AngleDialogBox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.example.pinit.R;

public class AngleDialogBox extends Dialog {
    private DialogBoxViewCustomizer dialogBoxViewCustomizer;
    public DialogBoxButtonAction dialogBoxButtonAction;

    public AngleDialogBox(@NonNull Context context,DialogBoxViewCustomizer dialogBoxViewCustomizer,DialogBoxButtonAction dialogBoxButtonAction) {
        super(context);
        this.dialogBoxViewCustomizer=dialogBoxViewCustomizer;
        this.dialogBoxButtonAction=dialogBoxButtonAction;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_angle_dialog_box);

        ViewGroup contentView=(ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0); //Dialog baseView
        dialogBoxViewCustomizer.customizeDialogBox(contentView);
    }
}
