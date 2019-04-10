package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChordsSounds extends AppCompatActivity {

    private MediaPlayer media;
    private List<Button> btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chords_sounds);

        Intent myIntent = getIntent();
        TextView chordsName = findViewById(R.id.chordsNames);

        String chordsNameString = myIntent.getStringExtra("ChordsName");

        chordsName.setText(chordsNameString.toUpperCase().replaceAll(".(?!$)", "$0 "));

        ImageView imageView =  findViewById(R.id.chords_sounds_image);
        imageView.setImageResource(this.getResources().getIdentifier(chordsNameString + "2", "drawable", this.getPackageName()));

        RelativeLayout lLayout = findViewById(R.id.ll_chordsNames);

        LinearLayout newLL = new LinearLayout(this);
        newLL.setOrientation(LinearLayout.HORIZONTAL);

        btnList = new ArrayList<>();
        int weightCount = 0;
        for(int x=0;x<chordsNameString.length();x++)
        {
            String textChordName = chordsNameString.charAt(x) + "";

            final String chordSoundName = chordsNameString.charAt(x) + "";
            final String filename = "android.resource://" + this.getPackageName() + "/raw/"; //issue wrong file pointer for fixing

            switch (chordsNameString.charAt(x) + ""){
                case "m":   btnList.get(btnList.size()-1).setText(btnList.get(btnList.size()-1).getText() + "m");
                            //setFileName(btnList.size()-1, filename + btnList.get(btnList.size()-1).getText().toString().toLowerCase().replace("play ",""));
                            setFileNamePost(filename);
                            break;
                case "7":   btnList.get(btnList.size()-1).setText(btnList.get(btnList.size()-1).getText() +"7");
                            //setFileName(btnList.size()-1, filename + btnList.get(btnList.size()-1).getText().toString().toLowerCase().replace("play ",""));
                            setFileNamePost(filename);
                            break;
                case "9":   btnList.get(btnList.size()-1).setText(btnList.get(btnList.size()-1).getText() +"9");
                            //setFileName(btnList.size()-1, filename + btnList.get(btnList.size()-1).getText().toString().toLowerCase().replace("play ",""));
                            setFileNamePost(filename);
                            break;
                default:
                    btnList.add(new Button(this));
                    btnList.get(btnList.size()-1).setText("Play " + textChordName.toUpperCase() + "");

                    /*
                    btnList.get(btnList.size()-1).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //media = MediaPlayer.create(getApplicationContext(), R.raw.g);
                            media = new MediaPlayer();
                            try { media.setDataSource(getApplicationContext(), Uri.parse(filename)); } catch (Exception e) {}
                            try { media.prepare(); } catch (Exception e) {}
                            media.start();
                        }
                    });
                    */


                    setFileName(btnList.size()-1, filename + chordSoundName);

                    btnList.get(btnList.size()-1).setAllCaps(false);
                    LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    p.weight = 1;

                    btnList.get(btnList.size()-1).setLayoutParams(p);
                    newLL.addView(btnList.get(btnList.size()-1));

                    weightCount += 1;
            }
        }

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -2);
        newLL.setWeightSum(weightCount);
        //newLL.setBackgroundColor(Color.BLUE);
        newLL.setLayoutParams(lp);

        RelativeLayout.LayoutParams params= new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, R.id.ll_chordsNames2);
        newLL.setLayoutParams(params);

        lLayout.addView(newLL);

    }

    private void setFileName(int index, final String filename){

        btnList.get(index).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                media = new MediaPlayer();
                try { media.setDataSource(getApplicationContext(), Uri.parse(filename)); } catch (Exception e) {}
                try { media.prepare(); } catch (Exception e) {}
                media.start();
            }
        });
    }

    private void setFileNamePost(String filename)
    {
        setFileName(btnList.size()-1, filename + btnList.get(btnList.size()-1).getText().toString().toLowerCase().replace("play ",""));
    }
}
