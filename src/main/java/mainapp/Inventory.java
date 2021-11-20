/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Zakaria Antifit
 */

package mainapp;

import javafx.collections.FXCollections;
import com.google.gson.JsonObject;
import javafx.collections.ObservableList;

import java.io.File;
import java.text.NumberFormat;

public class Inventory {
    private ObservableList<Item> currInventory = FXCollections.observableArrayList();

    public boolean validateItemName(String name) {
        //if(name is between 2-256 chars)
            //return true
        //else
            //return false
        return name.length() >= 2 && name.length() <= 256 && !name.isBlank();
    }

    public boolean validateItemSerialNum(String serialNum) {
        //if(serialNum == A-XXX-XXX-XXX format && serialNum doesn't already exist && is not empty)
            //return true
        //else
            //return false
        return (serialNum.matches("^[a-zA-Z]-[a-zA-Z0-9]{3}-[a-zA-Z0-9]{3}-[a-zA-Z0-9]{3}$")
                && !serialNumExists(serialNum) && !serialNum.isBlank());
    }

    private boolean serialNumExists(String serialNum) {
        //Check if serial num already exists
        boolean found = false;

        for(Item i: currInventory) {
            if(i.getSerialNumber().matches(serialNum)) {
                found = true;
                break;
            }
        }

        return found;
    }


    public boolean validateItemValue(String value) {
        boolean found = false;

        //if(value is number && 0 or greater && is not empty)
        try {
            double numValue = Double.parseDouble(value);

            if(numValue >= 0 && !value.isBlank())
                //return true
                found = true;
        } catch(NumberFormatException e) {
            found = false;
        }
        //else
            //return false
        return found;
    }

    public String formatValue(String value) {
        double newValue= Double.parseDouble(value);

        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        return formatter.format(newValue);
    }

    public void addItemToInventory(Item newItem) {
        //Add newItem to currInventory
        currInventory.add(newItem);
    }

    public void deleteItemFromInventory(Item itemToDelete) {
        //Delete selected item
        currInventory.remove(itemToDelete);
    }

    public void clearAllItems() {
        //clear all items from currInventory
        currInventory.clear();
    }

    public void editName(String name, Item selectedItem) {
        selectedItem.setName(name);
    }

    public void editSerialNum(String serialNum, Item selectedItem) {
        selectedItem.setSerialNumber(serialNum);
    }

    public void editValue(String value, Item selectedItem) {
        selectedItem.setValue(value);
    }

    public boolean checkIfSerialNumber(String searchText) {
        //if(searchText == A-XXX-XXX-XXX format)
            //return true
        //else
            //return false
        return searchText.matches("^[a-zA-Z]-[a-zA-Z0-9]{3}-[a-zA-Z0-9]{3}-[a-zA-Z0-9]{3}$");
    }

    public Item searchBySerialNumber(String serialNumQuery) {
        Item found = null;

        //if serialNumberQuery exists
            //return item that has the serial number
        //else
            //return null
        for(Item i: currInventory) {
            if(i.getSerialNumber().equalsIgnoreCase(serialNumQuery)) {
                found = i;
                break;
            }
        }

        return found;
    }

    public Item searchByName(String nameQuery) {
        Item found = null;

        //if nameQuery exists
            //return item that has the name
        //else
            //return null
        for(Item i: currInventory) {
            if(i.getName().equalsIgnoreCase(nameQuery)) {
                found = i;
                break;
            }
        }

        return found;
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
        //Use StringBuilder to format text into TSV format
        //return StringBuilder as String
        return "";
    }

    public String createHTMLFile() {
        //Use StringBuilder to format text into HTML format
        //return StringBuilder as String
        return "";
    }

    public JsonObject createJsonFile() {
        //Create JSON object
        //Assign items to it
        //return JSON object
        return null;
    }

    public boolean openTSVFile(File inventoryFile) {
        //try
            //Read in item info
        //catch(IOException | NoSuchElementException | IllegalStateException)
            //return false
        return false;
    }

    public boolean openHTMLFile(File inventoryFile) {
        //try
            //Read in item info
        //catch(IOException | NoSuchElementException | IllegalStateException)
            //return false
        return false;
    }

    public boolean openJsonFile(File inventoryFile) {
        //try
            //Read in item info
        //catch(IOException | NoSuchElementException | IllegalStateException)
            //return false
        return false;
    }


    public ObservableList<Item> getCurrInventory() {
        return currInventory;
    }
}
