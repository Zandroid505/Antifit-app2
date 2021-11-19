/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Zakaria Antifit
 */

package mainapp;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.io.File;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import com.google.gson.JsonObject;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class MainMenuController implements Initializable {
    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
    private Inventory inventory = new Inventory();

    //TableView
    @FXML private TableView<Item> inventoryTableView;
    @FXML private TableColumn<Item, String> nameColumn;
    @FXML private TableColumn<Item, String> serialNumColumn;
    @FXML private TableColumn<Item, String> valueColumn;

    @FXML private TextField nameTextField;
    @FXML private TextField serialNumTextField;
    @FXML private TextField valueTextField;

    @FXML private TextField searchTextField;
    @FXML private ChoiceBox<String> sortBy = new ChoiceBox<>();
    @FXML private ChoiceBox<String> directionOfSort = new ChoiceBox<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Set cell values for tableView
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        serialNumColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        //valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        //Set items in tableview to inventory's observableList
        inventoryTableView.setItems(inventory.getCurrInventory());

        inventoryTableView.setEditable(true);

        //Make description editable
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        //Make deadline editable
        serialNumColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        //Make value editable
        valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        //sortByStr = "None", "Name", "Serial #", "Value"
        //sortBy(sortByStr)
        sortBy.setItems(FXCollections.observableArrayList("None", "Name", "Serial #", "Value"));

        //directionOfSortStr = "n/a", "Ascending", "Descending"
        //directionOfSort(directionOfSortStr)
        directionOfSort.setItems(FXCollections.observableArrayList("n/a", "Ascending", "Descending"));
    }

    public void addItemButtonPressed() {
        //get name from textField
        String newName = nameTextField.getText();

        //get serialNumber from textField
        String newSerialNum = serialNumTextField.getText();

        //get value from textField
        String newValue = valueTextField.getText();

        //if(nameInvalidMessage(validateItemName()) && serialNumInvalidMessage(validateItemSerialNum())
        //              && valueInvalidMessage(validateItemValue()))
        if(nameInvalidMessage(inventory.validateItemName(newName)) && serialNumInvalidMessage(inventory.validateItemSerialNum(newSerialNum))
                && valueInvalidMessage(inventory.validateItemValue(newValue))) {



            //Create Item object with info
            Item newItem = new Item(newName, newSerialNum, newValue);

            //Add new item object to inventory
            inventory.addItemToInventory(newItem);
        }
    }

    private boolean nameInvalidMessage(boolean nameValid) {
        //if(nameValid == false)
        if(!nameValid) {
            //new Alert
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);

            //Title: "Inputted name is not valid"
            errorAlert.setTitle("Inputted name is not valid");

            //Header: "The name you inputted is in invalid"
            errorAlert.setHeaderText("The name you inputted is in invalid");

            //Content: "A name must be between 2-256 chars"
            errorAlert.setContentText("A name must be between 2-256 chars");

            //showAndWait
            errorAlert.showAndWait();
            return false;
        } else
            return true;
    }

    private boolean serialNumInvalidMessage(boolean serialNumValid) {
        //if(serialNumValid == false)
        if(!serialNumValid) {
            //new Alert
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);

            //Title: "Inputted serial number is not valid"
            errorAlert.setTitle("Inputted serial number is not valid");

            //Header: "The serial number you entered either exists already or is not in the correct format"
            errorAlert.setHeaderText("The serial number you entered either exists already or is not in the correct format");

            //Content: "A serial number must not already exist in an inventory and the format must be A-XXX-XXX-XXX"
            errorAlert.setContentText("A serial number must not already exist in an inventory and the format must be A-XXX-XXX-XXX");

            //showAndWait
            errorAlert.showAndWait();
            return false;
        } else
            return true;
    }

    private boolean valueInvalidMessage(boolean valueValid) {
        //if(serialNumValid == false)
        if(!valueValid) {
            //new Alert
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);

            //Title: "Inputted value is not valid"
            errorAlert.setTitle("Inputted value is not valid");

            //Header: "The value you have entered is either not a number or not greater than or equal to 0"
            errorAlert.setHeaderText("The value you have entered is either not a number or not greater than or equal to 0");

            //Content: "A value must be a number greater than or equal to 0"
            errorAlert.setContentText("A value must be a number greater than or equal to 0");

            //showAndWait
            errorAlert.showAndWait();
            return false;
        } else
            return true;
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
