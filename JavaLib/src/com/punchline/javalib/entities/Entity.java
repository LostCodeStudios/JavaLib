package com.punchline.javalib.entities;

import java.util.HashMap;
import java.util.Map;

/**
 * A game entity that contains several {@link Component Components} which define its attributes.
 * @author Nathaniel + WIlliam
 *
 */
public final class Entity extends ComponentManager {
	
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
	Map<Class<?>, Component> components;
	
	
	public Entity(String tag, String group, String type) {
		this.tag = tag;
		this.group = group;
		this.type = type;
		this.components = new HashMap<Class<?>, Component>(); //TODO: Is HashMap the best type to use for this?
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

}
