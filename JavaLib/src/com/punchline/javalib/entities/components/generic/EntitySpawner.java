package com.punchline.javalib.entities.components.generic;

import java.util.ArrayList;
import java.util.List;

import com.punchline.javalib.entities.Component;
import com.punchline.javalib.entities.ComponentManager;

/**
 * A component for {@link Entity Entities} that spawn other Entities.
 * @author William
 * @created Jul 27, 2013
 */
public class EntitySpawner implements Component {
	
	//region Fields
	
	private boolean group;
	private String spawnTemplate;
	private List<Object> args = new ArrayList<Object>();
	
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
		for(Object arg : args)
			this.args.add(arg);
		
		this.group = group;
		this.spawnTemplate = spawnTemplate;
		this.spawnDelay = spawnDelay;
		this.elapsed = 0f;
	}

	//endregion
	
	//region Accessors
	
	/**
	 * Gets the spawn delay.
	 * @return The spawnDelay.
	 */
	public float getSpawnDelay(){
		return spawnDelay;
	}
	
	/**
	 * Gets the args for the spawnTemplate
	 * @return The args for the spawnTemplate.
	 */
	public List<Object> getArgs(){
		return args;
	}
	
	/**
	 * Gets whether or not the spawnTemplate is a group template.
	 * @return Whether or not the spawnTemplate is a group template.
	 */
	public boolean isGroupTemplate(){
		return group;
	}
	
	/**
	 * Gets the spawnTemplate string.
	 * @return The spawnTemplate string.
	 */
	public String getSpawnTemplate(){
		return spawnTemplate;
	}
	
	//endregion
	
	//region Mutators
	
	/**
	 * Sets a new spawnTemplate.
	 * @param spawnTemplate The spawnTemplate string.
	 * @param group Whether or not the template is a group template.
	 * @param args The arguments for the template.
	 */
	public void setSpawnTemplate(String spawnTemplate, boolean group, Object... args){
		this.spawnTemplate = spawnTemplate;
		this.group = group;
		this.args.clear();
		for(Object arg : args)
			this.args.add(arg);
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
