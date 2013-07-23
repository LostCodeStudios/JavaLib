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
	/*
	 * Initializes the converter
	 */
	public static void init(float mPRatio){
		meterPixelRatio = mPRatio;
	}
	
	
	//CONVERSION UTILS
	public static float metersToPixels(float meters){
		return meters * meterPixelRatio;
	}
}
