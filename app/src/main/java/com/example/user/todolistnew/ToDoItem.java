package com.example.user.todolistnew;

/**
 * Created by user on 05/09/2016.
 */
public class ToDoItem {

    private long id;  // 'long' value type used when longer than int is needed
    private String name;
    private String priority;
    private String duedate;


    // Overload the constructors (create three different constructors that take different arguments)
    public ToDoItem() {

    }


    public ToDoItem(long id, String name, String priority, String duedate) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.duedate = duedate;
    }


    public ToDoItem(String name, String priority, String duedate){
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
