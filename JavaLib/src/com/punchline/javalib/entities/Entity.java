package com.punchline.javalib.entities;

/**
 * A game entity that contains several components which define its attributes.
 * @author Nathaniel
 *
 */
public final class Entity {

	/**
	 * This entity's unique tag, for identifying it individually. This must be set only once, by a template.
	 */
	String tag;
	
	/**
	 * The name of the group this entity belongs to. This must be set only once, by a template.
	 */
	String group;
	
	/**
	 * This entity's type. This must be set only once, by a template.
	 */
	String type;
	
	
	
}
