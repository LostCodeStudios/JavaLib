package com.punchline.javalib.entities;

/**
 * Base class for components.
 * @author Nathaniel
 *
 */
public abstract class Component {
	
	/**
	 * Called when this Component is added to a {@link ComponentContainer}.
	 * @param container The {@link ComponentContainer} this Component is added to.
	 */
	public void onAdd(ComponentContainer container) { }
	
	/**
	 * Called when this Component is removed from a {@link ComponentContainer}.
	 * @param container The {@link ComponentContainer} this Component is removed from.
	 */
	public void onRemove(ComponentContainer container) { }
	
}
