package com.punchline.javalib.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Helper class containing useful conversion methods.
 * @author William & Nathaniel
 * @created Jul 23, 2013
 */
public class Convert {
	//RATIOS
	/**
	 * The ratio between meters and pixels. yPixels = metersToPixels * yMeters;
	 */
	static float meterPixelRatio;
		
	//INIT

	/**
	 * Initializes Convert class with a meter to Pixel ratio.
	 * @param mPRatio The meter to pixel ratio.
	 */
	public static void init(float mPRatio){
		meterPixelRatio = mPRatio;
	}
	
	//CONVERSION UTILS
	
	/**
	 * Converts meters to pixels.
	 * @param meters The meters to be converted to pixels.
	 * @return meters Meters in pixels.
	 */
	public static float metersToPixels(float meters){
		return meters * meterPixelRatio;
	}
	
	/**
	 * Converts meters to pixels.
	 * @param meters A Vector2 whose measurements are expressed in meters.
	 * @return An equivalent Vector2 whose measurements are expressed in pixels.
	 */
	public static Vector2 metersToPixels(Vector2 meters) {
		return new Vector2(metersToPixels(meters.x), metersToPixels(meters.y));
	}
	
	/**
	 * Converts meters to pixels.
	 * @param meters A Vector3 whose measurements are expressed in meters.
	 * @return An equivalent Vector3 whose measurements are expressed in pixels.
	 */
	public static Vector3 metersToPixels(Vector3 meters) {
		return new Vector3(metersToPixels(meters.x), metersToPixels(meters.y), metersToPixels(meters.z));
	}
	
	/**
	 * Converts pixels to meters.
	 * @param pixels The pixels to be converted to meters.
	 * @return The pixels in meters.
	 */
	public static float pixelsToMeters(float pixels) {
		return pixels / meterPixelRatio;
	}
	
	/**
	 * Converts pixels to meters.
	 * @param pixels A Vector2 whose measurements are expressed in pixels.
	 * @return An equivalent Vector2 whose measurements are expressed in meters.
	 */
	public static Vector2 pixelsToMeters(Vector2 pixels) {
		return new Vector2(pixelsToMeters(pixels.x), pixelsToMeters(pixels.y));
	}
	
	/**
	 * Converts pixels to meters.
	 * @param pixels A Vector3 whose measurements are expressed in pixels.
	 * @return An equivalent Vector3 whose measurements are expressed in meters.
	 */
	public static Vector3 pixelsToMeters(Vector3 pixels) {
		return new Vector3(pixelsToMeters(pixels.x), pixelsToMeters(pixels.y), pixelsToMeters(pixels.z));
	}
	
}
