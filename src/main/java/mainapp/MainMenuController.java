/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Zakaria Antifit
 */

package mainapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        //Set items in tableview to inventory's observableList
        inventoryTableView.setItems(inventory.getCurrInventory());

        inventoryTableView.setEditable(true);

        //Make name editable
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        //Make serial # editable
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

            String formattedValue = inventory.formatValue(newValue);

            //Create Item object with info
            Item newItem = new Item(newName, newSerialNum, formattedValue);

            //Add new item object to inventory
            inventory.addItemToInventory(newItem);

            //Clear text fields
            nameTextField.clear();
            serialNumTextField.clear();
            valueTextField.clear();
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
            errorAlert.setHeaderText("The serial number you entered either already exists or is not in the correct format");

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
        Item selectedItem = inventoryTableView.getSelectionModel().getSelectedItem();

        //Call deleteItemFromInventory()
        inventory.deleteItemFromInventory(selectedItem);

    }

    public void clearAllItemsButtonPressed() {
        //Call clearAllItems()
        inventory.clearAllItems();
    }

    public void editNameCellEvent(TableColumn.CellEditEvent<Item, String> editedCell) {
        //Double click to edit
        if(inventory.validateItemName(editedCell.getNewValue())) {
            Item selectedTask = inventoryTableView.getSelectionModel().getSelectedItem();
            inventory.editName(editedCell.getNewValue(), selectedTask);
        } else
            nameInvalidMessage(false);
    }

    public void editSerialNumCellEvent(TableColumn.CellEditEvent<Item, String> editedCell) {
        //Double click to edit
        if(inventory.validateItemSerialNum(editedCell.getNewValue())) {
            Item selectedTask = inventoryTableView.getSelectionModel().getSelectedItem();
            inventory.editSerialNum(editedCell.getNewValue(), selectedTask);
        } else
            serialNumInvalidMessage(false);
    }

    public void editValueCellEvent(TableColumn.CellEditEvent<Item, String> editedCell) {
        //Double click to edit
        if(inventory.validateItemValue(editedCell.getNewValue())) {
            Item selectedTask = inventoryTableView.getSelectionModel().getSelectedItem();
            inventory.editValue(editedCell.getNewValue(), selectedTask);
        } else
            valueInvalidMessage(false);
    }

    public void searchButtonPressed() {
        //searchText = text field
        String searchText = searchTextField.getText();

        //Item foundItem
        Item foundItem;

        //if(checkIfSerialNumber(searchText))
        if(inventory.checkIfSerialNumber(searchText))
            //foundItem = searchBySerialNumber()
            foundItem = inventory.searchBySerialNumber(searchText);
        //else
        else
            //foundItem = searchByName(searchText)
            foundItem = inventory.searchByName(searchText);

        //if(foundItem != null)
        if(foundItem != null) {
            //Display item in tableView
            ObservableList<Item> newItem = FXCollections.observableArrayList();
            newItem.add(foundItem);
            inventoryTableView.refresh();
            inventoryTableView.setItems(newItem);
        }
        //else
        else
            //Display popup with message saying "Could not find item"
            couldNotFindItemMessage();
    }

    private void couldNotFindItemMessage() {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);

            errorAlert.setTitle("Item not found");

            errorAlert.setHeaderText("The item you are searching for does not exist.");

            errorAlert.showAndWait();
    }

    public void cancelButtonPressed() {
        //Clear searchTextField
        searchTextField.clear();

        //Reload Inventory into table
        inventoryTableView.refresh();
        inventoryTableView.setItems(inventory.getCurrInventory());
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
