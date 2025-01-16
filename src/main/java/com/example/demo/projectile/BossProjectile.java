package com.example.demo.projectile;

/**
 * The BossProjectile class extends Projectile and represents a projectile fired by the boss in the game.
 * It has its own properties like image name, height, velocity, and initial position.
 * It handles the movement of the projectile.
 */
public class BossProjectile extends Projectile {

	/**
	 * The name of the boss projectile's image file.
	 * This is used to load the image of the boss projectile from the resources.
	 */
	private static final String IMAGE_NAME = "fireball.png";

	/**
	 * The height of the boss projectile's image.
	 * It defines the height of the boss projectile's image when displayed.
	 */
	private static final int IMAGE_HEIGHT = 75;

	/**
	 * The horizontal velocity of the boss projectile.
	 * It determines how fast the boss projectile moves horizontally. A negative value indicates
	 * it moves from right to left.
	 */
	private static final int HORIZONTAL_VELOCITY = -15;

	/**
	 * The initial x-coordinate position of the boss projectile.
	 * This sets the starting horizontal position of the boss projectile.
	 */
	private static final int INITIAL_X_POSITION = 950;

	/**
	 * Constructor for the BossProjectile class.
	 *
	 * @param initialYPos The initial y-coordinate position of the boss projectile.
	 */
	public BossProjectile(double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
	}

	/**
	 * Updates the position of the boss projectile.
	 * Moves the boss projectile horizontally based on its horizontal velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the state of the boss projectile.
	 * Calls updatePosition() to update the boss projectile's position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}