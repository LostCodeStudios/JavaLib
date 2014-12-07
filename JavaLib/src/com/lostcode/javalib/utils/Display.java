package com.lostcode.javalib.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Static helper class for keeping track of how the game is being scaled to fit
 * its display.
 * 
 * @author Natman64
 * @created Jul 31, 2013
 */
public final class Display {

	// region Fields/Initialization

	private static int preferredWidth;
	private static int preferredHeight;

	/**
	 * Initializes the Display helper.
	 * 
	 * @param width
	 *            The game's preferred screen width.
	 * @param height
	 *            The game's preferred screen height.
	 */
	public static void init(int width, int height) {
		preferredWidth = width;
		preferredHeight = height;
	}

	// endregion

	// region Accessors

	/**
	 * @return The game's preferred screen width.
	 */
	public static int getPreferredWidth() {
		return preferredWidth;
	}

	/**
	 * @return The game's preferred screen height.
	 */
	public static int getPreferredHeight() {
		return preferredHeight;
	}

	/**
	 * @return The actual width of the game's window.
	 */
	public static int getRealWidth() {
		return Gdx.graphics.getWidth();
	}

	/**
	 * @return The actual height of the game's window.
	 */
	public static int getRealHeight() {
		return Gdx.graphics.getHeight();
	}

	/**
	 * @return The horizontal scaling that is being applied to the game to fit
	 *         on the screen.
	 * @deprecated Scaling should be applied indirectly by rendering through a
	 *             camera with a viewport of {@link #preferredWidth},
	 *             {@link #preferredHeight}.
	 */
	@Deprecated
	public static float scaleX() {
		return Gdx.graphics.getWidth() / preferredWidth;
	}

	/**
	 * @return The vertical scaling that is being applied to the game to fit on
	 *         the screen.
	 * @deprecated Scaling should be applied indirectly by rendering through a
	 *             camera with a viewport of {@link #preferredWidth},
	 *             {@link #preferredHeight}.
	 */
	@Deprecated
	public static float scaleY() {
		return Gdx.graphics.getHeight() / preferredHeight;
	}

	/**
	 * @return The average scale.
	 * @deprecated Scaling should be applied indirectly by rendering through a
	 *             camera with a viewport of {@link #preferredWidth},
	 *             {@link #preferredHeight}.
	 */
	@Deprecated
	public static float scale() {
		return (scaleX() + scaleY()) / 2;
	}

	// endregion
	
	/**
	 * Converts a given touch input location's coordinates into screen coordinates for a camera with viewport size
	 * equal to the preferred dimensions.
	 * @param input
	 * @return An adjusted input location, in the graphical Y-Up coordinate system.
	 */
	public static Vector2 getAdjustedInput(Vector2 input) {
		float w = preferredWidth; float rW = getRealWidth();
		float h = preferredHeight; float rH = getRealHeight();
		
		float xRatio = w / rW;
		float yRatio = h / rH;
		
		return new Vector2(
				input.x * xRatio, 
				(getRealHeight() - input.y) * yRatio);
	}
	
}
