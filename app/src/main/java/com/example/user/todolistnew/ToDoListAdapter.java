package com.example.user.todolistnew;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by user on 05/09/2016.
 */

//CUSTOM ADAPTER ALLOWS YOU TO CUSTOMIZE YOUR LISTVIEW TO INCLUDE MULTIPLE ITEMS IN ROW
public class ToDoListAdapter extends ArrayAdapter<ToDoItem> {

    Context context;
    List<ToDoItem> items;

    public ToDoListAdapter(Context context, int resource, int textViewResourceId, List<ToDoItem> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
        this.items = objects;
    }


    // GETS THE APPROPRIATE VIEW FOR LISTVIEW ROW FROM LAYOUT (ITEM.XML)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

            ToDoItem item = getItem(position);
            TextView itemName = (TextView) convertView.findViewById(R.id.itemName);
            CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox1);


            itemName.setText(item.getName());
            cb.setChecked(false);

            return convertView;
        }

}



