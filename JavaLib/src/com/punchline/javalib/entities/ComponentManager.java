package com.punchline.javalib.entities;

import java.util.HashMap;
import java.util.Map;

/**
 * Holds a map of {@link Component Components} with their type identifiers, 
 * and helpful methods for accessing and manipulating them.
 * @author Nathaniel
 *
 */
public abstract class ComponentManager {

	private Map<Class<?>, Component> components = new HashMap<Class<?>, Component>(); //HashMap?
	
	/**
	 * Adds the given component to this container.
	 * @param value The component to be added.
	 */
	public void addComponent(Component value) {
		components.put(value.getClass(), value);
		value.onAdd(this);
	}

	/**
	 * Removes the given component from this container.
	 * @param value The component to be removed.
	 */
	public void removeComponent(Component value) {
		value.onRemove(this);
		components.remove(value.getClass());
	}
	
	/**
	 * @param type The desired type.
	 * @return This container's component of that type.
	 */
	@SuppressWarnings("unchecked")
	public <T extends Component> T getComponent(T... type) {
		return (T)components.get((Class<T>) type.getClass().getComponentType());
	}
	
	/**
	 * @param type The desired type.
	 * @return Whether this container has a component of that type.
	 */
	@SuppressWarnings("unchecked")
	public <T extends Component> boolean hasComponent(T... type) {
		return components.containsKey((Class<T>) type.getClass().getComponentType());
	}
	
}
