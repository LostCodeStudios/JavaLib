package com.punchline.javalib.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for all EntitySystems. An EntitySystem contains a predicate for determining which Entities concern it,
 * as well as a process() method for performing logic on Entities.
 * @author Nathaniel
 *
 */
public abstract class EntitySystem {
	
	private List<Entity> entities = new ArrayList<Entity>();
	private long previousTime;
	private float deltaTime;
	
	/**
	 * Checks if an Entity is part of this system's processing list.
	 * @param e The Entity to check for.
	 * @return  Whether it's in this system's processing list.
	 */
	public boolean isProcessing(Entity e) {
		return entities.contains(e);
	}
	
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
		
		long time = System.nanoTime();
		deltaTime = (float)((time - previousTime) / 1000000000.0);
		
		for (Entity e : entities) {
			process(e);
		}
		
		previousTime = time;
	}
	
	/**
	 * Processes an individual entity.
	 * @param e The entity to be processed.
	 */
	protected void process(Entity e) { }
	
	/**
	 * Gets the amount of seconds between this call of processEntities() and the previous call of processEntities().
	 * @return The delta time, in seconds.
	 */
	public float deltaSeconds() {
		return deltaTime;
	}
	
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
