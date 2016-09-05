package com.example.user.todolistnew;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

/**
 * Created by user on 05/09/2016.
 */
public class MainActivity extends AppCompatActivity {

    List<ToDoItem> todoItems;
    ToDoListAdapter aToDoItemAdapter;
    ListView lvItems;
    EditText etEditText;
    private final int REQUEST_CODE = 20;
    SqlDatabaseHelper sqlDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqlDatabase = new SqlDatabaseHelper(this.getApplicationContext());
        populateArrayItems();
        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(aToDoItemAdapter);
        etEditText = (EditText) findViewById(R.id.etEditText);
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                sqlDatabase.deleteDb(todoItems.get(position).getName());
                todoItems.remove(position);
                aToDoItemAdapter.notifyDataSetChanged();
                return true;
            }
        });
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent secondActivity = new Intent(MainActivity.this, EditItemActivity.class);
                ToDoItem item = todoItems.get(position);
                secondActivity.putExtra(EditItemActivity.EXTRA_NAME, item.getName());
                secondActivity.putExtra(EditItemActivity.EXTRA_PRI, item.getPriority());
                secondActivity.putExtra(EditItemActivity.EXTRA_DUE_DATE, item.getDuedate());
                secondActivity.putExtra(EditItemActivity.EXTRA_ID, item.getId() + "");
                secondActivity.putExtra(EditItemActivity.EXTRA_POSITION, position);
                startActivityForResult(secondActivity, REQUEST_CODE);
            }
        });


    }

}
