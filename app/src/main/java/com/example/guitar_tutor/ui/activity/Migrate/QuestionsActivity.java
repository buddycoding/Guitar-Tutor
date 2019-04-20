package com.example.guitar_tutor.ui.activity.Migrate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.guitar_tutor.R;
import com.github.airsaid.library.widget.Chord;
import com.github.airsaid.library.widget.ChordView;


public class QuestionsActivity extends AppCompatActivity {

    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        button1 = (Button) findViewById(R.id.buttonhome);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                //openMainActivity();
            }
        });

        ChordView mChordView = findViewById(R.id.chordView1);
        //Chord chord = new Chord(new int[]{-1, 3, 2, 0, 1, 4}, new int[]{0, 3, 2, 0, 1, 4});
        //fret number & (string position and label)
        Chord chord = new Chord(new int[]{-1, -1, 0, 2, 3, 2}, new int[]{0, 0, 0, 1, 3, 2});
        mChordView.setChord(chord);
    }

    private void randQuestion()
    {

    }
}
