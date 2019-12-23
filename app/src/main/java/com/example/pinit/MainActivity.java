package com.example.pinit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.example.pinit.PermissionManager.PermissionManager;
import com.example.pinit.PinActivity.PinActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionManager.getCameraPermission(this);
    }

    public void onPinButtonClick(View button){
        Intent intent = new Intent(this, PinActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(grantResults[0]!= PackageManager.PERMISSION_GRANTED){
            PermissionManager.getCameraPermission(this);
        }
    }
}
