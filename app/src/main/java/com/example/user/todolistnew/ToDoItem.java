package com.example.user.todolistnew;

/**
 * Created by user on 05/09/2016.
 */
public class ToDoItem {

    private long id;  // 'long' value type used when longer than int is needed
    private String name;
    private String priority;
    private long duedate;



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

    public long getDuedate() {
        return duedate;
    }

    public void setDuedate(long duedate) {
        this.duedate = duedate;
    }


}
