package com.punchline.javalib.entities;

import java.util.ArrayList;
import java.util.List;

public abstract class EntitySystem {
	
	private List<Entity> entities = new ArrayList<Entity>();
	
	/**
	 * Predicate for determining if an {@link Entity} needs to be sent to this system for processing.
	 * @param e The {@link Entity} to be checked.
	 * @return Whether this system needs to process the given {@link Entity}.
	 */
	public abstract boolean canProcess(Entity e);
	
	/**
	 * Processes the given list of entities.
	 * @param entities A list of all desired entities.
	 */
	public void processEntities() {
		for (Entity e : entities) {
			process(e);
		}
	}
	
	/**
	 * Processes an individual entity.
	 * @param e The entity to be processed.
	 */
	protected void process(Entity e) { }
	
	/**
	 * Adds the given entity to the processing list.
	 * @param e The entity to add.
	 */
	public void add(Entity e) {
		entities.add(e);
		onAdded(e);
	}
	
	/**
	 * Called when an Entity is added to the system's processing list.
	 * @param e The added Entity.
	 */
	protected void onAdded(Entity e) { }
	
	public void changed(Entity e) {
		if (entities.contains(e) && !canProcess(e)) {
			remove(e); return;
		}
		
		onChanged(e);
	}
	
	/**
	 * Called when one of this system's Entities is changed.
	 * @param e The changed entity.
	 */
	public void onChanged(Entity e) { }
	
	/**
	 * Removes the given entity from the processing list.
	 * @param e The entity to remove.
	 */
	public void remove(Entity e) {
		entities.remove(e);
		onRemoved(e);
	}
	
	/**
	 * Called when an Entity is removed from the system's processing list.
	 * @param e The removed entity.
	 */
	protected void onRemoved(Entity e) { }
	
}
