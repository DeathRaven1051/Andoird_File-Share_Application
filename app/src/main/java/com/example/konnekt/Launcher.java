package com.example.konnekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Launcher extends AppCompatActivity {

    private Button mAPK,mFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        mAPK = findViewById(R.id.apk_button);
        mFile = findViewById(R.id.file_button);

        mAPK.setOnClickListener(view -> startActivity(new Intent(Launcher.this,MainActivity.class)));

        mFile.setOnClickListener(view -> startActivity(new Intent(Launcher.this,FileActivity.class)));

    }


}