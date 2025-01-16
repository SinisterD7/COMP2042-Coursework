package com.example.demo.projectile;

import com.example.demo.Actor.ActiveActor;

/**
 * Represents a general projectile in the game, providing common behavior and attributes
 * for all projectiles, such as movement and destruction upon taking damage.
 * This class is abstract and must be extended to define specific projectile behavior.
 *
 * @see ActiveActor.ActiveActorDestructible
 */
public abstract class Projectile extends ActiveActor.ActiveActorDestructible {

	/**
	 * Constructs a new projectile with the specified attributes.
	 *
	 * @param imageName     the name of the image representing the projectile.
	 * @param imageHeight   the height of the projectile's image, in pixels.
	 * @param initialXPos   the initial x-coordinate of the projectile.
	 * @param initialYPos   the initial y-coordinate of the projectile.
	 */
	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
	}

	/**
	 * Defines the behavior of the projectile when it takes damage.
	 * This implementation destroys the projectile by invoking {@link #destroy()}.
	 */
	@Override
	public void takeDamage() {
		this.destroy();
	}

	/**
	 * Abstract method to update the position of the projectile.
	 * Subclasses must provide a specific implementation for this method
	 * to define how the projectile moves.
	 */
	@Override
	public abstract void updatePosition();
}
