package com.lostcode.javalib.entities.components.generic;

import com.lostcode.javalib.entities.components.Component;
import com.lostcode.javalib.entities.components.ComponentManager;
import com.lostcode.javalib.entities.templates.EntityCreationArgs;

/**
 * Base class for components that belong in an {@link Inventory} 
 * @author GenericCode
 * @Created November 11, 2013
 */

public class Item implements Component {
	
	private String type;
	private EntityCreationArgs creationArgs;
	protected float useTime;
	
	/**
	 * Constructs an Item component.
	 * 
	 * @param type
	 *            An identifying String associated with the Item.
	 */
	public Item(String type) {
		this.type = type;
	}
	
	/**
	 * Called when the Item is used. Returns whether use was successful
	 * 
	 * @param use
	 *            Tag communicating how the Item is being used.   
	 * @param args
	 *            Arguments needed for the particular use.     
	 */
	public boolean use( String use, Object... args ){
		return false;
	}
	
	/**
	 * Returns the Item's type.
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * Sets the Item's type
	 * 
	 *  @param type
	 *            The new type.
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Returns the Item's useTime.
	 */
	public float getUseTime() {
		return this.useTime;
	}
	
	/**
	 * Sets the Item's useTime.
	 * 
	 *  @param type
	 *            The new delay.
	 */
	public void setUseTime(String type) {
		this.type = type;
	}
	
	/**
	 * Returns the arguments needed to create the physical version of this Item.
	 */
	public EntityCreationArgs getCreationArgs() {
		return this.creationArgs;
	}
	
	@Override
	public void onAdd(ComponentManager container) {
	}

	@Override
	public void onRemove(ComponentManager container) {
	}

}
