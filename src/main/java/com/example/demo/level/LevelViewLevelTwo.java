package com.example.demo.level;

import com.example.demo.Image.ShieldImage;
import javafx.scene.Group;

/**
 * LevelViewLevelTwo is a subclass of LevelView, specifically designed for the second level of the game.
 * It handles the display and management of images related to LevelTwo, including the shield image.
 * It initializes the shield image at a specific position and provides methods to show or hide it.
 */
public class LevelViewLevelTwo extends LevelView {

	/**
	 * The x-coordinate position of the shield image.
	 * This constant defines where the shield image will be placed horizontally.
	 */
	private static final int SHIELD_X_POSITION = 1150;

	/**
	 * The y-coordinate position of the shield image.
	 * This constant defines where the shield image will be placed vertically.
	 */
	private static final int SHIELD_Y_POSITION = 500;

	/**
	 * The root group that holds all the visual elements.
	 * It is used to add images and other UI elements to the scene.
	 */
	private final Group root;

	/**
	 * The shield image for this level.
	 * Represents the shield that might be displayed during the game.
	 */
	private final ShieldImage shieldImage;

	/**
	 * Constructor for LevelViewLevelTwo.
	 *
	 * @param root          The root group of the scene.
	 * @param heartsToDisplay The number of hearts to display (possibly related to player health).
	 */
	public LevelViewLevelTwo(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		this.root = root;
		this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
		addImagesToRoot();
	}

	/**
	 * Adds images to the root group.
	 * In this case, it adds the shield image to the root group.
	 */
	private void addImagesToRoot() {
		root.getChildren().addAll(shieldImage);
	}

	/**
	 * Shows the shield image.
	 * Makes the shield image visible on the screen.
	 */
	public void showShield() {
		shieldImage.showShield();
	}

	/**
	 * Hides the shield image.
	 * Makes the shield image invisible on the screen.
	 */
	public void hideShield() {
		shieldImage.hideShield();
	}
}