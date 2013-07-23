package com.punchline.javalib.utils;

/**
 * Helper class for common unit conversions.
 * @author Nathaniel
 *
 */
public final class Units {

	static float pixelsPerMeter = 32f;
	
	/**
	 * @return How many pixels fit in one meter.
	 */
	public static float getPixelsPerMeter() {
		return pixelsPerMeter;
	}
	
	/**
	 * Sets how many pixels can fit in one meter. Cannot be negative, or zero.
	 * @param pixels
	 */
	public static void setPixelsPerMeter(float pixels) {
		if (pixels > 0) pixelsPerMeter = pixels;
	}
	
	/**
	 * Converts a screen measurement to a world measurement.
	 * @param pixels The amount of screen pixels.
	 * @return The equivalent amount of meters.
	 */
	public static float screenToWorld(float pixels) {
		return (float)pixels / pixelsPerMeter;
	}
	
	/**
	 * Converts a world measurement to a screen measurement.
	 * @param meters The amount of world meters.
	 * @return The equivalent amount of pixels.
	 */
	public static float worldToScreen(float meters) {
		return meters * pixelsPerMeter;
	}
	
}
