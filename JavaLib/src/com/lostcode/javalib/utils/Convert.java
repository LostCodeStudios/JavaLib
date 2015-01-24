package com.lostcode.javalib.utils;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Helper class containing useful conversion methods.
 * 
 * @author MadcowD
 * @author Natman64
 * @created Jul 23, 2013
 */
public final class Convert {

	// region Fields/Initialization

	/**
	 * The ratio between meters and pixels.
	 */
	private static float pixelMeterRatio;

	/**
	 * Initializes Convert class with a meter to Pixel ratio.
	 * 
	 * @param mPRatio
	 *            The pixel to meter ratio.
	 */
	public static void init(float pMRatio) {
		pixelMeterRatio = pMRatio;
	}

	// endregion

	// region Ratios

	/**
	 * @return The pixel-meter ratio being used to convert.
	 */
	public static float getPixelMeterRatio() {
		return pixelMeterRatio;
	}

	/**
	 * @return The meter-pixel ratio being used to convert.
	 */
	public static float getMeterPixelRatio() {
		return 1 / pixelMeterRatio;
	}

	// endregion

	// region Meters to Pixels

	/**
	 * Converts meters to pixels.
	 * 
	 * @param meters
	 *            The meters to be converted to pixels.
	 * @return meters Meters in pixels.
	 */
	public static float metersToPixels(float meters) {
		return meters * pixelMeterRatio;
	}

	/**
	 * Converts meters to pixels.
	 * 
	 * @param meters
	 *            A Vector2 whose measurements are expressed in meters.
	 * @return An equivalent Vector2 whose measurements are expressed in pixels.
	 */
	public static Vector2 metersToPixels(Vector2 meters) {
		return new Vector2(metersToPixels(meters.x), metersToPixels(meters.y));
	}

	/**
	 * Converts meters to pixels.
	 * 
	 * @param meters
	 *            A Vector3 whose measurements are expressed in meters.
	 * @return An equivalent Vector3 whose measurements are expressed in pixels.
	 */
	public static Vector3 metersToPixels(Vector3 meters) {
		return new Vector3(metersToPixels(meters.x), metersToPixels(meters.y),
				metersToPixels(meters.z));
	}

	/**
	 * Converts meters to pixels.
	 * 
	 * @param meters
	 *            A Rectangle whose location and measurements are expressed in
	 *            meters.
	 * @return An equivalent Rectangle whose measurements are expressed in
	 *         pixels.
	 */
	public static Rectangle metersToPixels(Rectangle meters) {
		return new Rectangle(metersToPixels(meters.x),
				metersToPixels(meters.y), metersToPixels(meters.width),
				metersToPixels(meters.height));
	}

	// endregion

	// region Pixels to meters.

	/**
	 * Converts pixels to meters.
	 * 
	 * @param pixels
	 *            The pixels to be converted to meters.
	 * @return The pixels in meters.
	 */
	public static float pixelsToMeters(float pixels) {
		return pixels / pixelMeterRatio;
	}

	/**
	 * Converts pixels to meters.
	 * 
	 * @param pixels
	 *            A Vector2 whose measurements are expressed in pixels.
	 * @return An equivalent Vector2 whose measurements are expressed in meters.
	 */
	public static Vector2 pixelsToMeters(Vector2 pixels) {
		return new Vector2(pixelsToMeters(pixels.x), pixelsToMeters(pixels.y));
	}

	/**
	 * Converts pixels to meters.
	 * 
	 * @param pixels
	 *            A Vector3 whose measurements are expressed in pixels.
	 * @return An equivalent Vector3 whose measurements are expressed in meters.
	 */
	public static Vector3 pixelsToMeters(Vector3 pixels) {
		return new Vector3(pixelsToMeters(pixels.x), pixelsToMeters(pixels.y),
				pixelsToMeters(pixels.z));
	}

	/**
	 * Converts pixels to meters.
	 * 
	 * @param pixels
	 *            A rectangle whose location and measurements are expressed in
	 *            pixels.
	 * @return An equivalent Rectangle whose measurements are expressed in
	 *         meters.
	 */
	public static Rectangle pixelsToMeters(Rectangle pixels) {
		return new Rectangle(pixelsToMeters(pixels.x),
				pixelsToMeters(pixels.y), pixelsToMeters(pixels.width),
				pixelsToMeters(pixels.height));
	}

	// endregion

}
