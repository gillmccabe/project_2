package com.example.user.todolistnew;

import android.app.Activity;
import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ToDoItemHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.todo_list_item, null, false);

            holder = new ToDoItemHolder();
            holder.name = (TextView)row.findViewById(R.id.textViewName);
            holder.priority = (TextView)row.findViewById(R.id.textViewPriority);
            holder.duedate = (TextView)row.findViewById(R.id.textViewDueDate);


        }
        else
        {
            holder = (ToDoItemHolder)row.getTag();
        }

        ToDoItem item = items.get(position);
        holder.name.setText(item.getName());
        holder.priority.setText(item.getPriority());

        Long time = item.getDuedate();
        int flags = DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_MONTH |
                DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_YEAR;
        holder.duedate.setText(DateUtils.formatDateTime(context, time, flags));
        row.setTag(holder);
        return row;
    }



    static class ToDoItemHolder
    {
        TextView name;
        TextView priority;
        TextView duedate;

    }
}
