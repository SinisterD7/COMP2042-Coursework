package com.example.demo.controller;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The Main class extends the Application class and serves as the entry point for the JavaFX application.
 * It initializes the primary stage with specified properties such as title and resizability.
 * It uses {@link StartMenu} to display the start menu and launches the game when selected.
 *
 * @author [Your Name]
 * @version 1.0
 */
public class Main extends Application {

	/**
	 * The title of the application.
	 */
	private static final String TITLE = "Sky Battle";

	/**
	 * An instance of the Controller used to manage the game's control logic.
	 */
	private Controller myController;

	/**
	 * The start method is called when the JavaFX application is launched.
	 * It displays the start menu and sets up the game stage.
	 *
	 * @param stage The primary stage of the JavaFX application.
	 */
	@Override
	public void start(Stage stage) {
		stage.setTitle(TITLE);
		stage.setResizable(false);

		// Display the start menu
		StartMenu startMenu = new StartMenu();
		startMenu.display(stage, () -> {
			try {
				launchGame(stage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Launches the game by setting up the game stage and invoking the Controller.
	 *
	 * @param stage The primary stage of the JavaFX application.
	 * @throws Exception if any error occurs during game initialization.
	 */
	private void launchGame(Stage stage) throws Exception {
		myController = new Controller(stage);
		myController.launchGame();
	}

	/**
	 * The main method that launches the JavaFX application.
	 *
	 * @param args Command-line arguments.
	 */
	public static void main(String[] args) {
		launch();
	}
}
