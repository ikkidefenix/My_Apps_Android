package com.project.angel.notepad;

/**
 * Created by AngelA on 02-Dec-17.
 */

public class Item {

    private int id;
    private String title;

    public Item(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
