package com.example.demo.Image;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The WinImage class extends ImageView and is used to display a winning image.
 * It manages the visibility, size, and position of the winning image.
 */
public class WinImage extends ImageView {

	/**
	 * The name of the winning image file.
	 * This is the relative path used to locate the winning image resource.
	 */
	private static final String IMAGE_NAME = "/com/example/demo/images/youwin.png";

	/**
	 * The height of the winning image in pixels.
	 * This constant defines the height of the image when displayed.
	 */
	private static final int HEIGHT = 500;

	/**
	 * The width of the winning image in pixels.
	 * This constant defines the width of the image when displayed.
	 */
	private static final int WIDTH = 600;

	/**
	 * Constructor for the WinImage class.
	 *
	 * @param xPosition the x-coordinate position of the winning image in the scene
	 * @param yPosition the y-coordinate position of the winning image in the scene
	 */
	public WinImage(double xPosition, double yPosition) {
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		this.setVisible(false);
		this.setFitHeight(HEIGHT);
		this.setFitWidth(WIDTH);
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
	}

	/**
	 * Shows the winning image.
	 * This method sets the visibility of the winning image to true.
	 */
	public void showWinImage() {
		this.setVisible(true);
	}
}