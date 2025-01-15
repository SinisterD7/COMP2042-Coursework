package com.example.demo.plane;

import com.example.demo.projectile.BossProjectile;
import java.util.*;
import com.example.demo.Image.ShieldImage;

/**
 * The Boss class extends FighterPlane and represents the boss enemy in the game.
 * It has unique characteristics and behaviors, such as a shield, a move pattern,
 * and a fire rate. It controls the boss's movement, firing projectiles, taking damage,
 * and shield activation.
 */
public class Boss extends FighterPlane {

	/**
	 * The name of the boss's image file.
	 * This is used to load the boss's image from the resources.
	 */
	private static final String IMAGE_NAME = "bossplane.png";

	/**
	 * The initial x-coordinate position of the boss.
	 * This sets the starting horizontal position of the boss.
	 */
	private static final double INITIAL_X_POSITION = 1000.0;

	/**
	 * The initial y-coordinate position of the boss.
	 * This sets the starting vertical position of the boss.
	 */
	private static final double INITIAL_Y_POSITION = 400;

	/**
	 * The offset for the projectile's y-position.
	 * This value is used to adjust the y-position when the boss fires projectiles.
	 */
	private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;

	/**
	 * The fire rate of the boss.
	 * A value between 0 and 1 indicating the probability of the boss firing a projectile in a frame.
	 */
	private static final double BOSS_FIRE_RATE = 0.04;

	/**
	 * The probability of the boss activating its shield.
	 * A value between 0 and 1 indicating the likelihood of the shield being activated.
	 */
	private static final double BOSS_SHIELD_PROBABILITY = 0.02;

	/**
	 * The height of the boss's image.
	 * This sets the height of the boss's image when displayed.
	 */
	private static final int IMAGE_HEIGHT = 300;

	/**
	 * The vertical velocity of the boss.
	 * This value determines how fast the boss moves vertically.
	 */
	private static final int VERTICAL_VELOCITY = 8;

	/**
	 * The health of the boss.
	 * The amount of health the boss has at the start of the level.
	 */
	private static final int HEALTH = 100;

	/**
	 * The number of different move types in a cycle.
	 * This constant affects how often the boss changes its move pattern.
	 */
	private static final int MOVE_FREQUENCY_PER_CYCLE = 5;

	/**
	 * Zero value used for some calculations.
	 * A constant representing zero, used in the move pattern and other calculations.
	 */
	private static final int ZERO = 0;

	/**
	 * The maximum number of frames the boss can move in the same direction.
	 * This limits how long the boss can move in one direction before changing.
	 */
	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;

	/**
	 * The upper bound of the boss's y-position.
	 * The boss cannot move above this y-coordinate.
	 */
	private static final int Y_POSITION_UPPER_BOUND = -100;

	/**
	 * The lower bound of the boss's y-position.
	 * The boss cannot move below this y-coordinate.
	 */
	private static final int Y_POSITION_LOWER_BOUND = 475;

	/**
	 * The maximum number of frames the shield can be activated.
	 * The shield will deactivate after this many frames.
	 */
	private static final int MAX_FRAMES_WITH_SHIELD = 20;

	/**
	 * The list of moves in the boss's move pattern.
	 * Contains vertical velocities that determine the boss's vertical movement.
	 */
	private final List<Integer> movePattern;

	/**
	 * Flag indicating if the boss has its shield activated.
	 * True if the shield is active, false otherwise.
	 */
	private boolean isShielded;

	/**
	 * The number of consecutive moves in the same direction.
	 * Keeps track of how long the boss has been moving in the same direction.
	 */
	private int consecutiveMovesInSameDirection;

	/**
	 * The index of the current move in the move pattern.
	 * Used to track the boss's current move in the move pattern.
	 */
	private int indexOfCurrentMove;

	/**
	 * The number of frames the shield has been activated.
	 * Tracks how long the shield has been active.
	 */
	private int framesWithShieldActivated;

	/**
	 * The shield image associated with the boss.
	 * Represents the shield that can be activated or deactivated.
	 */
	private final ShieldImage shieldImage;

	/**
	 * Constructor for the Boss class.
	 * Initializes the boss's properties, move pattern, and shield image.
	 */
	public Boss() {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
		movePattern = new ArrayList<>();
		consecutiveMovesInSameDirection = 0;
		indexOfCurrentMove = 0;
		framesWithShieldActivated = 0;
		isShielded = false;
		initializeMovePattern();
		shieldImage = new ShieldImage(INITIAL_X_POSITION, INITIAL_Y_POSITION);
	}

	/**
	 * Updates the boss's position.
	 * Moves the boss vertically, adjusts the shield image's position, and checks position bounds.
	 */
	@Override
	public void updatePosition() {
		double initialTranslateY = getTranslateY();
		moveVertically(getNextMove());
		double currentPosition = getLayoutY() + getTranslateY();
		shieldImage.setLayoutX(getLayoutX());
		shieldImage.setLayoutY(currentPosition);

		if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
			setTranslateY(initialTranslateY);
		}
	}

	/**
	 * Updates the boss's state.
	 * Calls updatePosition() and updateShield() to update the boss's position and shield.
	 */
	@Override
	public void updateActor() {
		updatePosition();
		updateShield();
	}

	/**
	 * Fires a projectile from the boss.
	 * The boss fires a projectile based on the bossFiresInCurrentFrame() condition.
	 *
	 * @return A new BossProjectile if the boss fires, or null if it doesn't.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		return bossFiresInCurrentFrame()? new BossProjectile(getProjectileInitialPosition()) : null;
	}

	/**
	 * Handles the boss taking damage.
	 * The boss only takes damage if its shield is not activated.
	 */
	@Override
	public void takeDamage() {
		if (!isShielded) {
			super.takeDamage();
		}
	}

	/**
	 * Initializes the boss's move pattern.
	 * Creates a pattern of vertical moves and shuffles them.
	 */
	private void initializeMovePattern() {
		for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
			movePattern.add(VERTICAL_VELOCITY);
			movePattern.add(-VERTICAL_VELOCITY);
			movePattern.add(ZERO);
		}
		Collections.shuffle(movePattern);
	}

	/**
	 * Updates the shield's state.
	 * Activates, deactivates, or maintains the shield based on various conditions.
	 */
	private void updateShield() {
		if (isShielded) framesWithShieldActivated++;
		else if (shieldShouldBeActivated()) {
			activateShield();
			shieldImage.showShield();
		}
		if (shieldExhausted()) {
			deactivateShield();
			shieldImage.hideShield();
		}
	}

	/**
	 * Gets the next move from the move pattern.
	 * Chooses the next move, shuffles the pattern if necessary, and resets the index.
	 *
	 * @return The next vertical move from the move pattern.
	 */
	private int getNextMove() {
		int currentMove = movePattern.get(indexOfCurrentMove);
		consecutiveMovesInSameDirection++;
		if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
			Collections.shuffle(movePattern);
			consecutiveMovesInSameDirection = 0;
			indexOfCurrentMove++;
		}
		if (indexOfCurrentMove == movePattern.size()) {
			indexOfCurrentMove = 0;
		}
		return currentMove;
	}

	/**
	 * Determines if the boss fires in the current frame.
	 *
	 * @return True if the boss fires, false otherwise.
	 */
	private boolean bossFiresInCurrentFrame() {
		return Math.random() < BOSS_FIRE_RATE;
	}

	/**
	 * Gets the initial position for the boss's projectile.
	 * Calculates the initial y-position for a projectile fired by the boss.
	 *
	 * @return The initial y-position of the projectile.
	 */
	private double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}

	/**
	 * Determines if the shield should be activated.
	 *
	 * @return True if the shield should be activated, false otherwise.
	 */
	private boolean shieldShouldBeActivated() {
		return Math.random() < BOSS_SHIELD_PROBABILITY;
	}

	/**
	 * Determines if the shield is exhausted.
	 *
	 * @return True if the shield has been active for too long, false otherwise.
	 */
	private boolean shieldExhausted() {
		return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
	}

	/**
	 * Activates the boss's shield.
	 * Sets the isShielded flag to true.
	 */
	private void activateShield() {
		isShielded = true;
	}

	/**
	 * Deactivates the boss's shield.
	 * Resets the shield activation status and frame counter.
	 */
	private void deactivateShield() {
		isShielded = false;
		framesWithShieldActivated = 0;
	}

	/**
	 * Gets the boss's shield image.
	 *
	 * @return The ShieldImage associated with the boss.
	 */
	public ShieldImage getShieldImage() {
		return shieldImage;
	}
}