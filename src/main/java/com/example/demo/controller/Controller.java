package com.example.demo.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import com.example.demo.level.LevelParent;

/**
 * The Controller class implements the Observer interface and is responsible for managing the game's control logic.
 * It handles game launching, scene switching, and exception handling.
 * It uses reflection to create different game level objects based on class names, adds the current controller as an observer to the level objects,
 * and processes update notifications from level objects.
 *
 * @author [Your Name]
 * @version 1.0
 */
public class Controller implements Observer {

	/**
	 * Stores the class name of the first level.
	 */
	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.level.LevelOne";
	/**
	 * Holds the reference to the main stage.
	 */
	private final Stage stage;

	/**
	 * Constructor that takes a Stage object and stores its reference.
	 *
	 * @param stage The main stage of the game.
	 */
	public Controller(Stage stage) {
		this.stage = stage;
	}

	/**
	 * Method to launch the game.
	 * It shows the main stage and goes to the first level.
	 *
	 * @throws ClassNotFoundException if the class with the given name cannot be found.
	 * @throws NoSuchMethodException if the required constructor is not available in the class.
	 * @throws SecurityException if a security exception occurs.
	 * @throws InstantiationException if an object cannot be instantiated.
	 * @throws IllegalAccessException if there is an access permission issue.
	 * @throws IllegalArgumentException if the arguments passed to the constructor are invalid.
	 * @throws InvocationTargetException if an exception occurs during constructor invocation.
	 */
	public void launchGame() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {

		stage.show();
		goToLevel(LEVEL_ONE_CLASS_NAME);
	}

	/**
	 * Method to go to a level specified by the class name.
	 * It uses reflection to create a level object from the class name, adds the controller as an observer to the level object,
	 * initializes the level's scene, sets it on the stage, and starts the game.
	 *
	 * @param className The name of the level class to go to.
	 * @throws ClassNotFoundException if the class with the given name cannot be found.
	 * @throws NoSuchMethodException if the required constructor is not available in the class.
	 * @throws SecurityException if a security exception occurs.
	 * @throws InstantiationException if an object cannot be instantiated.
	 * @throws IllegalAccessException if there is an access permission issue.
	 * @throws IllegalArgumentException if the arguments passed to the constructor are invalid.
	 * @throws InvocationTargetException if an exception occurs during constructor invocation.
	 */
	private void goToLevel(String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> myClass = Class.forName(className);
		Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
		LevelParent myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth());
		myLevel.addObserver(this);
		Scene scene = myLevel.initializeScene();
		stage.setScene(scene);
		myLevel.startGame();

	}

	/**
	 * Implementation of the update method from the Observer interface.
	 * Called when the observed object sends an update notification, attempts to go to the level specified in the notification.
	 * If an exception occurs, an error dialog will be displayed.
	 *
	 * @param arg0 The observed object.
	 * @param arg1 The notification object, usually the class name of the next level in this class.
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		try {
			goToLevel((String) arg1);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				 | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(e.getClass().toString());
			alert.show();
		}
	}

}