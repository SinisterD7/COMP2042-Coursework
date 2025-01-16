package com.example.demo.plane;

import com.example.demo.projectile.UserProjectile;

/**
 * The UserPlane class extends FighterPlane and represents the player's plane in the game.
 * It manages the plane's movement, projectile firing, and kill count.
 * It has its own boundaries, initial position, and velocity for movement.
 */
public class UserPlane extends FighterPlane {

	/**
	 * The name of the user plane's image file.
	 * This is used to load the image of the user plane from the resources.
	 */
	private static final String IMAGE_NAME = "userplane.png";

	/**
	 * The upper bound of the user plane's y-position.
	 * The user plane cannot move above this y-coordinate.
	 */
	private static final double Y_UPPER_BOUND = -40;

	/**
	 * The lower bound of the user plane's y-position.
	 * The user plane cannot move below this y-coordinate.
	 */
	private static final double Y_LOWER_BOUND = 600.0;

	/**
	 * The initial x-coordinate position of the user plane.
	 * This sets the starting horizontal position of the user plane.
	 */
	private static final double INITIAL_X_POSITION = 5.0;

	/**
	 * The initial y-coordinate position of the user plane.
	 * This sets the starting vertical position of the user plane.
	 */
	private static final double INITIAL_Y_POSITION = 300.0;

	/**
	 * The height of the user plane's image.
	 * It defines the height of the user plane's image when displayed.
	 */
	private static final int IMAGE_HEIGHT = 150;

	/**
	 * The vertical velocity of the user plane.
	 * It determines how fast the user plane moves vertically.
	 */
	private static final int VERTICAL_VELOCITY = 8;

	/**
	 * The x-position of the user plane's projectile.
	 * This is the fixed x-position from which the projectile is fired.
	 */
	private static final int PROJECTILE_X_POSITION = 110;

	/**
	 * The offset for the projectile's y-position.
	 * This value is used to adjust the y-position when the user plane fires a projectile.
	 */
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20;

	/**
	 * The velocity multiplier for the user plane's movement.
	 * It controls the direction and speed of vertical movement.
	 */
	private int velocityMultiplier;

	/**
	 * The number of kills made by the user plane.
	 * It keeps track of the number of enemies killed by the user plane.
	 */
	private int numberOfKills;

	/**
	 * Constructor for the UserPlane class.
	 *
	 * @param initialHealth The initial health of the user plane.
	 */
	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		velocityMultiplier = 0;
	}

	/**
	 * Updates the position of the user plane.
	 * Moves the user plane vertically based on the velocity multiplier, and checks position bounds.
	 */
	@Override
	public void updatePosition() {
		if (isMoving()) {
			double initialTranslateY = getTranslateY();
			this.moveVertically(VERTICAL_VELOCITY * velocityMultiplier);
			double newPosition = getLayoutY() + getTranslateY();
			if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}
		}
	}

	/**
	 * Updates the state of the user plane.
	 * Calls updatePosition() to update the user plane's position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

	/**
	 * Fires a projectile from the user plane.
	 *
	 * @return A new UserProjectile at the specified position.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		return new UserProjectile(PROJECTILE_X_POSITION, getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
	}

	/**
	 * Checks if the user plane is moving.
	 *
	 * @return True if the velocity multiplier is not zero, false otherwise.
	 */
	private boolean isMoving() {
		return velocityMultiplier!= 0;
	}

	/**
	 * Moves the user plane upwards.
	 * Sets the velocity multiplier to -1, indicating upward movement.
	 */
	public void moveUp() {
		velocityMultiplier = -1;
	}

	/**
	 * Moves the user plane downwards.
	 * Sets the velocity multiplier to 1, indicating downward movement.
	 */
	public void moveDown() {
		velocityMultiplier = 1;
	}

	/**
	 * Stops the user plane's movement.
	 * Sets the velocity multiplier to 0.
	 */
	public void stop() {
		velocityMultiplier = 0;
	}

	/**
	 * Gets the number of kills made by the user plane.
	 *
	 * @return The number of kills.
	 */
	public int getNumberOfKills() {
		return numberOfKills;
	}

	/**
	 * Increments the number of kills made by the user plane.
	 */
	public void incrementKillCount() {
		numberOfKills++;
	}
}