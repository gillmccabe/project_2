package com.example.user.todolistnew;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by user on 05/09/2016.
 */
public class ItemEditActivity extends AppCompatActivity {

    EditText editText;
    EditText newPriority;
    EditText newDuedate;

    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_PRI = "priority";
    public static final String EXTRA_DUE_DATE = "due_date";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_ID = "extra_id";


    String mName="";
    String mPriority="";
    String mDate = "";
    String mId = "";
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

//        Bundle intent = getIntent().getExtras();
//        if (intent.containsKey(EXTRA_NAME))
//            mName = intent.getString(EXTRA_NAME);
//        if (intent.containsKey(EXTRA_ID))
//            mId = intent.getString(EXTRA_ID);
//        if (intent.containsKey(EXTRA_PRI))
//            mPriority = intent.getString(EXTRA_PRI);
//        if (intent.containsKey(EXTRA_DUE_DATE))
//            mDate = intent.getString(EXTRA_DUE_DATE);
//        if (intent.containsKey(EXTRA_POSITION))
//            position = intent.getInt(EXTRA_POSITION);
//
//        editText = (EditText) findViewById(R.id.etEditText);
//        editText.setText(mName);
//        editText.setSelection(mName.length());
//        newPriority = (EditText) findViewById(R.id.etPriority);
//        newPriority.setText(mPriority);
//        newPriority.setSelection(mPriority.length());
//        editText = (EditText) findViewById(R.id.dueDate);
//        editText.setText(mDate);
//        editText.setSelection(mDate.length());
//    }
//
//
//    public void onSaveBtn(View view) {
//
//        Intent data = new Intent();
//        data.putExtra(ItemEditActivity.EXTRA_NAME,editText.getText().toString());
//        data.putExtra(ItemEditActivity.EXTRA_PRI,newPriority.getText().toString());
//        data.putExtra(ItemEditActivity.EXTRA_DUE_DATE,newDuedate.getText().toString());
//        data.putExtra(ItemEditActivity.EXTRA_POSITION, position);
//        data.putExtra(ItemEditActivity.EXTRA_ID, mId);
//        setResult(RESULT_OK, data); // set result code and bundle data for response
//        finish();
    }


}
