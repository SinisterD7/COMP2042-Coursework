package com.example.demo.Image;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * The HeartDisplay class is responsible for displaying a number of heart images.
 * It initializes a container (HBox) and populates it with heart images based on the number of hearts to display.
 * It also provides a method to remove the first heart image from the container.
 */
public class HeartDisplay {

	/**
	 * The name of the heart image file.
	 * This is the relative path used to locate the heart image resource.
	 */
	private static final String HEART_IMAGE_NAME = "/com/example/demo/images/heart.png";

	/**
	 * The height to which the heart images will be scaled.
	 * This constant defines the height of the heart images in pixels.
	 */
	private static final int HEART_HEIGHT = 50;

	/**
	 * The index of the first item, used for removing the first heart.
	 * It is used when calling the removeHeart method to remove the first heart from the container.
	 */
	private static final int INDEX_OF_FIRST_ITEM = 0;

	/**
	 * The container (HBox) that holds the heart images.
	 * It stores the heart images in a horizontal layout.
	 */
	private HBox container;

	/**
	 * The x-coordinate position of the container in the scene.
	 * This variable holds the horizontal position of the container.
	 */
	private double containerXPosition;

	/**
	 * The y-coordinate position of the container in the scene.
	 * This variable holds the vertical position of the container.
	 */
	private double containerYPosition;

	/**
	 * The number of heart images to display.
	 * This variable determines how many heart images will be added to the container.
	 */
	private int numberOfHeartsToDisplay;

	/**
	 * Constructor for the HeartDisplay class.
	 *
	 * @param xPosition        the x-coordinate position of the container in the scene
	 * @param yPosition        the y-coordinate position of the container in the scene
	 * @param heartsToDisplay  the number of heart images to display
	 */
	public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
		this.containerXPosition = xPosition;
		this.containerYPosition = yPosition;
		this.numberOfHeartsToDisplay = heartsToDisplay;
		initializeContainer();
		initializeHearts();
	}

	/**
	 * Initializes the container (HBox) and sets its position.
	 * This method creates a new HBox, and sets its layout position using the containerXPosition and containerYPosition.
	 */
	private void initializeContainer() {
		container = new HBox();
		container.setLayoutX(containerXPosition);
		container.setLayoutY(containerYPosition);
	}

	/**
	 * Initializes the heart images and adds them to the container.
	 * This method loops through the numberOfHeartsToDisplay, creates a new ImageView for each heart using the HEART_IMAGE_NAME,
	 * sets the height of the heart image to HEART_HEIGHT, and ensures the aspect ratio is preserved.
	 * Each heart image is then added to the container.
	 */
	private void initializeHearts() {
		for (int i = 0; i < numberOfHeartsToDisplay; i++) {
			ImageView heart = new ImageView(new Image(getClass().getResource(HEART_IMAGE_NAME).toExternalForm()));
			heart.setFitHeight(HEART_HEIGHT);
			heart.setPreserveRatio(true);
			container.getChildren().add(heart);
		}
	}

	/**
	 * Removes the first heart image from the container.
	 * If the container is not empty, it removes the first child (heart image) from the container.
	 */
	public void removeHeart() {
		if (!container.getChildren().isEmpty())
			container.getChildren().remove(INDEX_OF_FIRST_ITEM);
	}

	/**
	 * Gets the container (HBox).
	 *
	 * @return the container (HBox) that holds the heart images
	 */
	public HBox getContainer() {
		return container;
	}
}