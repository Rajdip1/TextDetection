package com.example.textscanning;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

public class OnBoardingScreen extends TutorialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_on_boarding_screen);

        addFragment(new Step.Builder().setTitle("Extract Text from images using ML")
                .setContent("Recognize text in images with ML Kit on Android")
                .setBackgroundColor(Color.parseColor("#FF0957")) // int background color
                .setDrawable(R.drawable.onboard1) // int top drawable
                .setSummary("You can use ML Kit to recognize text in images or video, such as the text of a street sign. ")
                .build());

        addFragment(new Step.Builder().setTitle("Copy and use the extracted text in seconds")
                .setContent("Recognize text in images with ML Kit on Android")
                .setBackgroundColor(Color.parseColor("#FF0957")) // int background color
                .setDrawable(R.drawable.onboard2) // int top drawable
                .setSummary("You can use ML Kit to recognize text in images or video, such as the text of a street sign. ")
                .build());

        addFragment(new Step.Builder().setTitle("This is header")
                .setContent("This is content")
                .setBackgroundColor(Color.parseColor("#FF0957")) // int background color
                .setDrawable(R.drawable.onboard1) // int top drawable
                .setSummary("You can use ML Kit to recognize text in images or video, such as the text of a street sign. ")
                .build());

    }

    @Override
    public void finishTutorial() {
        // Your implementation
    }

    @Override
    public void currentFragmentPosition(int position) {

    }

}