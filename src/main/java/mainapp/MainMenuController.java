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

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainMenuController implements Initializable {
    private FileChooser fileChooser = new FileChooser();
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


    public void saveAsTSVFile() {
        fileChooser.setTitle("Save Dialog");

        fileChooser.setInitialFileName("newInventory");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("text file", "*.txt"));

        //Open file explorer
        //Let user choose where to save to do list(s)
        //Save to location
        File tsvFile = fileChooser.showSaveDialog(new Stage());
        if(tsvFile != null) {
            saveSystem(tsvFile, inventory.createTSVFile());
            fileChooser.setInitialDirectory(tsvFile.getParentFile());
        }
    }

    public void saveAsHTMLFile() {
        //Open file explorer

        //get user chosen file

        //saveSystem(user chosen file, createHTMLFile())
    }

    public void saveAsJSONFile() {
        fileChooser.setTitle("Save Dialog");

        fileChooser.setInitialFileName("newInventory");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("json file", "*.json"));

        //Open file explorer
        //get user chosen file
        //saveSystemAsJsonObject(user chosen file, createJsonFile())
        File jsonFile = fileChooser.showSaveDialog(new Stage());
        if(jsonFile != null) {
            saveSystemAsJsonObject(jsonFile, inventory.createJsonFile());
            fileChooser.setInitialDirectory(jsonFile.getParentFile());
        }
    }

    private void saveSystem(File outFile, String listText) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);

        //try
        try (PrintWriter printWriter = new PrintWriter(outFile)) {
            //Write text to File
            printWriter.write(listText);
        }
        //catch(FileNotFoundException)
        catch(FileNotFoundException e) {
            //errorSavingInventoryMessage()
            errorAlert.setTitle("Error saving file");
            errorAlert.setHeaderText("Couldn't save file");
            errorAlert.showAndWait();
        }
    }

    private void saveSystemAsJsonObject(File outFile, String jsonString) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);

        //try
        try (PrintWriter printWriter = new PrintWriter(outFile)){
            printWriter.write(jsonString);
        }
        //catch(FileNotFoundException)
        catch(IOException e) {
            //errorSavingInventoryMessage()
            errorAlert.setTitle("Error saving file");
            errorAlert.setHeaderText("Couldn't save file");
            errorAlert.showAndWait();
        }
    }

    public void loadTSVFileIntoInventory() {
        //Open file explorer
        fileChooser.setTitle("Load Dialog");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("text file", "*.txt"));

        //get user chosen file
        File inventoryFile = fileChooser.showOpenDialog(new Stage());
        if(inventoryFile != null) {
            fileChooser.setInitialDirectory(inventoryFile.getParentFile());
            inventory.clearAllItems();
            if(!inventory.openTSVFile(inventoryFile))
                errorOpeningInventoryMessage();
        }
    }

    public void loadHTMLFileIntoInventory() {
        //Open file explorer
        fileChooser.setTitle("Load Dialog");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("html file", "*.html"));

        //get user chosen file
    }

    public void loadJsonFileIntoInventory() {
        //Open file explorer
        fileChooser.setTitle("Load Dialog");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("json file", "*.json"));

        //get user chosen file
        File inventoryFile = fileChooser.showOpenDialog(new Stage());
        if(inventoryFile != null) {
            fileChooser.setInitialDirectory(inventoryFile.getParentFile());
            inventory.clearAllItems();
            if(!inventory.openJsonFile(inventoryFile))
                errorOpeningInventoryMessage();
        }
    }

    private void errorOpeningInventoryMessage() {
        //Display error message if file could not be opened
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);

        errorAlert.setTitle("Error opening file");
        errorAlert.setHeaderText("Couldn't open file");
        errorAlert.showAndWait();
    }

}
