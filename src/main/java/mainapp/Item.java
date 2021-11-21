/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Zakaria Antifit
 */

package mainapp;

import javafx.beans.property.SimpleStringProperty;

public class Item {
    private SimpleStringProperty name;
    private SimpleStringProperty serialNumber;
    private SimpleStringProperty value;

    public Item(String name, String serialNumber, String value) {
        //Initialize class fields to parameters
        this.name = new SimpleStringProperty(name);
        this.serialNumber = new SimpleStringProperty(serialNumber);
        this.value = new SimpleStringProperty(value);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSerialNumber() {
        return serialNumber.get();
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber.set(serialNumber);
    }

    public String getValue() {
        return value.get();
    }

    public void setValue(String value) {
        this.value.set(value);
    }
}
