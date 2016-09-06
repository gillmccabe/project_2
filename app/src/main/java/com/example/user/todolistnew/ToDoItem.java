package com.example.user.todolistnew;

/**
 * Created by user on 05/09/2016.
 */
public class ToDoItem {

    private long id;  // 'long' value type used when longer than int is needed
    private String name;
    private String priority;
    private String duedate;


//////    EMPTY CONTRUCTOR
    public ToDoItem() {

    }
//
//
//    CONSTRUCTOR
    public ToDoItem(long id, String name, String priority, String duedate) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.duedate = duedate;
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }


}
