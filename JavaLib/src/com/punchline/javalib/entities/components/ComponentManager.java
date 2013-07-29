package com.punchline.javalib.entities.components;

import java.util.ArrayList;
import java.util.List;


/**
 * Holds a map of {@link Component Components} with their type identifiers, 
 * and helpful methods for accessing and manipulating them.
 * @author Nathaniel & William
 *
 */
public abstract class ComponentManager {

	private List<Component> components = new ArrayList<Component>();
	
	/**
	 * Adds the given component to this container.
	 * @param component The component to be added.
	 */
	public Component addComponent(Component component) {
		components.add(component);
		component.onAdd(this);
		return component;
	}
	
	/**
	 * Removes the given component from this container.
	 * @param value The component to be removed.
	 */
	public void removeComponent(Component component) {
		component.onRemove(this);
		components.remove(component);
	}
	
	/**
	 * @param type The desired type.
	 * @return This container's component of that type, or null if there is none.
	 */
	public Component getComponent(Class<? extends Component> type) {
		for (Component c : components) {
			if (type.isInstance(c))
				return c;
		}
		
		return null;
	}
	
	/**
	 * @param type The desired type.
	 * @return Whether this container has a component of that type.
	 */
	public boolean hasComponent(Class<? extends Component> type){
		return getComponent(type) != null;
	}
	
	/**
	 * Clears all of this manager's components.
	 */
	protected void clearComponents(){
		for(Component c : components)
			c.onRemove(this);
		
		components.clear();
	}
	
}
