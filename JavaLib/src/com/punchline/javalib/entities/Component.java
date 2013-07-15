package com.punchline.javalib.entities;

/**
 * Base class for components.
 * @author Nathaniel
 *
 */
public abstract class Component {

	/**
	 * This unique string is used for identifying components of this type.
	 */
	protected static String componentID = "";
	
	/**
	 * @return This component type's unique ID.
	 */
	public static String getID() {
		return componentID;
	}
	
}
