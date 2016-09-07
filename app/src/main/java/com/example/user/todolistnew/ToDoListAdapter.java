package com.example.user.todolistnew;

import android.app.Activity;
import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by user on 05/09/2016.
 */
public class ToDoListAdapter extends ArrayAdapter<ToDoItem> {

    Context context;

    @Override
    public int getCount() {
        return items.size();
    }

    List<ToDoItem> items;

    public ToDoListAdapter(Context context, int resource, int textViewResourceId, List<ToDoItem> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
        this.items = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ToDoItem item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        TextView itemName = (TextView)convertView.findViewById(R.id.itemName);
        TextView itemPriority = (TextView)convertView.findViewById(R.id.itemPriority);
        TextView itemDueDate = (TextView)convertView.findViewById(R.id.itemDueDate);

        itemName.setText(item.getName());
        itemPriority.setText(item.getPriority());
        itemDueDate.setText(item.getDuedate());

        return convertView;
    }
}
