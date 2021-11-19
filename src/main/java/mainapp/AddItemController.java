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


    public void cancelButtonPressed() {
        //Clear text fields
        //Return to mainMenu scene
    }


}
