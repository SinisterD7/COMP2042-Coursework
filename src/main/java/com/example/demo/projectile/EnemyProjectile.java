package com.example.demo.projectile;

/**
 * Represents a projectile fired by an enemy in the game.
 * This projectile moves horizontally to the left at a constant velocity.
 * The class extends {@link Projectile}, inheriting common projectile behavior.
 *
 * @see Projectile
 */
public class EnemyProjectile extends Projectile {

	/**
	 * The name of the image representing the enemy projectile.
	 */
	private static final String IMAGE_NAME = "enemyFire.png";

	/**
	 * The height of the enemy projectile's image, in pixels.
	 */
	private static final int IMAGE_HEIGHT = 50;

	/**
	 * The horizontal velocity of the enemy projectile, in pixels per update.
	 * A negative value indicates movement to the left.
	 */
	private static final int HORIZONTAL_VELOCITY = -10;

	/**
	 * Creates a new enemy projectile with the specified initial position.
	 *
	 * @param initialXPos the initial x-coordinate of the projectile.
	 * @param initialYPos the initial y-coordinate of the projectile.
	 */
	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the position of the projectile by moving it horizontally to the left.
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
