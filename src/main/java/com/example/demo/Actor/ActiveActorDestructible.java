package com.example.demo.Actor;

import javafx.scene.image.ImageView;

/**
 * ActiveActorDestructible is an abstract class that extends ActiveActor and implements Destructible.
 * It represents a destructible active actor in the application.
 * It includes functionality for tracking the destruction status of the actor, updating its position,
 * and handling damage taken by the actor.
 *
 * @author [Your Name]
 * @version 1.0
 */
public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	/**
	 * A boolean flag indicating whether the actor has been destroyed.
	 * If true, the actor is considered destroyed, and false indicates it is still active.
	 */
	private boolean isDestroyed;

	/**
	 * Constructor for ActiveActorDestructible.
	 *
	 * @param imageName    The name of the image associated with the actor.
	 * @param imageHeight  The height of the image representing the actor.
	 * @param initialXPos  The initial x-coordinate position of the actor.
	 * @param initialYPos  The initial y-coordinate position of the actor.
	 */
	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		isDestroyed = false;
	}

	/**
	 * Abstract method to update the position of the actor.
	 * Subclasses must implement this method to define how the actor's position changes over time.
	 */
	@Override
	public abstract void updatePosition();

	/**
	 * Abstract method to update the state or behavior of the actor.
	 * Subclasses must implement this method to define how the actor updates itself.
	 */
	public abstract void updateActor();

	/**
	 * Abstract method to handle the damage taken by the actor.
	 * Subclasses must implement this method to define how the actor responds to damage.
	 */
	@Override
	public abstract void takeDamage();

	/**
	 * Marks the actor as destroyed.
	 * Sets the isDestroyed flag to true.
	 */
	@Override
	public void destroy() {
		setDestroyed(true);
	}

	/**
	 * Sets the destruction status of the actor.
	 *
	 * @param isDestroyed The new destruction status of the actor.
	 */
	protected void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	/**
	 * Checks if the actor has been destroyed.
	 *
	 * @return true if the actor is destroyed, false otherwise.
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}
}