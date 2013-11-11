package com.lostcode.javalib.entities.components.generic;

import com.lostcode.javalib.entities.components.Component;
import com.lostcode.javalib.entities.components.ComponentManager;

/**
 * Base class for components that belong in an {@link Inventory} 
 * @author GenericCode
 * @Created November 11, 2013
 */

public class Item implements Component {
	
	private String type;
	
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
	
	@Override
	public void onAdd(ComponentManager container) {
	}

	@Override
	public void onRemove(ComponentManager container) {
	}

}
