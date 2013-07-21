package com.punchline.javalib.entities;

import java.util.HashMap;
import java.util.Map;

/**
 * Holds a map of {@link Component Components} with their type identifiers, 
 * and helpful methods for accessing and manipulating them.
 * @author Nathaniel
 *
 */
public abstract class ComponentContainer {

	Map<String, Component> components = new HashMap<String, Component>(); //HashMap?
	
	/**
	 * Adds the given component to this container.
	 * @param value The component to be added.
	 */
	@SuppressWarnings("static-access")
	public void addComponent(Component value) {
		components.put(value.getID(), value);
		value.onAdd(this);
	}

	/**
	 * Removes the given component from this container.
	 * @param value The component to be removed.
	 */
	@SuppressWarnings("static-access")
	public void removeComponent(Component value) {
		value.onRemove(this);
		components.remove(value.getID());
	}
	
	/**
	 * @param typeID The desired type.
	 * @return This container's component of that type.
	 */
	public Component getComponent(String typeID) {
		return components.get(typeID);
	}
	
	/**
	 * @param typeID The desired type.
	 * @return Whether this container has a component of that type.
	 */
	public boolean hasComponent(String typeID) {
		return components.containsKey(typeID);
	}
	
}
