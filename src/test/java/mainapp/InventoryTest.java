package mainapp;

import org.junit.jupiter.api.Test;

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

        testInventory.addItemToInventory(new Item("newItem", "A-xxx-xxx-xxx", "25.00"));

        Item expected = new Item("newItem", "A-xxx-xxx-xxx", "25.00");

        assertEquals(expected.getName(), testInventory.getCurrInventory().get(0).getName());
        assertEquals(expected.getSerialNumber(), testInventory.getCurrInventory().get(0).getSerialNumber());
        assertEquals(expected.getValue(), testInventory.getCurrInventory().get(0).getValue());
    }


}