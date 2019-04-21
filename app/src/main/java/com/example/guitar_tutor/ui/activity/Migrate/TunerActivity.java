package com.example.guitar_tutor.ui.activity.Migrate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.guitar_tutor.R;


public class TunerActivity extends AppCompatActivity {

    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuner);

        button1 = (Button) findViewById(R.id.buttonhome);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
                //openMainActivity();
            }
        });
    }
}
