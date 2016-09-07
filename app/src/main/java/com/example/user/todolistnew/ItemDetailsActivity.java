package com.example.user.todolistnew;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by user on 07/09/2016.
 */
public class ItemDetailsActivity extends AppCompatActivity {

    TextView nameText;
    TextView priorityText;
    TextView duedateText;
    Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        nameText = (TextView) findViewById(R.id.viewdetailsname);
        priorityText = (TextView) findViewById(R.id.viewdetailspriority);
        duedateText = (TextView) findViewById(R.id.viewdetailsduedate);

        Intent intent = getIntent();

        String mName = intent.getStringExtra("Item Name");
        String mPriority = intent.getStringExtra("Item Priority");
        String mDueDate = intent.getStringExtra("Item Due Date");

            nameText.setText(mName);
            priorityText.setText(mPriority);
            duedateText.setText(mDueDate);

        editButton = (Button) findViewById(R.id.viewdetailseditbutton);
        editButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ItemDetailsActivity.this, ItemEditActivity.class);

                startActivity(intent);
            }
        });

    }

}

