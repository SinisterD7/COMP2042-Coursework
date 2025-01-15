package com.example.demo.level;

import com.example.demo.plane.Boss;
import com.example.demo.Image.ShieldImage;

/**
 * LevelTwo is a concrete implementation of the LevelParent class representing the second level of the game.
 * It sets up specific parameters and behaviors for the second level, including background image,
 * initial player health, and the introduction of a boss.
 * It also defines how to check if the game is over, initialize friendly units, spawn enemies,
 * and instantiate the level view.
 */
public class LevelTwo extends LevelParent {

	/**
	 * The relative path of the background image for this level.
	 * This is used to load the background image from the resources.
	 */
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";

	/**
	 * The initial health of the player at the start of this level.
	 * This sets the player's health when the level begins.
	 */
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/**
	 * The boss for this level.
	 * Represents the main enemy in this level that the player must defeat.
	 */
	private final Boss boss;

	/**
	 * The view for this level.
	 * Manages the visual representation of the second level.
	 */
	private LevelViewLevelTwo levelView;

	/**
	 * The shield image associated with the boss.
	 * Represents the shield that the boss might have.
	 */
	private ShieldImage shieldImage;

	/**
	 * Constructor for LevelTwo.
	 *
	 * @param screenHeight the height of the game screen
	 * @param screenWidth  the width of the game screen
	 */
	public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		boss = new Boss();
		shieldImage = boss.getShieldImage();
	}

	/**
	 * Initializes the friendly units.
	 * In this implementation, it adds the user and the shield image to the root of the scene.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
		getRoot().getChildren().add(shieldImage);
	}

	/**
	 * Checks if the game is over.
	 * If the user is destroyed, the game is lost. If the boss is destroyed, the game is won.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		} else if (boss.isDestroyed()) {
			winGame();
		}
	}

	/**
	 * Spawns enemy units.
	 * In this level, only the boss is spawned if there are no current enemies.
	 */
	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
		}
	}

	/**
	 * Instantiates the level view.
	 * It creates a new LevelViewLevelTwo object using the root and the player's initial health.
	 *
	 * @return a new LevelViewLevelTwo object
	 */
	@Override
	protected LevelView instantiateLevelView() {
		levelView = new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH);
		return levelView;
	}
}