package com.willpower.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;

import com.willpower.lib.JBasicDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JBasicDialog.create(MainActivity.this).width(0.85f)
                .height(WindowManager.LayoutParams.WRAP_CONTENT)
                .content("setContentView(R.layout.activity_main);")
                .show();
    }
}
