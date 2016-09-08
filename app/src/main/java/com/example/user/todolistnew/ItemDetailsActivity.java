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
    String mName;
    String mPriority;
    String mDueDate;
    String mID;
    String mPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);


//        SETS VARIABLES TO TEXT VIEW FIELDS THAT CAN BE CALLED IN NEXT METHOD
        nameText = (TextView) findViewById(R.id.viewdetailsname);
        priorityText = (TextView) findViewById(R.id.viewdetailspriority);
        duedateText = (TextView) findViewById(R.id.viewdetailsduedate);


//        RECEIVES INTENT FROM MAIN ACTIVITY AND SETS TEXTVIEW FIELDS TO RELEVANT VALUE
        Intent intent = getIntent();
        mName = intent.getStringExtra("Item Name");
        mPriority = intent.getStringExtra("Item Priority");
        mDueDate = intent.getStringExtra("Item Due Date");
        mID = intent.getStringExtra("Item ID");
        mPosition = intent.getStringExtra("Item position");

            nameText.setText(mName);
            priorityText.setText(mPriority);
            duedateText.setText(mDueDate);


//      CREATED A NEW INTENT TO SEND TO EDIT ITEM - IS THIS NECESSARY?
        editButton = (Button) findViewById(R.id.viewdetailseditbutton);
        editButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ItemDetailsActivity.this, ItemEditActivity.class);
                intent.putExtra("Item Name", mName);
                intent.putExtra("Item Priority", mPriority);
                intent.putExtra("Item Due Date", mDueDate);
                intent.putExtra("Item ID", mID);
                intent.putExtra("Item position", mPosition);
                startActivity(intent);
            }
        });

    }

}

