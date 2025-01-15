package com.example.demo.Actor;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Abstract class ActiveActor represents an active actor in the application.
 * It extends ImageView, meaning it is associated with an image.
 * This class provides basic functionality for actors that have movement and position within the scene.
 *
 * @author [Your Name]
 * @version 1.0
 */
public abstract class ActiveActor extends ImageView {

	/**
	 * The location of the image resources.
	 * This is a constant that holds the path where the images are stored.
	 */
	private static final String IMAGE_LOCATION = "/com/example/demo/images/";

	/**
	 * Constructor for the ActiveActor.
	 * Initializes the ImageView with the specified image, sets its initial position,
	 * height, and maintains the aspect ratio.
	 *
	 * @param imageName     The name of the image file.
	 * @param imageHeight   The height of the image.
	 * @param initialXPos   The initial x-coordinate position.
	 * @param initialYPos   The initial y-coordinate position.
	 */
	public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		//this.setImage(new Image(IMAGE_LOCATION + imageName));
		this.setImage(new Image(getClass().getResource(IMAGE_LOCATION + imageName).toExternalForm()));
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
	}

	/**
	 * Abstract method to update the position of the actor.
	 * Subclasses must implement this method to define how the actor's position changes over time.
	 */
	public abstract void updatePosition();

	/**
	 * Moves the actor horizontally by the specified amount.
	 * It modifies the translateX property of the ImageView.
	 *
	 * @param horizontalMove The amount to move the actor horizontally.
	 */
	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}

	/**
	 * Moves the actor vertically by the specified amount.
	 * It modifies the translateY property of the ImageView.
	 *
	 * @param verticalMove The amount to move the actor vertically.
	 */
	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}

	/**
	 * Nested abstract class ActiveActorDestructible represents a destructible active actor.
	 * It extends ActiveActor and implements the Destructible interface.
	 * Such actors can take damage and be destroyed.
	 *
	 * @author [Your Name]
	 * @version 1.0
	 */
	public abstract static class ActiveActorDestructible extends ActiveActor implements Destructible {

		/**
		 * Flag to indicate whether the actor is destroyed.
		 */
		private boolean isDestroyed;

		/**
		 * Constructor for ActiveActorDestructible.
		 * Calls the superclass constructor to initialize the actor's image and position.
		 * Also initializes the destroyed flag.
		 *
		 * @param imageName     The name of the image file.
		 * @param imageHeight   The height of the image.
		 * @param initialXPos   The initial x-coordinate position.
		 * @param initialYPos   The initial y-coordinate position.
		 */
		public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
			super(imageName, imageHeight, initialXPos, initialYPos);
			isDestroyed = false;
		}

		/**
		 * Abstract method to update the position of the actor.
		 * Subclasses must implement this method.
		 */
		@Override
		public abstract void updatePosition();

		/**
		 * Abstract method to update the actor's state.
		 * Subclasses must implement this method to define how the actor updates itself.
		 */
		public abstract void updateActor();

		/**
		 * Abstract method to make the actor take damage.
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
		 * Sets the destroyed flag.
		 *
		 * @param isDestroyed The value to set the isDestroyed flag to.
		 */
		protected void setDestroyed(boolean isDestroyed) {
			this.isDestroyed = isDestroyed;
		}

		/**
		 * Checks if the actor is destroyed.
		 *
		 * @return true if the actor is destroyed, false otherwise.
		 */
		public boolean isDestroyed() {
			return isDestroyed;
		}
	}
}