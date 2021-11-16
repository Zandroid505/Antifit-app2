/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Zakaria Antifit
 */

package mainapp;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddItemController {
    private Inventory inventory;

    @FXML private TextField nameTextField;
    @FXML private TextField serialNumTextField;
    @FXML private TextField valueTextField;

    public void initInventory(Inventory inventory) {
        //Assign parameter to inventory
    }

    public void addButtonPressed() {
        //get name from textField
        //get serialNumber from textField
        //get value from textField

        //if(nameInvalidMessage(validateItemName()) && serialNumInvalidMessage(validateItemSerialNum())
        //              && valueInvalidMessage(validateItemValue()))
            //Parse String value to Double
            //Create Item object with info
            //Add new item object to inventory
    }

    public void cancelButtonPressed() {
        //Clear text fields
        //Return to mainMenu scene
    }

    private void nameInvalidMessage(boolean nameValid) {
        //if(nameValid == false)
            //new Alert

            //Title: "Inputted name is not valid"
            //Header: "The name you inputted is in invalid"
            //Content: "A name must be between 2-256 chars"
            //showAndWait
    }

    private void serialNumInvalidMessage(boolean serialNumValid) {
        //if(serialNumValid == false)
            //new Alert

            //Title: "Inputted serial number is not valid"
            //Header: "The serial number you entered either exists already or is not in the correct format"
            //Content: "A serial number must not already exist in an inventory and the format must be A-XXX-XXX-XXX"
            //showAndWait
    }

    private void valueInvalidMessage(boolean valueValid) {
        //if(serialNumValid == false)
            //new Alert

            //Title: "Inputted value is not valid"
            //Header: "The value you have entered is either not a number or not greater than or equal to 0"
            //Content: "A value must be a number greater than or equal to 0"
            //showAndWait
    }

}
