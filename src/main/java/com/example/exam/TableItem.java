package com.example.exam;

import javafx.scene.control.TableView;

public class TableItem {

    public String name;
    public String value;

    public TableItem(String n, String v)
    {
        name = n;
        value = v;
    }

    public String getName(){
        return name;
    }

    public String getValue(){
        return value;
    }


}
