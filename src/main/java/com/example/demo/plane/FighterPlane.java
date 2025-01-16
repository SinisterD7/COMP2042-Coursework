package com.example.demo.plane;

import com.example.demo.Actor.ActiveActor;

/**
 * The FighterPlane class is an abstract class that extends ActiveActor.ActiveActorDestructible.
 * It represents a fighter plane in the game, which has health and can fire projectiles.
 * It also handles taking damage and provides methods to calculate projectile positions.
 */
public abstract class FighterPlane extends ActiveActor.ActiveActorDestructible {

	/**
	 * The health of the fighter plane.
	 * Represents the remaining health of the fighter plane.
	 */
	private int health;

	/**
	 * Constructor for the FighterPlane class.
	 *
	 * @param imageName   The name of the image file for the fighter plane.
	 * @param imageHeight The height of the fighter plane's image.
	 * @param initialXPos The initial x-coordinate position of the fighter plane.
	 * @param initialYPos The initial y-coordinate position of the fighter plane.
	 * @param health      The initial health of the fighter plane.
	 */
	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
	}

	/**
	 * Abstract method to fire a projectile.
	 * Subclasses must implement this method to define how the fighter plane fires a projectile.
	 *
	 * @return An ActiveActorDestructible representing the fired projectile.
	 */
	public abstract ActiveActorDestructible fireProjectile();

	/**
	 * Handles taking damage for the fighter plane.
	 * Decreases the health of the fighter plane by one and destroys it if health reaches zero.
	 */
	@Override
	public void takeDamage() {
		health--;
		if (healthAtZero()) {
			this.destroy();
		}
	}

	/**
	 * Calculates the x-position of the projectile.
	 *
	 * @param xPositionOffset The offset value for the x-position of the projectile.
	 * @return The calculated x-position of the projectile.
	 */
	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	/**
	 * Calculates the y-position of the projectile.
	 *
	 * @param yPositionOffset The offset value for the y-position of the projectile.
	 * @return The calculated y-position of the projectile.
	 */
	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	/**
	 * Checks if the health of the fighter plane is zero.
	 *
	 * @return True if the health is zero, false otherwise.
	 */
	private boolean healthAtZero() {
		return health == 0;
	}

	/**
	 * Gets the current health of the fighter plane.
	 *
	 * @return The current health of the fighter plane.
	 */
	public int getHealth() {
		return health;
	}
}