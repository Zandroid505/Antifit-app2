/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Zakaria Antifit
 */

package mainapp;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    @Test
    void testIfInventoryCanHold1024Items() {
        Inventory testInventory = new Inventory();

        for(int i = 0; i < 1024; i++) {
            Item testItem = new Item("itemName", "A-" + i + "xx-xxx-xxx", "5");
            testInventory.addItemToInventory(testItem);
        }

        int actual = 0;

        for(Item i: testInventory.getCurrInventory()) {
            actual++;
        }

        assertEquals(1024, actual);
    }

    @Test
    void checkIfNonNumberValueIsRejected() {
        Inventory testInventory = new Inventory();

        assertFalse(testInventory.validateItemValue("abdfl"));
    }

    @Test
    void checkIfValueLessThanZeroIsRejected() {
        Inventory testInventory = new Inventory();

        assertFalse(testInventory.validateItemValue("-1"));
    }

    @Test
    void checkIfValidValueIsAccepted() {
        Inventory testInventory = new Inventory();

        assertTrue(testInventory.validateItemValue("25.36"));
    }

    @Test
    void checkIfInvalidSerialNumbersAreRejected() {
        Inventory testInventory = new Inventory();

        //Number instead of letter as first character
        assertFalse(testInventory.validateItemSerialNum("1-xxx-xxx-xxx"));

        //No hyphens separating chars
        assertFalse(testInventory.validateItemSerialNum("axxxxxxxx2"));

        //Special characters included
        assertFalse(testInventory.validateItemSerialNum("a-xxx-x@x-xx3"));
    }

    @Test
    void checkIfItemsWithTheSameSerialNumbersAreRejected() {
        Inventory testInventory = new Inventory();

        testInventory.addItemToInventory(new Item("newItem", "1-xxx-xxx-xxx", "25.00"));

        //Test to see if item exists already
        assertFalse(testInventory.validateItemSerialNum("1-xxx-xxx-xxx"));
    }

    @Test
    void checkIfNameWithFewerThanMinCharsIsRejected() {
        Inventory testInventory = new Inventory();

        assertFalse(testInventory.validateItemName("0"));
    }

    @Test
    void checkIfNameWithMoreThanMaxCharsIsRejected() {
        Inventory testInventory = new Inventory();

        assertFalse(testInventory.validateItemName("a".repeat(257)));
    }

    @Test
    void checkIfNameWithMaxCharsIsAccepted() {
        Inventory testInventory = new Inventory();

        assertTrue(testInventory.validateItemName("a".repeat(256)));
    }

    @Test
    void checkIfNameWithMinCharsIsAccepted() {
        Inventory testInventory = new Inventory();

        assertTrue(testInventory.validateItemName("a".repeat(2)));
    }

    @Test
    void checkIfNameWithWhiteSpaceIsRejected() {
        Inventory testInventory = new Inventory();

        assertFalse(testInventory.validateItemName("     "));
    }

    @Test
    void checkIfBlankNameIsRejected() {
        Inventory testInventory = new Inventory();

        assertFalse(testInventory.validateItemName(""));
    }

    @Test
    void checkIfNewItemIsAddedToInventory() {
        Inventory testInventory = new Inventory();

        testInventory.addItemToInventory(new Item("newItem", "C-3T5-PG9-3DC", "25.00"));

        Item expected = new Item("newItem", "C-3T5-PG9-3DC", "25.00");

        assertEquals(expected.getName(), testInventory.getCurrInventory().get(0).getName());
        assertEquals(expected.getSerialNumber(), testInventory.getCurrInventory().get(0).getSerialNumber());
        assertEquals(expected.getValue(), testInventory.getCurrInventory().get(0).getValue());
    }

    @Test
    void checkIfItemIsDeletedFromInventory() {
        Inventory testInventory = new Inventory();
        Item unexpectedData = new Item("newItem", "C-3T5-PG9-3DC", "45.87");
        List<Item> unexpected = new ArrayList<>();

        //Array list that contains data that should be deleted in test
        unexpected.add(unexpectedData);

        testInventory.addItemToInventory(new Item("newItem", "C-3T5-PG9-3DC", "45.87"));

        //Delete Item from testInventory
        testInventory.deleteItemFromInventory(testInventory.getCurrInventory().get(0));

        //Check to see if item does not contain the dummy data previously added
        assertNotEquals(unexpected, testInventory.getCurrInventory());
    }

    @Test
    void checkIfAllItemsAreClearedFromInventory() {
        Inventory testInventory = new Inventory();

        testInventory.addItemToInventory(new Item("newItem1", "C-3T5-GY9-3DC", "45.87"));
        testInventory.addItemToInventory(new Item("newItem2", "D-3O5-PG9-3DC", "29.39"));
        testInventory.addItemToInventory(new Item("newItem3", "Z-3T5-PU9-3DC", "100.99"));
        testInventory.addItemToInventory(new Item("newItem4", "K-3O5-PG9-3AH", "112.13"));

        testInventory.clearAllItems();

        assertTrue(testInventory.getCurrInventory().isEmpty());
    }

    @Test
    void checkIfNameCanBeEdited() {
        Inventory testInventory = new Inventory();

        //Add dummy data
        testInventory.addItemToInventory(new Item("Item1", "C-3T5-GY9-3DC", "45.87"));

        Item actual = testInventory.getCurrInventory().get(0);

        //Call editName() with new name
        testInventory.editName("New Item name", actual);

        //check if name was changed
        assertEquals("New Item name", actual.getName());
    }

    @Test
    void checkIfSerialNumCanBeEdited() {
        Inventory testInventory = new Inventory();

        //Add dummy data
        testInventory.addItemToInventory(new Item("Item1", "C-3T5-GY9-3DC", "45.87"));

        Item actual = testInventory.getCurrInventory().get(0);

        //Call editSerialNum() with new serial #
        testInventory.editSerialNum("A-123-ABC-789", actual);

        //check if serial # was changed
        assertEquals("A-123-ABC-789", actual.getSerialNumber());
    }

    @Test
    void checkIfValueCanBeEdited() {
        Inventory testInventory = new Inventory();

        //Add dummy data
        testInventory.addItemToInventory(new Item("Item1", "C-3T5-GY9-3DC", "45.87"));

        Item actual = testInventory.getCurrInventory().get(0);

        //Call editDeadline() with new title
        testInventory.editValue("25.00", actual);

        //check if deadline was changed
        assertEquals("25.00", actual.getValue());
    }


}