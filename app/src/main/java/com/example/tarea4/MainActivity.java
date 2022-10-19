package com.example.tarea4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText userName;
    RadioGroup userGender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.et1);
        userGender = findViewById(R.id.rg1);
        View toolbar = findViewById(R.id.toolbar);
        Button nextScreen = findViewById(R.id.nextScreen);
        nextScreen.setOnClickListener(this);

        //region exit button
        Button exit = toolbar.findViewById(R.id.exitButton);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setMessage("Are you sure you want to exit?")
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
    }

    private void MaleScreen(){
        Intent next = new Intent(this, MalePhrases.class);
        next.putExtra("userName", userName.getText().toString());
        startActivity(next);
    }

    private void FemaleScreen(){
        Intent next = new Intent(this, FemalePhrases.class);
        next.putExtra("userName", userName.getText().toString());
        startActivity(next);
    }

    @Override
    public void onClick(View view) {
        if (userName.getText().length() <= 0){
            Context context = getApplicationContext();
            CharSequence text = "You need to specify your name";
            int duration = Toast.LENGTH_SHORT;
            Toast errorToast = Toast.makeText(context, text, duration);
            errorToast.show();
        }
        else{
            int maleId = findViewById(R.id.chk1).getId();
            int femaleId = findViewById(R.id.chk2).getId();

            if (userGender.getCheckedRadioButtonId() == maleId){
                MaleScreen();
            }
            else if(userGender.getCheckedRadioButtonId() == femaleId){
                FemaleScreen();
            }
            else{
                Context context = getApplicationContext();
                CharSequence text = "You need to specify your gender";
                int duration = Toast.LENGTH_SHORT;
                Toast errorToast = Toast.makeText(context, text, duration);
                errorToast.show();
            }
        }
    }
}