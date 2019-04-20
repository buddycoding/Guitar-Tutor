package com.example.guitar_tutor.ui.activity.Migrate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.guitar_tutor.R;
import com.example.guitar_tutor.ui.activity.Migrate.Adapters.QuestionsList;
import com.github.airsaid.library.widget.Chord;
import com.github.airsaid.library.widget.ChordView;

import java.util.ArrayList;
import java.util.Random;


public class QuestionsActivity extends AppCompatActivity {

    private Button button1, button2, button3;
    private RadioButton radioButton;
    private RadioGroup radioGroup;
    ArrayList<QuestionsList> questions = QuestionsList.createQuestionList();
    private int answer;

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

        button2 = findViewById(R.id.buttonsubmit);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAnswer();
            }
        });

        button3 = findViewById(R.id.buttonnext);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextChord();
            }
        });


        radioGroup = findViewById(R.id.radio_group);

        randQuestion();

    }

    private void nextChord()
    {
        randQuestion();
    }

    private void randQuestion()
    {
        radioGroup.clearCheck();

        Random rand = new Random();
        int value = rand.nextInt(questions.size());

        ChordView mChordView = findViewById(R.id.chordView1);
        //Chord chord = new Chord(new int[]{-1, 3, 2, 0, 1, 4}, new int[]{0, 3, 2, 0, 1, 4});
        //fret number & (string position and label)
        Chord chord = new Chord(questions.get(value).getFret(), questions.get(value).getString());
        mChordView.setChord(chord);

        rand = new Random();
        int value2 = rand.nextInt(3);

        switch (value2)
        {
            case 0:
                radioButton = findViewById(R.id.radio_a);
                break;
            case 1:
                radioButton = findViewById(R.id.radio_b);
                break;
            case 2:
                radioButton = findViewById(R.id.radio_c);
                break;
        }

        answer = radioButton.getId();
        radioButton.setText(questions.get(value).getName().toUpperCase()+ " Chord");

        String holderName = "";
        String holderName2 = radioButton.getText().toString();

        for(int x = 0; x < radioGroup.getChildCount(); x++)
        {
            if(x!=radioGroup.indexOfChild(radioButton))
            {
                RadioButton radioButton2 = findViewById(radioGroup.getChildAt(x).getId());
                radioButton2.setText(randomChoice(questions.size(), value).toUpperCase() + " Chord");

                while(holderName.equals(radioButton2.getText().toString())){
                    radioButton2.setText(randomChoice(questions.size(), value).toUpperCase() + " Chord");
                }

                while(holderName2.equals(radioButton2.getText().toString())){
                    radioButton2.setText(randomChoice(questions.size(), value).toUpperCase() + " Chord");
                }

                holderName = radioButton2.getText().toString();
            }
        }
    }

    private String randomChoice(int bound, int check)
    {
        Random rand = new Random();
        int value = rand.nextInt(bound);

        while(value == check)
        {
            value = rand.nextInt(bound);
        }

        return questions.get(value).getName();
    }

    private void submitAnswer()
    {
        int selectedRadio = radioGroup.getCheckedRadioButtonId();
        String message = "Wrong answer!";

        if(selectedRadio == answer)
        {
            message = "Correct answer!";
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
