package com.example.tarea4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class FemalePhrases extends AppCompatActivity {
    ImageView userGender;
    TextView userName;
    TextView welcome;
    View toolbar;
    Button todayImage;
    Button randomImage;
    int todaysImage;
    int imageToShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_female_phrases);

        todaysImage = pickRandomImage();
        toolbar = findViewById(R.id.toolbar);
        userGender = toolbar.findViewById(R.id.userGender);
        userName = toolbar.findViewById(R.id.userName);
        todayImage = findViewById(R.id.todayImage);
        randomImage = findViewById(R.id.randomImage);
        welcome = findViewById(R.id.welcome);

        userGender.setBackgroundResource(R.drawable.female_user);
        userName.setText(getIntent().getStringExtra("userName"));
        welcome.setText(welcome.getText().toString().replace("placeholder", getIntent().getStringExtra("userName")));

        //region exit button
        Button exit = toolbar.findViewById(R.id.exitButton);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(FemalePhrases.this);

                builder.setMessage("Are you sure you want to ext?")
                        .setTitle("Exit Application")
                        .setPositiveButton("Yes, i'm sure", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No, go back", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });

                builder.setIcon(R.drawable.ic_launcher_background);
                builder.create();
                builder.show();
            }
        });
        //endregion

        //region back button
        ImageView back = toolbar.findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //endregion

        todayImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageToShow = todaysImage;
                image_alert alert = new image_alert();
                Bundle args = new Bundle();
                args.putInt("image", imageToShow);
                alert.setArguments(args);
                alert.show(getSupportFragmentManager(), "image");
            }
        });

        randomImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageToShow = pickRandomImage();
                image_alert alert = new image_alert();
                Bundle args = new Bundle();
                args.putInt("image", imageToShow);
                alert.setArguments(args);
                alert.show(getSupportFragmentManager(), "image");
            }
        });
    }

    private int pickRandomImage(){
        ArrayList<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.female_1);
        imageList.add(R.drawable.female_2);
        imageList.add(R.drawable.female_3);
        imageList.add(R.drawable.female_4);
        imageList.add(R.drawable.female_5);

        Random rand = new Random();
        return imageList.get(rand.nextInt(4) + 1);
    }
}