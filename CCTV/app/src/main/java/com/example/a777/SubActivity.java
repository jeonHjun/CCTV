package com.example.a777;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SubActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mCall;
    private Button mDialogCall;
    private EditText mEditNumber;
    private String mNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call);

        mCall = (Button) findViewById(R.id.btnCall);
        mDialogCall = (Button) findViewById(R.id.btnDialog);
        mEditNumber = (EditText) findViewById(R.id.edtNumber);

        mCall.setOnClickListener(this);
        mDialogCall.setOnClickListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 3924) {
            if(grantResults.length >= 1) {
                // TODO : 퍼미션 허용 시 할 동작
                mNum = mEditNumber.getText().toString();
                String tel = "tel:" + mNum;
                startActivity(new Intent("android.intent.action.CALL", Uri.parse(tel)));
            }
        }
    }

    @Override
    public void onClick(View v) {

        mNum = mEditNumber.getText().toString();
        String tel = "tel:" + mNum;
        switch (v.getId()){
            case R.id.btnCall:
                if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 3924);
                }else{
                    startActivity(new Intent("android.intent.action.CALL", Uri.parse(tel)));
                }
                break;
            case R.id.btnDialog:
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
                break;
        }
    }}

