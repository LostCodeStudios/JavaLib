package com.punchline.javalib.entities.components.generic;

import com.punchline.javalib.entities.components.Component;
import com.punchline.javalib.entities.components.ComponentManager;
import com.punchline.javalib.entities.templates.EntityCreationArgs;

/**
 * A component for {@link Entity Entities} that spawn other Entities.
 * @author William
 * @created Jul 27, 2013
 */
public class EntitySpawner implements Component {
	
	//region Fields
	
	private EntityCreationArgs creationArgs;
	
	private float spawnDelay;
	private float elapsed;
	
	//endregion
	
	//region Initialization
	
	/**
	 * Constructs and EntitySpawner component.
	 * @param spawnTemplate The tag of the entity template that will be used to
	 * spawn children entities.
	 * @param group Whether the template is a group template.
	 * @param spawnDelay The delay/rate at which entities are created from the
	 * template.
	 * @param args The arguments for the template.
	 */
	public EntitySpawner(String spawnTemplate, boolean group, float spawnDelay, Object... args) {		
		this.creationArgs = new EntityCreationArgs(spawnTemplate, group, args);
		
		this.spawnDelay = spawnDelay;
		this.elapsed = 0f;
	}

	//endregion
	
	//region Accessors
	
	/**
	 * @return The spawnDelay.
	 */
	public float getSpawnDelay(){
		return spawnDelay;
	}
	
	/**
	 * @return The creation args used to make entities.
	 */
	public EntityCreationArgs getCreationArgs() {
		return creationArgs;
	}
	
	//endregion
	
	//region Mutators
	
	/**
	 * Sets the EntityCreationArgs used when spawning entities.
	 * @param creationArgs The new creation args.
	 */
	public void setCreationArgs(EntityCreationArgs creationArgs) {
		this.creationArgs = creationArgs;
	}
	
	/**
	 * Sets the spawn delay.
	 * @param delay The new spawnDelay.
	 */
	public void setSpawnDelay(float delay){
		spawnDelay = delay;
	}
	
	//endregion
	
	//region Helpers
	
	/**
	 * Checks if the EntitySpawner allows a spawn.
	 * @param delta The world-delta time in seconds.
	 * @return Whether or not an entity should be spawned.
	 */
	public boolean spawn(float delta){
		elapsed += delta;
		
		if(elapsed >= spawnDelay)
		{
			elapsed = 0f;
			return true;
		}
		
		return false;
	}
	
	//endregion
	
	//region Events
	
	@Override
	public void onAdd(ComponentManager container) { }

	@Override
	public void onRemove(ComponentManager container) { }

	//endregion
	
}
