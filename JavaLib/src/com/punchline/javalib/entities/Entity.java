package com.punchline.javalib.entities;

import java.util.HashMap;
import java.util.Map;

/**
 * A game entity that contains several {@link Component Components} which define its attributes.
 * @author Nathaniel
 *
 */
public final class Entity {
	
	/**
	 * This entity's unique tag, for identifying it individually. This must be set only once, by a template.
	 */
	String tag = "";
	
	/**
	 * The name of the group this entity belongs to. This must be set only once, by a template.
	 */
	String group = "";
	
	/**
	 * This entity's type. This must be set only once, by a template.
	 */
	String type = "";
	
	/**
	 * Contains all of this entity's components, each mapped to a String showing the type of the component.
	 */
	Map<String, Component> components;
	
	public Entity(String tag, String group, String type) {
		this.tag = tag;
		this.group = group;
		this.type = type;
		this.components = new HashMap<String, Component>(); //TODO: Is HashMap the best type to use for this?
	}
	
	/**
	 * @return This entity's unique tag.
	 */
	public String getTag() {
		return tag;
	}
	
	/**
	 * @return This entity's group name.
	 */
	public String getGroup() {
		return group;
	}
	
	/**
	 * @return This entity's type.
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Adds the given component to this entity's component map, with the key of the component's type identifier.
	 * @param value
	 */
	@SuppressWarnings("static-access")
	public void addComponent(Component value) {
		components.put(value.getID(), value);
	}
	
	/**
	 * Removes the given component from this entity's component map.
	 * @param value
	 */
	@SuppressWarnings("static-access")
	public void removeComponent(Component value) {
		components.remove(value.getID());
	}
	
	/**
	 * @param key The type identifier for the desired component.
	 * @return The desired component.
	 */
	public Component getComponent(String key) {
		return components.get(key);
	}
	
}
