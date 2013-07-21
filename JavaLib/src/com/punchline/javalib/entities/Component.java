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
	
	/**
	 * Called when this Component is added to a {@link ComponentContainer}.
	 * @param container The {@link ComponentContainer} this Component is added to.
	 */
	public abstract void onAdd(ComponentContainer container);
	
	/**
	 * Called when this Component is removed from a {@link ComponentContainer}.
	 * @param container The {@link ComponentContainer} this Component is removed from.
	 */
	public abstract void onRemove(ComponentContainer container);
	
}
