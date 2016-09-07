package com.example.user.todolistnew;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by user on 07/09/2016.
 */
public class HomePageActivity extends AppCompatActivity {

    Button mHomeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        mHomeButton = (Button) findViewById(R.id.homeButton);
        mHomeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomePageActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });

    }
}
