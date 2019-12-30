package com.example.pinit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.example.pinit.FindActivity.DialogBoxHelper;
import com.example.pinit.PermissionManager.PermissionManager;
import com.example.pinit.PinActivity.PinActivity;

public class MainActivity extends AppCompatActivity {
    private DialogBoxHelper helperDialogBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionManager.getCameraPermission(this);

        //TODO Add app icon and release apk
    }

    public void onPinButtonClick(View button){
        Intent intent = new Intent(this, PinActivity.class);
        startActivity(intent);
    }

    public void onFindButtonClick(View view){
        helperDialogBox= new DialogBoxHelper(this);
        helperDialogBox.showDialogBoxForFindActivity();
    }

    public void onDialogButtonClick(View view){
        helperDialogBox.triggerDialogboxClick();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(grantResults[0]!= PackageManager.PERMISSION_GRANTED){
            PermissionManager.getCameraPermission(this);
        }
    }
}