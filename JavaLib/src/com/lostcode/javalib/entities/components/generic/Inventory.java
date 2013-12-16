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

@SuppressWarnings("unused")
public class Inventory extends MultiComponent<Item> {

	private Entity owner;
	private EntityWorld world;
	private int max;
	private Item selected;
	
	/**
	 * Constructs an empty Inventory component.
	 * 
	 * @param owner
	 *            The Entity that owns this component.
	 * @param world
	 *            The EntityWorld that owns Entity owner.
	 * @param max
	 *            The maximum number of items contained within the inventory.
	 */
	public Inventory( Entity owner, EntityWorld world, int max ) {
		super(new Item(null), new Item[0]);
		
		this.owner = owner;
		this.world = world;
		this.max = max;
	}
	
	/**
	 * Constructs an Inventory component with initial contents.
	 * 
	 * @param owner
	 *            The Entity that owns this component.
	 * @param world
	 *            The EntityWorld that owns Entity owner.
	 * @param max
	 *            The maximum number of items contained within the inventory.
	 * @param initial
	 *            The initial contents of the inventory. If the size of initial is greater than max, max automatically becomes the size of initial.
	 */
	public Inventory(Entity owner, EntityWorld world, int max, Array<Item> initial) {
		super(new Item(null), initial);
		
		this.owner = owner;
		this.world = world;
		this.max = max;
		if( max < initial.size )
			max = initial.size;
	}
	
	/**
	 * Adds a given Item component to the Inventory.
	 * Returns true if inventory has space and item is added, otherwise returns false
	 * 
	 * @param item
	 *            The item to be added.
	 */
	public boolean add(Item item) {
		if( this.children.size < max ) {
			this.children.add(item);
			return true;
		}
				
		return false;
	}
	
	/**
	 * Returns the Item currently selected in the inventory, null if item doesn't exist.
	 */
	public Item getSelected(){
		return selected;
	}
	
	/**
	 * Returns the index of the Item currently selected by the inventory.
	 */
	public int getSelectedIndex(){
		return children.lastIndexOf(selected, true);
	}
	
	/**
	 * Selects the Item at the given index
	 * 
	 * @param index
	 *            The index of the item to be selected.
	 */
	public void select(int index){
		if( index <= children.size-1 )
			selected = children.get(index);
	}
	
	/**
	 * Selects the given Item
	 * 
	 * @param item
	 *            The index of the item to be selected.
	 */
	public void select(Item item){
		if( children.contains(item, true))
			selected = item;
	}
	
	/**
	 * Drops the Item at the given index as it's own entity
	 * 
	 * @param index
	 *            The index of the item to be selected.
	 */
	public void drop(int index) {
		if( index <= children.size-1 ) {
			world.create( children.get(index).getCreationArgs() );
			children.removeIndex(index);
		}
	}
	
	/**
	 * Drops the given Item as it's own entity
	 * 
	 * @param item
	 *            The index of the item to be selected.
	 */
	public void drop(Item item) {
		if( children.contains(item, true)) {
			world.create(item.getCreationArgs());
			removeChild(item);
		}
	}
	
	/**
	 * Returns an Array containing the contents of the Inventory
	 */
	public Array<Item> getItems(){
		return this.children;
	}
}
