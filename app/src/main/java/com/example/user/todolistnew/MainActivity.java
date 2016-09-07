package com.example.user.todolistnew;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
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
    private final int REQUEST_CODE = 20;
    SqlDatabaseHelper sqlDatabase;
    Context context = this;
    EditText submitText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqlDatabase = new SqlDatabaseHelper(this);
        populateArrayItems();                                   // DEFINED AT BOTTOM OF THIS CLASS
        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(myToDoItemAdapter);
        submitText = (EditText) findViewById(R.id.name);

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
                intent.putExtra("Item ID", selectedItem.getId() + "");
                intent.putExtra("Item position", position);
                startActivity(intent);
            }
        });
    }






    // CLICKING ADD BUTTON MAKES DIALOG APPEAR TO ENTER NEW ENTRY
    public void addItem(View view) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.add_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText edtName = (EditText) dialogView.findViewById(R.id.name);
//        Spinner spinimage = (Spinner) dialogView.findViewById(R.id.spinner);
        final EditText edtPriority = (EditText) dialogView.findViewById(R.id.priority);
        final EditText edtDueDate = (EditText) dialogView.findViewById(R.id.duedate);

        dialogBuilder.setTitle("Add Item To List");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String itemName = edtName.getText().toString();
                String itemPriority = edtPriority.getText().toString();
                String itemDuedate = edtDueDate.getText().toString();


                // CREATE NEW INSTANCE OF TODOITEM AND THEN SET VALUES USING OUTPUT OF DIALOG
                ToDoItem item = new ToDoItem();
                item.setName(itemName);
                item.setPriority(itemPriority);
                item.setDuedate(itemDuedate);
                myToDoItemAdapter.add(item);        // ADD NEW TODOITEM TO ADAPTER
                writeItems(item);                   //SAVE INTO DATABASE
                Toast.makeText(getApplicationContext(), "Item Added!", Toast.LENGTH_LONG).show();
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }


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



    //  UPDATE ENTRY
    protected void onActivityResult(Intent data) {
//        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            int pos = data.getExtras().getInt("Item position");
            ToDoItem item = new ToDoItem();
            item.setName(data.getExtras().getString("Item Name"));
            item.setId(Integer.parseInt(data.getExtras().getString("Item ID")));
            item.setPriority(data.getExtras().getString("Item Priority"));
            item.setDuedate(data.getExtras().getString("Item Due Date"));
            todoItems.set(pos,item);
            myToDoItemAdapter.notifyDataSetChanged();
            updateItem(item);
            writeItems(item);
    }




}
