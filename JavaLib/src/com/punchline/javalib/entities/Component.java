package com.punchline.javalib.entities;

/**
 * Base class for components.
 * @author Nathaniel
 *
 */
public abstract class Component {
	
	/**
	 * Called when this Component is added to a {@link ComponentManager}.
	 * @param container The {@link ComponentManager} this Component is added to.
	 */
	public void onAdd(ComponentManager container) { }
	
	/**
	 * Called when this Component is removed from a {@link ComponentManager}.
	 * @param container The {@link ComponentManager} this Component is removed from.
	 */
	public void onRemove(ComponentManager container) { }
	
}
