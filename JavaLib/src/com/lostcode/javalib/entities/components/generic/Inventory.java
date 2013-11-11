package com.lostcode.javalib.entities.components.generic;

import com.badlogic.gdx.utils.Array;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.MultiComponent;

/**
 * Component to manage a set of {@link Item}s within an inventory 
 * @author GenericCode
 * @Created November 11, 2013
 */

public class Inventory extends MultiComponent<Item> {

	private Entity owner;
	private EntityWorld world;
	private float max;
	
	/**
	 * Constructs an Inventory component.
	 * 
	 * @param owner
	 *            The Entity that owns this component.
	 * @param world
	 *            The EntityWorld that owns Entity owner.
	 * @param max
	 *            The maximum number of items contained within the inventory.
	 */
	public Inventory(Item base, Array<Item> children, Entity owner, EntityWorld world, float max) {
		super(base, children);
		
		this.owner = owner;
		this.world = world;
		this.max = max;
	}
	
	/**
	 * Constructs an Inventory component.
	 * 
	 * @param owner
	 *            The Entity that owns this component.
	 * @param world
	 *            The EntityWorld that owns Entity owner.
	 * @param max
	 *            The maximum number of items contained within the inventory.
	 * @param initial
	 *            The initial contents of the inventory.
	 */
	public Inventory(Item base, Array<Item> children, Entity owner, EntityWorld world, float max, Array<Item> initial) {
		super(base, children);
		
		this.owner = owner;
		this.world = world;
		this.max = max;
		
		for (Item item : initial)
			this.children.add(item);
	}
	
	/**
	 * Adds a given Item component to the Inventory.
	 * Returns true if inventory has space and item is added, otherwise returns false
	 * 
	 * @param item
	 *            The item to be added.
	 */
	public boolean add(Item item) {
		if( this.children.size == this.max ) {
			this.children.add(item);
			return true;
		}
				
		return false;
	}
	
	/**
	 * Removes a given Item component from the Inventory.
	 * Returns true if item was successfully removed, otherwise returns false
	 * 
	 * @param item
	 *            The item to be removed.
	 */
	public boolean remove(Item item){
		return this.children.removeValue(item, true);
	}

}
