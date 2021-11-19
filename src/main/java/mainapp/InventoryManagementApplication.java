/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Zakaria Antifit
 */

package mainapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InventoryManagementApplication extends Application {
    private SceneManager sceneManager = new SceneManager();

    @Override
    public void start(Stage primaryStage) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
//
//        primaryStage.setTitle("Inventory Manager");
//        primaryStage.setScene(new Scene(root));
//
//        primaryStage.show();

        //Initialize scenes
        sceneManager.load();

        Stage stage = new Stage();
        stage.setTitle("Inventory Manager");
        stage.setScene(sceneManager.getScene("MainMenu"));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
