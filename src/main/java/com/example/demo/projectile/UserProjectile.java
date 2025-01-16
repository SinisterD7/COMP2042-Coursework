package com.example.demo.projectile;

/**
 * Represents a projectile fired by the user in the game.
 * This projectile moves horizontally to the right at a constant velocity.
 * The class extends {@link Projectile}, inheriting common projectile behavior.
 *
 * @see Projectile
 */
public class UserProjectile extends Projectile {

	/**
	 * The name of the image representing the user's projectile.
	 */
	private static final String IMAGE_NAME = "userfire.png";

	/**
	 * The height of the user's projectile image, in pixels.
	 */
	private static final int IMAGE_HEIGHT = 125;

	/**
	 * The horizontal velocity of the user's projectile, in pixels per update.
	 * A positive value indicates movement to the right.
	 */
	private static final int HORIZONTAL_VELOCITY = 15;

	/**
	 * Creates a new user projectile with the specified initial position.
	 *
	 * @param initialXPos the initial x-coordinate of the projectile.
	 * @param initialYPos the initial y-coordinate of the projectile.
	 */
	public UserProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the position of the projectile by moving it horizontally to the right.
	 * The velocity is determined by {@code HORIZONTAL_VELOCITY}.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the actor's state. This implementation specifically updates
	 * the projectile's position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
