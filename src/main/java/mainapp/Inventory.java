/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Zakaria Antifit
 */

package mainapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

public class Inventory {
    private ObservableList<Item> currInventory = FXCollections.observableArrayList();

    public boolean validateItemName(String name) {
        //if(name is between 2-256 chars)
            //return true
        //else
            //return false
        return false;
    }

    public boolean validateItemSerialNum(String serialNum) {
        //if(serialNum == A-XXX-XXX-XXX format && serialNum doesn't already exist && is not empty)
            //return true
        //else
            //return false
        return false;
    }

    public boolean validateItemValue(String value) {
        //if(value is number && 0 or greater && is not empty)
            //return true
        //else
            //return false
        return false;
    }

    public void addItemToInventory(Item newItem) {
        //Add newItem to currInventory
    }

    public void deleteItemFromInventory(Item itemToDelete) {
        //Delete selected item
    }

    public void clearAllItems() {
        //clear all items from currInventory
    }

    public boolean checkIfSerialNumber(String searchText) {
        //if(searchText == A-XXX-XXX-XXX format)
            //return true
        //else
            //return false
        return false;
    }

    public Item searchBySerialNumber(String serialNumberQuery) {
        //if serialNumberQuery exists
            //return item that has the serial number
        //else
            //return null
        return null;
    }

    public Item searchByName(String nameQuery) {
        //if nameQuery exists
            //return item that has the name
        //else
            //return null
        return null;
    }

    public void sortByName(int direction) {
        //Sort currInventory by Name
    }

    public void sortBySerialNumber(int direction) {
        //Sort currInventory by Serial #
    }

    public void sortByValue(int direction) {
        //Sort currInventory by Value
    }

    public String createTSVFile() {

    }


    public ObservableList<Item> getCurrInventory() {
        return currInventory;
    }
}
