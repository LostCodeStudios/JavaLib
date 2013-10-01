package com.punchline.javalib.entities.components.generic;

import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.ComponentManager;
import com.punchline.javalib.entities.components.abstracted.Stat;

/**
 * A component describing any kind of cooldown.
 * @author GenericCode
 * @created September 25, 2013
 *
 */

public class Cooldown extends Stat {
	
	private EntityWorld world;
	
	/**
	 * Constructs a Cooldown component.
	 * @param world
	 * @param time The cooldown's time.
	 */
	public Cooldown(EntityWorld world, float time){
		super(time);
		
		this.world = world;
	}
	
	/**
	 * @return Whether the cooldown is complete.
	 */
	public boolean isFinished() {
		return isEmpty();
	}
	
	/**
	 * Restarts the cooldown timer.
	 */
	public void restart() {
		fillMax();
	}
	
	@Override
	public void onAdd(ComponentManager container) { 
		setRegenerationRate(world, -1); //Starts the regeneration process.
	}

	@Override
	public void onRemove(ComponentManager container) { 
		setRegenerationRate(world, 0); //Aborts the regeneration process.
	}

}
