package com.example.demo.controller;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the start menu for the game, providing options to start the game or exit.
 */
public class StartMenu {

    /**
     * The width of the menu screen in pixels.
     */
    private static final int SCREEN_WIDTH = 1300;

    /**
     * The height of the menu screen in pixels.
     */
    private static final int SCREEN_HEIGHT = 750;

    /**
     * Sets up and displays the start menu on the provided stage.
     *
     * @param stage      The primary stage for the application.
     * @param startGame  A callback function to launch the game when the "Start Game" button is clicked.
     */
    public void display(Stage stage, Runnable startGame) {
        // Create menu layout
        VBox menuLayout = new VBox(30);
        menuLayout.setStyle("-fx-alignment: center; -fx-background-color: black; -fx-padding: 20;");

        // Title Label
        Label titleLabel = new Label("Sky Battle");
        titleLabel.setStyle("-fx-font-size: 50px; -fx-text-fill: white; -fx-font-weight: bold;");

        // Start Button
        Button startButton = new Button("Start Game");
        startButton.setStyle("-fx-font-size: 20px; -fx-padding: 10px;");
        startButton.setOnAction(event -> startGame.run());

        // Exit Button
        Button exitButton = new Button("Exit");
        exitButton.setStyle("-fx-font-size: 20px; -fx-padding: 10px;");
        exitButton.setOnAction(event -> stage.close());

        // Add components to layout
        menuLayout.getChildren().addAll(titleLabel, startButton, exitButton);

        // Set up and display the scene
        Scene menuScene = new Scene(menuLayout, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage.setScene(menuScene);
        stage.show();
    }
}
