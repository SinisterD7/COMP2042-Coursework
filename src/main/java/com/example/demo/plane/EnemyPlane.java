package com.example.demo.plane;

import com.example.demo.projectile.EnemyProjectile;

/**
 * The EnemyPlane class extends FighterPlane and represents an enemy plane in the game.
 * It has its own properties like image name, height, velocity, and fire rate.
 * It also handles the movement and projectile firing of the enemy plane.
 */
public class EnemyPlane extends FighterPlane {

	/**
	 * The name of the enemy plane's image file.
	 * This is used to load the image of the enemy plane from the resources.
	 */
	private static final String IMAGE_NAME = "enemyplane.png";

	/**
	 * The height of the enemy plane's image.
	 * It defines the height of the enemy plane's image when displayed.
	 */
	private static final int IMAGE_HEIGHT = 150;

	/**
	 * The horizontal velocity of the enemy plane.
	 * It determines how fast the enemy plane moves horizontally. A negative value indicates
	 * it moves from right to left.
	 */
	private static final int HORIZONTAL_VELOCITY = -6;

	/**
	 * The offset for the projectile's x-position.
	 * This value is used to adjust the x-position when the enemy plane fires a projectile.
	 */
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;

	/**
	 * The offset for the projectile's y-position.
	 * This value is used to adjust the y-position when the enemy plane fires a projectile.
	 */
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;

	/**
	 * The initial health of the enemy plane.
	 * The amount of health the enemy plane has at the start.
	 */
	private static final int INITIAL_HEALTH = 1;

	/**
	 * The fire rate of the enemy plane.
	 * A value between 0 and 1 indicating the probability of the enemy plane firing a projectile in a frame.
	 */
	private static final double FIRE_RATE = 0.01;

	/**
	 * Constructor for the EnemyPlane class.
	 *
	 * @param initialXPos The initial x-coordinate position of the enemy plane.
	 * @param initialYPos The initial y-coordinate position of the enemy plane.
	 */
	public EnemyPlane(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
	}

	/**
	 * Updates the position of the enemy plane.
	 * Moves the enemy plane horizontally based on its horizontal velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Fires a projectile from the enemy plane.
	 * The enemy plane fires a projectile based on the fire rate probability.
	 *
	 * @return A new EnemyProjectile if the enemy plane fires, or null if it doesn't.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		if (Math.random() < FIRE_RATE) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPostion = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new EnemyProjectile(projectileXPosition, projectileYPostion);
		}
		return null;
	}

	/**
	 * Updates the state of the enemy plane.
	 * Calls updatePosition() to update the enemy plane's position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}