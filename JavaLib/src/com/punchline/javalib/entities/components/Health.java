/**
 * 
 */
package com.punchline.javalib.entities.components;

import com.punchline.javalib.entities.Component;
import com.punchline.javalib.entities.ComponentManager;

/**
 * The JavaLib health component.
 * @author William
 * @created Jul 23, 2013
 */
public class Health implements Component {
	
	/**
	 * The health of a entity.
	 */
	private float health = 1f;
	
	/**
	 * Initializes the health of an entity.
	 */
	public Health(float health) {
		this.health = health;
	}
	
	/**
	 * Gets the health.
	 * @return The health of an entity.
	 */
	public float getHealth(){
		return health;
	}
	
	/**
	 * Sets the health.
	 * @param newHealth The new health value.
	 */
	public void setHealth(float newHealth){
		health = newHealth;
		//TODO: add event handlers. (onDeath and onDamage);
	}
	
	/**
	 * Provides details as to the whereabouts of an entity's life force.
	 * @return (health > 0)
	 */
	public boolean isAlive(){
		return (health >0 );
	}

	
	
	
	//COMPONENT STUFF
	/** {@inheritDoc}
	 * @see com.punchline.javalib.entities.Component#onAdd(com.punchline.javalib.entities.ComponentManager)
	 */
	@Override
	public void onAdd(ComponentManager container) {
		// TODO Auto-generated method stub

	}

	/** {@inheritDoc}
	 * @see com.punchline.javalib.entities.Component#onRemove(com.punchline.javalib.entities.ComponentManager)
	 */
	@Override
	public void onRemove(ComponentManager container) {
		// TODO Auto-generated method stub

	}

}
