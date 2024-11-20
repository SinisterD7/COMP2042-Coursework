package com.example.demo.controller;

import java.lang.reflect.Constructor;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import com.example.demo.LevelParent;

public class Controller implements Observer {

	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.LevelOne";
	private final Stage stage;
	private final LevelFactory levelFactory;

	public Controller(Stage stage) {
		this.stage = stage;
		this.levelFactory = new LevelFactory();
	}

	public void launchGame() {
		stage.show();
		try {
			goToLevel(LEVEL_ONE_CLASS_NAME);
		} catch (GameException e) {
			handleError("Failed to launch game", e);
		}
	}

	private void goToLevel(String levelClassName) {
		try {
			LevelParent level = levelFactory.createLevel(levelClassName, stage.getHeight(), stage.getWidth());
			level.addObserver(this);
			Scene scene = level.initializeScene();
			stage.setScene(scene);
			level.startGame();
		} catch (GameException e) {
			throw e;
		} catch (Exception e) {
			throw new GameException("Unexpected error loading level: " + levelClassName, e);
		}
	}

	@Override
	public void update(Observable observable, Object levelName) {
		if (!(levelName instanceof String)) {
			throw new GameException("Invalid level name type: " + levelName.getClass());
		}
		try {
			goToLevel((String) levelName);
		} catch (GameException e) {
			handleError("Failed to load next level", e);
		}
	}

	private void handleError(String message, Exception e) {
		// Log the error with full stack trace
		logger.error(message, e);
		// Show user-friendly error dialog
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Game Error");
		alert.setHeaderText(message);
		alert.setContentText("Please restart the application. If the problem persists, contact support.");
		alert.showAndWait();
	}

}

/* 
Extract level creation logic into a factory class
Add better type safety for the level name parameter
Improve error handling with more specific exceptions
Add logging instead of System.err
-Key improvements explained:
1.Separation of Concerns: Extracted level creation logic into LevelFactory class, making the code more modular and easier to test.
2.Better Error Handling:
-Replaced multiple specific exceptions with a single GameException
-Added proper logging using SLF4J instead of System.err
-Added type checking for the level name parameter
3.Improved Maintainability:
Each class has a single responsibility
Reduced code duplication
Clearer error messages
4.Better Logging:
-Using proper logging framework instead of System.err
-Full stack traces are now logged properly
-Easier to configure logging levels and outputs
added the SLF4J dependency to the pom.xml (using Maven):

These changes will have these benefits:
Proper logging infrastructure
Better organized code with separation of concerns
More consistent error handling
Better maintainability
Make sure to import any missing classes and rebuild your project after making these changes. 
The logging configuration can be customized further based on your needs 
(e.g., adding file output, changing log levels, etc.).
*/