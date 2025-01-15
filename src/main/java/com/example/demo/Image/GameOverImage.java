package com.example.demo.Image;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The GameOverImage class extends ImageView and is used to display a specific image when the game ends.
 * It loads the image through a specified image path and sets its position in the scene.
 */
public class GameOverImage extends ImageView {

	/**
	 * Stores the relative path of the game-over image.
	 * This path is used to locate the image file to be displayed when the game ends.
	 */
	private static final String IMAGE_NAME = "/com/example/demo/images/gameover.png";

	/**
	 * Constructor for creating a GameOverImage object.
	 *
	 * @param xPosition the x-coordinate position of the image in the scene
	 * @param yPosition the y-coordinate position of the image in the scene
	 */
	public GameOverImage(double xPosition, double yPosition) {
		setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
//        setImage(ImageSetUp.getImageList().get(ImageSetUp.getGameOver()));
		setLayoutX(xPosition);
		setLayoutY(yPosition);
	}
}