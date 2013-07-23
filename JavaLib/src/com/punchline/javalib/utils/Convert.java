package com.punchline.javalib.utils;

public class Convert {
	//RATIOS
	/**
	 * The ratio between meters and pixels. yPixels = metersToPixels * yMeters;
	 */
	static float meterPixelRatio;
	
	/**
	 * The ration between radians and degrees
	 */
	static final float radianDegreeRatio = 180/(float)Math.PI;
	
	
	
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
	 * Converts pixels to meters by devision.
	 * @param pixels The pixels to be converted to meters.
	 * @return The pixels in meters.
	 */
	public static float pixelsToMeters(float pixels){
		return pixels / meterPixelRatio;
	}
}
