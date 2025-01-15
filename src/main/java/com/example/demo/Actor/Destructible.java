package com.example.demo.Actor;

/**
 * The Destructible interface defines the behavior of objects that can take damage and be destroyed.
 * It includes methods for taking damage and destroying the object.
 *
 * @author [Your Name]
 * @version 1.0
 */
public interface Destructible {

	/**
	 * This method is called when the object takes damage.
	 * Implementations of this method should define how the object responds to damage,
	 * which could involve reducing health, changing appearance, or other effects.
	 */
	void takeDamage();

	/**
	 * This method is called to destroy the object.
	 * Implementations of this method should define the destruction process,
	 * such as removing the object from the game, playing an animation,
	 * or changing its state to indicate destruction.
	 */
	void destroy();
}