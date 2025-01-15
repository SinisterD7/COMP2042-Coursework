package com.example.demo.level;

import java.util.*;
import java.util.stream.Collectors;

import com.example.demo.Actor.ActiveActor;
import com.example.demo.plane.FighterPlane;
import com.example.demo.plane.UserPlane;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.util.Duration;

/**
 * The LevelParent class is an abstract class that serves as a base for different levels in the game.
 * It manages game elements such as actors, projectiles, the game loop, and user input handling.
 * It also handles the game lifecycle, including initialization, updating, and transitioning between levels.
 */
public abstract class LevelParent extends Observable {

	/**
	 * Adjustment value for screen height.
	 * Used to calculate the maximum Y position for enemies.
	 */
	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;

	/**
	 * Delay in milliseconds for the game loop.
	 * This value determines how often the game loop updates.
	 */
	private static final int MILLISECOND_DELAY = 50;

	/**
	 * The height of the game screen.
	 * This variable stores the height of the game screen.
	 */
	private final double screenHeight;

	/**
	 * The width of the game screen.
	 * This variable stores the width of the game screen.
	 */
	private final double screenWidth;

	/**
	 * The maximum Y position for enemies.
	 * Calculated based on the screen height and adjustment.
	 */
	private final double enemyMaximumYPosition;

	/**
	 * The root group for all game elements.
	 * Holds all the visual elements of the game.
	 */
	private final Group root;

	/**
	 * The game timeline for the main game loop.
	 * Controls the timing and looping of game updates.
	 */
	private final Timeline timeline;

	/**
	 * The user's plane.
	 * Represents the player's plane in the game.
	 */
	private final UserPlane user;

	/**
	 * The game scene.
	 * Represents the main scene of the game.
	 */
	private final Scene scene;

	/**
	 * The background image view.
	 * Displays the background image of the level.
	 */
	private final ImageView background;

	/**
	 * List of friendly units.
	 * Contains all friendly units in the game.
	 */
	private final List<ActiveActor.ActiveActorDestructible> friendlyUnits;

	/**
	 * List of enemy units.
	 * Contains all enemy units in the game.
	 */
	private final List<ActiveActor.ActiveActorDestructible> enemyUnits;

	/**
	 * List of user's projectiles.
	 * Contains all projectiles fired by the user.
	 */
	private final List<ActiveActor.ActiveActorDestructible> userProjectiles;

	/**
	 * List of enemy projectiles.
	 * Contains all projectiles fired by enemies.
	 */
	private final List<ActiveActor.ActiveActorDestructible> enemyProjectiles;

	/**
	 * The current number of enemies in the game.
	 * Keeps track of the number of active enemies.
	 */
	private int currentNumberOfEnemies;

	/**
	 * The view for the current level.
	 * Manages the visual representation of the level.
	 */
	private LevelView levelView;

	/**
	 * Constructor for the LevelParent class.
	 *
	 * @param backgroundImageName The name of the background image.
	 * @param screenHeight       The height of the screen.
	 * @param screenWidth        The width of the screen.
	 * @param playerInitialHealth The initial health of the player.
	 */
	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		this.user = new UserPlane(playerInitialHealth);
		this.friendlyUnits = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();

		this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.currentNumberOfEnemies = 0;
		initializeTimeline();
		friendlyUnits.add(user);
	}

	/**
	 * Abstract method to initialize friendly units.
	 * Subclasses should implement this method to initialize friendly units.
	 */
	protected abstract void initializeFriendlyUnits();

	/**
	 * Abstract method to check if the game is over.
	 * Subclasses should implement this method to define game over conditions.
	 */
	protected abstract void checkIfGameOver();

	/**
	 * Abstract method to spawn enemy units.
	 * Subclasses should implement this method to spawn enemy units.
	 */
	protected abstract void spawnEnemyUnits();

	/**
	 * Abstract method to instantiate the level view.
	 * Subclasses should implement this method to create the level view.
	 */
	protected abstract LevelView instantiateLevelView();

	/**
	 * Initializes the game scene.
	 * Sets up the background, friendly units, and shows the heart display.
	 *
	 * @return The initialized game scene.
	 */
	public Scene initializeScene() {
		initializeBackground();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		return scene;
	}

	/**
	 * Starts the game.
	 * Gives focus to the background and starts the timeline.
	 */
	public void startGame() {
		background.requestFocus();
		timeline.play();
	}

	/**
	 * Transitions to the next level.
	 * Notifies observers about the next level.
	 *
	 * @param levelName The name of the next level.
	 */
	public void goToNextLevel(String levelName) {
		setChanged();
		endGame();
		notifyObservers(levelName);
	}

	/**
	 * Updates the game scene.
	 * This method is called periodically by the game loop and updates various game elements.
	 */
	private void updateScene() {
		spawnEnemyUnits();
		updateActors();
		generateEnemyFire();
		updateNumberOfEnemies();
		handleEnemyPenetration();
		handleUserProjectileCollisions();
		handleEnemyProjectileCollisions();
		handlePlaneCollisions();
		removeAllDestroyedActors();
		updateKillCount();
		updateLevelView();
		checkIfGameOver();
	}

	/**
	 * Initializes the game timeline.
	 * Sets the cycle count and adds a key frame for the game loop.
	 */
	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	/**
	 * Initializes the background.
	 * Sets up key event handlers for user input and adds the background to the root.
	 */
	private void initializeBackground() {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);
		background.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode kc = e.getCode();
				if (kc == KeyCode.UP) user.moveUp();
				if (kc == KeyCode.DOWN) user.moveDown();
				if (kc == KeyCode.SPACE) fireProjectile();
			}
		});
		background.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode kc = e.getCode();
				if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.stop();
			}
		});
		root.getChildren().add(background);
	}

	/**
	 * Fires a projectile from the user's plane.
	 * Adds the projectile to the root and the userProjectiles list.
	 */
	private void fireProjectile() {
		ActiveActor.ActiveActorDestructible projectile = user.fireProjectile();
		root.getChildren().add(projectile);
		userProjectiles.add(projectile);
	}

	/**
	 * Generates enemy fire.
	 * Calls the fireProjectile method on each enemy.
	 */
	private void generateEnemyFire() {
		enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
	}

	/**
	 * Spawns an enemy projectile.
	 * Adds the projectile to the root and the enemyProjectiles list if it is not null.
	 *
	 * @param projectile The projectile to spawn.
	 */
	private void spawnEnemyProjectile(ActiveActor.ActiveActorDestructible projectile) {
		if (projectile!= null) {
			root.getChildren().add(projectile);
			enemyProjectiles.add(projectile);
		}
	}

	/**
	 * Updates all actors in the game.
	 * Calls the updateActor method on each actor in the friendlyUnits, enemyUnits, userProjectiles, and enemyProjectiles lists.
	 */
	private void updateActors() {
		friendlyUnits.forEach(plane -> plane.updateActor());
		enemyUnits.forEach(enemy -> enemy.updateActor());
		userProjectiles.forEach(projectile -> projectile.updateActor());
		enemyProjectiles.forEach(projectile -> projectile.updateActor());
	}

	/**
	 * Removes all destroyed actors from the game.
	 * Calls removeDestroyedActors for each actor list.
	 */
	private void removeAllDestroyedActors() {
		removeDestroyedActors(friendlyUnits);
		removeDestroyedActors(enemyUnits);
		removeDestroyedActors(userProjectiles);
		removeDestroyedActors(enemyProjectiles);
	}

	/**
	 * Removes destroyed actors from a given list.
	 * Filters out destroyed actors, removes them from the root, and from the list.
	 *
	 * @param actors The list of actors to process.
	 */
	private void removeDestroyedActors(List<ActiveActor.ActiveActorDestructible> actors) {
		List<ActiveActor.ActiveActorDestructible> destroyedActors = actors.stream().filter(actor -> actor.isDestroyed())
				.collect(Collectors.toList());
		root.getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}

	/**
	 * Handles plane collisions.
	 * Calls handleCollisions with friendlyUnits and enemyUnits.
	 */
	private void handlePlaneCollisions() {
		handleCollisions(friendlyUnits, enemyUnits);
	}

	/**
	 * Handles collisions between user projectiles and enemy units.
	 * Calls handleCollisions with userProjectiles and enemyUnits.
	 */
	private void handleUserProjectileCollisions() {
		handleCollisions(userProjectiles, enemyUnits);
	}

	/**
	 * Handles collisions between enemy projectiles and friendly units.
	 * Calls handleCollisions with enemyProjectiles and friendlyUnits.
	 */
	private void handleEnemyProjectileCollisions() {
		handleCollisions(enemyProjectiles, friendlyUnits);
	}

	/**
	 * Handles collisions between two sets of actors.
	 * Causes damage to intersecting actors.
	 *
	 * @param actors1 The first set of actors.
	 * @param actors2 The second set of actors.
	 */
	private void handleCollisions(List<ActiveActor.ActiveActorDestructible> actors1,
								  List<ActiveActor.ActiveActorDestructible> actors2) {
		for (ActiveActor.ActiveActorDestructible actor : actors2) {
			for (ActiveActor.ActiveActorDestructible otherActor : actors1) {
				if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
					actor.takeDamage();
					otherActor.takeDamage();
				}
			}
		}
	}

	/**
	 * Handles enemy penetration.
	 * Causes damage to the user if an enemy has penetrated defenses.
	 *
	 * @param
	 */
	private void handleEnemyPenetration() {
		for (ActiveActor.ActiveActorDestructible enemy : enemyUnits) {
			if (enemyHasPenetratedDefenses(enemy)) {
				user.takeDamage();
				enemy.destroy();
			}
		}
	}

	/**
	 * Updates the level view.
	 * Updates the heart display based on the user's health.
	 */
	private void updateLevelView() {
		levelView.removeHearts(user.getHealth());
	}

	/**
	 * Updates the user's kill count.
	 * Increases the kill count based on the difference between the current and previous number of enemies.
	 */
	private void updateKillCount() {
		for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
			user.incrementKillCount();
		}
	}

	/**
	 * Checks if an enemy has penetrated defenses.
	 *
	 * @param enemy The enemy to check.
	 * @return True if the enemy has penetrated defenses, false otherwise.
	 */
	private boolean enemyHasPenetratedDefenses(ActiveActor.ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > screenWidth;
	}

	/**
	 * Handles winning the game.
	 * Stops the timeline and shows the win image.
	 */
	protected void winGame() {
		timeline.stop();
		levelView.showWinImage();
	}

	/**
	 * Handles losing the game.
	 * Stops the timeline and shows the game over image.
	 */
	protected void loseGame() {
		timeline.stop();
		levelView.showGameOverImage();
	}

	/**
	 * Ends the game.
	 * Stops the timeline, clears all key frames, removes event listeners, and clears all game elements.
	 */
	public void endGame() {
		// Stop the timeline (pause any ongoing animations or timed events)
		timeline.stop();
		// Clear all key frames from the timeline (removes any scheduled animations or events)
		timeline.getKeyFrames().clear();
		// Remove the key release event listener from the background
		// (prevents further keyboard events from being processed after game ends)
		background.setOnKeyReleased(null);
		// Clear all child nodes from the root container (removes all visible game objects from the screen)
		root.getChildren().clear();
		// Clear the collections tracking different game elements:
		// - Friendly units (e.g., player's characters or allies)
		friendlyUnits.clear();
		// - Enemy projectiles (e.g., bullets fired by enemies)
		enemyProjectiles.clear();
		// - Enemy units (e.g., enemy characters or enemies)
		enemyUnits.clear();
		// - User projectiles (e.g., bullets fired by the player)
		userProjectiles.clear();
	}

	/**
	 * Returns the user's plane.
	 *
	 * @return The user's plane.
	 */
	protected UserPlane getUser() {
		return user;
	}

	/**
	 * Returns the root group.
	 *
	 * @return The root group.
	 */
	protected Group getRoot() {
		return root;
	}

	/**
	 * Returns the current number of enemies.
	 *
	 * @return The number of active enemies.
	 */
	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	/**
	 * Adds an enemy unit.
	 * Adds the enemy to the enemyUnits list and the root.
	 *
	 * @param enemy The enemy unit to add.
	 */
	protected void addEnemyUnit(ActiveActor.ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
	}

	/**
	 * Returns the maximum Y position for enemies.
	 *
	 * @return The maximum Y position for enemies.
	 */
	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	/**
	 * Returns the screen width.
	 *
	 * @return The screen width.
	 */
	protected double getScreenWidth() {
		return screenWidth;
	}

	/**
	 * Checks if the user is destroyed.
	 *
	 * @return True if the user is destroyed, false otherwise.
	 */
	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	/**
	 * Updates the number of enemies.
	 * Sets the currentNumberOfEnemies to the size of the enemyUnits list.
	 */
	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = enemyUnits.size();
	}
}