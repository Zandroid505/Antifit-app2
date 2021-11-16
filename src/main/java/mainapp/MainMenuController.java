/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Zakaria Antifit
 */

package mainapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import com.google.gson.JsonObject;

public class MainMenuController implements Initializable {
    private Inventory inventory = new Inventory();

    //TableView
    @FXML private TableView<Item> inventoryTableView;
    @FXML private TableColumn<Item, String> nameColumn;
    @FXML private TableColumn<Item, String> serialNumColumn;
    @FXML private TableColumn<Item, Double> valueColumn;

    @FXML private TextField searchTextField;
    @FXML private ChoiceBox<String> sortBy;
    @FXML private ChoiceBox<String> directionOfSort;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Set initial directory

        //Set cell values for tableView

        //Set items in tableview to inventory's observableList

        //sortByStr = "None", "Name", "Serial #", "Value"
        //sortBy(sortByStr)

        //directionOfSortStr = "n/a", "Ascending", "Descending"
        //directionOfSort(directionOfSortStr)
    }

    public void addItemButtonPressed() {
        //Open addItem scene
    }

    public void deleteItemButtonPressed() {
        //get selected task

        //Call deleteItemFromInventory()
    }

    public void clearAllItemsButtonPressed() {
        //Call clearAllItems()
    }

    public void editItemMouseClick() {
        //if(mouseClickCount == 2)
            //Open editItem scene
    }

    public void searchButtonPressed() {
        //searchText = text field
        //Item foundItem

        //if(checkIfSerialNumber(searchText))
            //foundItem = searchBySerialNumber()
        //else
            //foundItem = searchByName(searchText)

        //if(foundItem != null)
            //Popup with Item info
        //else
            //Display popup with message saying "Could not find item"
    }

    public void cancelButtonPressed() {
        //Clear searchTextField
        //Reload Inventory into table
    }

    public void sortInventory() {
        //get selected value from sortBy choiceBox
        //get selected value from directionOfSort choiceBox
        //if(directionOfSort choiceBox doesn't have default value)
            //set to ascending

        //if(directionOfSort is ascending)
            //direction = 0
        //else
            //direction = 1

        //if(sortBy == "Name")
            //sortByName(direction)
        //else if(sortBy == "Serial #")
            //sortBySerialNumber(direction)
        //else if(sortBy == "Value")
            //sortByValue(direction)
        //else
            //Do nothing

        //Clear tableView
        //Redisplay currInventory in tableView
    }

    public void saveAsTSVFile() {
        //Open file explorer

        //get user chosen file

        //saveSystem(user chosen file, createTSVFile())
    }

    public void saveAsHTMLFile() {
        //Open file explorer

        //get user chosen file

        //saveSystem(user chosen file, createHTMLFile())
    }

    public void saveAsJSONFile() {
        //Open file explorer

        //get user chosen file

        //saveSystemAsJsonObject(user chosen file, createJsonFile())
    }

    private void saveSystem(File outFile, String listText) {

        //try
            //Write text to File
        //catch(FileNotFoundException)
            //errorSavingInventoryMessage()
    }

    private void saveSystemAsJsonObject(File outFile, JsonObject jsonObject) {
        //try
            //Write json object to File
        //catch(FileNotFoundException)
            //errorSavingInventoryMessage()
    }

    private void errorSavingInventoryMessage() {
        //Display error message if file could not be saved
    }

    public void loadInventoryToTable() {
        //Open file explorer

        //get user chosen file

        //if(.txt)
            //openTSVFile(user chosen file)
        //if(.html)
            //openHTMLFile(user chosen file)
        //if(.json)
            //openJsonFile(user chosen file)


    }

    private void errorOpeningInventoryMessage() {
        //Display error message if file could not be opened
    }

}
