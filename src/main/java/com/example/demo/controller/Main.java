package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The Main class extends the Application class and serves as the entry point for the JavaFX application.
 * It initializes the primary stage with specified properties such as title, size, and resizability.
 * It also creates an instance of the Controller and calls the launchGame method on it to start the game.
 *
 * @author [Your Name]
 * @version 1.0
 */
public class Main extends Application {

	/**
	 * The width of the screen in pixels.
	 */
	private static final int SCREEN_WIDTH = 1300;
	/**
	 * The height of the screen in pixels.
	 */
	private static final int SCREEN_HEIGHT = 750;
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
	 * It sets up the primary stage by setting the title, size, and resizability.
	 * Then it creates a Controller instance and calls its launchGame method.
	 *
	 * @param stage The primary stage of the JavaFX application.
	 * @throws ClassNotFoundException if a required class cannot be found.
	 * @throws NoSuchMethodException if a required method cannot be found.
	 * @throws SecurityException if a security issue occurs.
	 * @throws InstantiationException if an object cannot be instantiated.
	 * @throws IllegalAccessException if there is an access violation.
	 * @throws IllegalArgumentException if invalid arguments are passed.
	 * @throws InvocationTargetException if an invocation target exception occurs.
	 */
	@Override
	public void start(Stage stage) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		stage.setTitle(TITLE);
		stage.setResizable(false);
		stage.setHeight(SCREEN_HEIGHT);
		stage.setWidth(SCREEN_WIDTH);
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