package com.punchline.javalib.entities.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;

/**
 * Base class for all EntitySystems. An EntitySystem contains a predicate for determining which Entities concern it,
 * as well as a process() method for performing logic on Entities.
 * @author Natman64
 *
 */
public abstract class EntitySystem implements Disposable {
	
	//region Fields
	
	/**
	 * This system's processing list. Use direct access sparingly.
	 */
	protected Array<Entity> entities = new Array<Entity>();
	
	private long previousTime;
	private float processTime;
	
	private float interval = 0f;
	private float elapsed = 0f;
	
	/**
	 * The {@link EntityWorld} in which this system is contained.
	 */
	protected EntityWorld world;
	
	/**
	 * If the processing list has been modified since the last call to processEntities(), this value is true.
	 */
	protected boolean processingListChanged;
	
	//endregion
	
	//region Initialization
	
	/**
	 * Constructs an EntitySystem.
	 */
	public EntitySystem() { 
	}
	
	/**
	 * Constructs an EntitySystem with a required interval between calls of its processEntities() method.
	 * @param world The {@link EntityWorld} in which this system is contained.
	 * @param interval The seconds required between calls.
	 */
	public EntitySystem(float interval) {
		this.interval = interval;
	}
	
	//endregion
	
	//region Accessors
	
	/**
	 * @return The amount of seconds that need to pass between calls of processEntities().
	 */
	public float getInterval() {
		return interval;
	}
	
	/**
	 * @return The amount of seconds that have passed since the last call of processEntities().
	 */
	public float getElapsedInterval() {
		return elapsed;
	}
	
	//endregion
	
	//region Mutators
	
	/**
	 * Sets this system's EntityWorld.
	 * @param world The EntityWorld containing this system.
	 */
	public void setWorld(EntityWorld world) {
		this.world = world;
	}
	
	//endregion
	
	//region Interval Processing
	
	/**
	 * Adds to the time elapsed since the last call of processEntities().
	 * @param elapsed The seconds elapsed.
	 */
	public void addElapsedInterval(float elapsed) {
		this.elapsed += elapsed;
	}
	
	/**
	 * Resets the timer for this system's interval.
	 */
	public void resetElapsedInterval() {
		elapsed -= interval; //Subtract interval to account for extra time.
	}
	
	//endregion
	
	//region Entity Processing
	
	/**
	 * Processes the given list of entities.
	 * @param entities A list of all desired entities.
	 */
	public void processEntities() {
		
		long time = System.nanoTime();
		
		for (Entity e : entities) {
			process(e);
		}
		
		previousTime = time;
		time = System.nanoTime();
		
		processTime = (float)((time - previousTime) / 1000.0);
		
		processingListChanged = false;
	}
	
	/**
	 * Processes an individual entity.
	 * @param e The entity to be processed.
	 */
	protected abstract void process(Entity e);
	
	//endregion
	
	//region Events
	
	/**
	 * Called when the game application is paused.
	 */
	public void pause() { }
	
	/**
	 * Called when the game application is resumed.
	 */
	public void resume() { }
	
	//endregion
	
	//region Time Values
	
	/**
	 * @return The amount of seconds between this call of processEntities() and the previous call of processEntities().
	 */
	public float deltaSeconds() {
		return Gdx.graphics.getDeltaTime() * world.getTimeCoefficient();
	}
	
	/**
	 * @return The amount of seconds this system took during its last call of processEntities().
	 */
	public float processTime() {
		return processTime;
	}
	
	//endregion

	//region Entity Management
	
	/**
	 * Checks if an Entity is part of this system's processing list.
	 * @param e The Entity to check for.
	 * @return  Whether it's in this system's processing list.
	 */
	public boolean isProcessing(Entity e) {
		return entities.contains(e, true);
	}
	
	/**
	 * Predicate for determining if an {@link Entity} needs to be sent to this system for processing.
	 * @param e The {@link Entity} to be checked.
	 * @return Whether this system needs to process the given {@link Entity}.
	 */
	public abstract boolean canProcess(Entity e);
	
	/**
	 * Adds the given entity to the processing list.
	 * @param e The entity to add.
	 */
	public void add(Entity e) {
		entities.add(e);
		onAdded(e);
		
		processingListChanged = true;
	}
	
	/**
	 * Removes the given entity from the processing list.
	 * @param e The entity to remove.
	 */
	public void remove(Entity e) {
		entities.removeValue(e, true);
		onRemoved(e);
		
		processingListChanged = true;
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
	 * Called when an Entity is removed from the system's processing list.
	 * @param e The removed entity.
	 */
	protected void onRemoved(Entity e) { }
	
	//endregion

}
