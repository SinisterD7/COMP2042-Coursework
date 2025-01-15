package com.example.demo.level;

import com.example.demo.Actor.ActiveActor;
import com.example.demo.plane.EnemyPlane;

/**
 * LevelOne is a concrete implementation of the LevelParent class representing the first level of the game.
 * It sets up specific parameters and behaviors for the first level, including background image,
 * enemy spawn probability, number of enemies, kill target, and initial player health.
 * It also defines how to check if the game is over, initialize friendly units, spawn enemies,
 * and instantiate the level view.
 */
public class LevelOne extends LevelParent {

	/**
	 * The relative path of the background image for this level.
	 * This is used to load the background image from the resources.
	 */
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";

	/**
	 * The fully qualified name of the next level.
	 * This is used to transition the player to the next level when certain conditions are met.
	 */
	private static final String NEXT_LEVEL = "com.example.demo.level.LevelTwo";

	/**
	 * The total number of enemies that can be spawned in this level.
	 * This sets the maximum number of enemies that will appear in this level.
	 */
	private static final int TOTAL_ENEMIES = 5;

	/**
	 * The number of kills required to advance to the next level.
	 * The player must achieve this number of kills to move on to the next level.
	 */
	private static final int KILLS_TO_ADVANCE = 10;

	/**
	 * The probability of spawning an enemy.
	 * This value (between 0 and 1) determines the likelihood of spawning an enemy.
	 */
	private static final double ENEMY_SPAWN_PROBABILITY = 0.20;

	/**
	 * The initial health of the player at the start of this level.
	 * This sets the player's health when the level begins.
	 */
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/**
	 * Constructor for LevelOne.
	 *
	 * @param screenHeight the height of the game screen
	 * @param screenWidth  the width of the game screen
	 */
	public LevelOne(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Checks if the game is over.
	 * If the user is destroyed, the game is lost. If the user has reached the kill target, the game advances to the next level.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		} else if (userHasReachedKillTarget()) {
			goToNextLevel(NEXT_LEVEL);
		}
	}

	/**
	 * Initializes the friendly units.
	 * In this implementation, it adds the user to the root of the scene.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Spawns enemy units.
	 * It spawns enemies based on the ENEMY_SPAWN_PROBABILITY until the TOTAL_ENEMIES limit is reached.
	 * It uses a random value and checks against the probability to decide whether to spawn an enemy.
	 * The initial Y position of the enemy is randomly determined within the allowed range.
	 */
	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				ActiveActor.ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
				addEnemyUnit(newEnemy);
			}
		}
	}

	/**
	 * Instantiates the level view.
	 * It creates a new LevelView object using the root and the player's initial health.
	 *
	 * @return a new LevelView object
	 */
	@Override
	protected LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Checks if the user has reached the kill target.
	 *
	 * @return true if the user's number of kills is greater than or equal to KILLS_TO_ADVANCE, false otherwise
	 */
	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}
}