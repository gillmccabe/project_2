package com.example.user.todolistnew;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by user on 05/09/2016.
 */
public class ItemEditActivity extends AppCompatActivity {

    EditText editText;
    EditText newPriority;
    EditText newDuedate;
    String mName="";
    String mPriority="";
    String mDate = "";
    String mId = "";
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        Bundle intent = getIntent().getExtras();
        if (intent.containsKey("Item Name"))
            mName = intent.getString("Item Name");
        if (intent.containsKey("Item ID"))
            mId = intent.getString("Item ID");
        if (intent.containsKey("Item Priority"))
            mPriority = intent.getString("Item Priority");
        if (intent.containsKey("Item Due Date"))
            mDate = intent.getString("Item Due Date");
        if (intent.containsKey("Item position"))
            position = intent.getInt("Item position");


        editText = (EditText) findViewById(R.id.etEditText);
        editText.setText(mName);
        editText.setSelection(mName.length());
        newPriority = (EditText) findViewById(R.id.etPriority);
        newPriority.setText(mPriority);
        newPriority.setSelection(mPriority.length());
        newDuedate = (EditText) findViewById(R.id.dueDate);
        newDuedate.setText(mDate);
        newDuedate.setSelection(mDate.length());
    }


//    SAVES ANY EDITED VALUES TO A NEW TODOITEM AND PASSES IT INTO THE UPDATEITEM METHOD TO UPDATE IN DB
    public void onSaveBtn(View view) {

        Intent data = new Intent(this, ItemDetailsActivity.class);
        data.putExtra("Item Name", editText.getText().toString());
        data.putExtra("Item Priority",newPriority.getText().toString());
        data.putExtra("Item Due Date",newDuedate.getText().toString());
        data.putExtra("Item position", position);
        data.putExtra("Item ID", mId);

        ToDoItem toDoItem = new ToDoItem(Long.parseLong(mId), editText.getText().toString(), newPriority.getText().toString(), newDuedate.getText().toString());
        SqlDatabaseHelper sql = new SqlDatabaseHelper(this);
        sql.updateItem(toDoItem);
        startActivity(data);
        Toast.makeText(getApplicationContext(), "Item Updated!", Toast.LENGTH_LONG).show();
    }


}
