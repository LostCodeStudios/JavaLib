package com.punchline.javalib.entities;

/**
 * Base interface for components.
 * @author Nathaniel
 *
 */
public interface Component {
	
	/**
	 * Called when this Component is added to a {@link ComponentManager}.
	 * @param container The {@link ComponentManager} this Component is added to.
	 */
	void onAdd(ComponentManager container);
	
	/**
	 * Called when this Component is removed from a {@link ComponentManager}.
	 * @param container The {@link ComponentManager} this Component is removed from.
	 */
	void onRemove(ComponentManager container);
	
}
