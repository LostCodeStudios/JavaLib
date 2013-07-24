/**
 * 
 */
package com.punchline.javalib.entities.components.generic;

import com.punchline.javalib.entities.ComponentManager;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.Stat;

/**
 * A component describing an Entity's health. When health becomes 0, the Entity will be deleted.
 * @author William
 * @created Jul 23, 2013
 */
public class Health extends Stat {
	
	/**
	 * Callback for when an Entity dies.
	 * @author Nathaniel
	 * @created Jul 24, 2013
	 */
	public interface OnDeathCallback {
		
		/**
		 * Called when the owner of this component runs out of health.
		 * @param owner The owner of this component.
		 * @param world The world that Entity owner belongs to.
		 */
		public void onDeath(Entity owner, EntityWorld world);
		
	}
	
	private Entity owner;
	private EntityWorld world;
	
	/**
	 * Constructs a Health component.
	 * @param owner The Entity that owns this component.
	 * @param world The EntityWorld that owns Entity owner.
	 * @param max The max amount of health.
	 */
	public Health(Entity owner, EntityWorld world, float max) {
		super(max);
		
		this.owner = owner;
		this.world = world;
	}
	
	@Override
	public void drain(double amount) {
		super.drain(amount);
		
		if (isEmpty()) { //Dead
			
			if (onDeath != null)
				onDeath.onDeath(owner, world);
			
		}
	}

	/**
	 * Callback for when the Entity that owns this component dies.
	 */
	public OnDeathCallback onDeath;

	@Override
	public void onAdd(ComponentManager container) { }

	@Override
	public void onRemove(ComponentManager container) { }
	
}
