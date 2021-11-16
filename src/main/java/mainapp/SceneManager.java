/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Zakaria Antifit
 */

package mainapp;

import com.sun.tools.javac.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private Map<String, Scene> scenes = new HashMap<>();

    public void load() {
        MainMenuController mainMenuController = new MainMenuController();
        AddItemController addItemController = new AddItemController();
        EditItemController editItemController = new EditItemController();

        Parent root;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        loader.setController(mainMenuController);

        try {
            root = loader.load();
            scenes.put("MainMenu", new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }

        loader = new FXMLLoader(getClass().getResource("AddItem.fxml"));
        loader.setController(addItemController);

        try {
            root = loader.load();
            scenes.put("AddItem", new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Scene getScene(String name) {
        return scenes.get(name);
    }
}
