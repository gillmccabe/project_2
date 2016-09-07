package com.example.user.todolistnew;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 05/09/2016.
 */
public class MainActivity extends AppCompatActivity {

    List<ToDoItem> todoItems;
    ToDoListAdapter myToDoItemAdapter;
    ListView lvItems;
    EditText submitText;
    private final int REQUEST_CODE = 20;
    SqlDatabaseHelper sqlDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqlDatabase = new SqlDatabaseHelper(this);
        populateArrayItems();                                   // DEFINED AT BOTTOM OF THIS CLASS
        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(myToDoItemAdapter);
        submitText = (EditText) findViewById(R.id.etEditText);

        //ON ITEM LONG CLICK WITHIN ON CREATE METHOD
        //THIS METHOD ALLOWS YOU TO LONG CLICK ON AN ITEM IN LIST AND DELETE IT FROM DB
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                sqlDatabase.deleteDb(todoItems.get(position).getName());
                todoItems.remove(position);
                myToDoItemAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Item Deleted!", Toast.LENGTH_LONG).show();
                return true;
            }
        });


        //ON ITEM CLICK WITHIN ON CREATE METHOD
        // THIS METHOD ALLOWS YOU TO SHORT CLICK ON ITEM AND GO TO ITEM DETAILS
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToDoItem selectedItem = todoItems.get(position);

                Intent intent = new Intent(MainActivity.this, ItemDetailsActivity.class);
                intent.putExtra("Item Name", selectedItem.getName());
                intent.putExtra("Item Priority", selectedItem.getPriority());
                intent.putExtra("Item Due Date", selectedItem.getDuedate());
                intent.putExtra("Item ID", selectedItem.getId()+"");
                intent.putExtra("Item position", position);
                startActivity(intent);
            }
        });

    }  // THIS BRACKET CLOSES ON CREATE METHOD



    // CREATE NEW ENTRY IN DATABASE
    private void writeItems(ToDoItem temp){
        long id = sqlDatabase.addDb(temp);
        temp.setId(id);
        myToDoItemAdapter.notifyDataSetChanged();
    }

    // READ ALL ITEMS IN DATABASE
    private void readItems(){
        if(sqlDatabase.getAllItems() != null){
            List<ToDoItem> dbItems = sqlDatabase.getAllItems();
            todoItems.clear();
            todoItems.addAll(dbItems);
        }
    }

    // CALLS DATABASE UPDATE METHOD TO ALLOW YOU TO UPDATE ITEM
    private void updateItem(ToDoItem item) {
        sqlDatabase.updateItem(item);
    }


    // POPULATES A NEW ARRAY LIST OF TODOITEM WITH ALL ITEMS IN DATABASE
    // USES ADAPTER TO SET APPROPRIATE LAYOUT
    public void populateArrayItems(){
        todoItems = new ArrayList<ToDoItem>();
        readItems();
        myToDoItemAdapter = new ToDoListAdapter(this, R.layout.item, R.id.itemName, todoItems);
    }


    // HANDLES WHAT IS PRINTED TO THE LIST VIEW WHEN USER CLICKS ADD BUTTON
    // IF NOTHING IS TYPED IN TEXTEDIT FIELD THEN USER RECEIVES TOAST ASKING THEM TO INPUT AN ITEM
    public void onAdd(View view) {
        String editText = submitText.getText().toString();
        if (editText == null || editText.isEmpty()) {
            Toast.makeText(this, "You must enter an item", Toast.LENGTH_SHORT).show();
            return;
        }
        ToDoItem item = new ToDoItem();
        item.setName(editText);
        item.setDuedate("-----------");


        myToDoItemAdapter.add(item); // ADD NEW TODOITEM TO ADAPTER
        submitText.setText(""); // SET TEXT ENTERED INTO EDITTEXT FIELD
        writeItems(item); //SAVE INTO DATABASE
    }


//    //  UPDATE ENTRY
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
//            int pos = data.getExtras().getInt(ItemEditActivity.EXTRA_POSITION);
//            ToDoItem item = new ToDoItem();
//            item.setName(data.getExtras().getString(ItemEditActivity.EXTRA_NAME));
//            item.setId(Integer.parseInt(data.getExtras().getString(ItemEditActivity.EXTRA_ID)));
//            item.setPriority(data.getExtras().getString(ItemEditActivity.EXTRA_PRI));
//            item.setDuedate(data.getExtras().getString(ItemEditActivity.EXTRA_DUE_DATE));
//            todoItems.set(pos,item);
//            myToDoItemAdapter.notifyDataSetChanged();
//            updateItem(item);
//        }
//    }




}
