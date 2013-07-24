/**
 * 
 */
package com.punchline.javalib.entities.components.generic;

import com.punchline.javalib.entities.Component;
import com.punchline.javalib.entities.ComponentManager;
import com.punchline.javalib.entities.Entity;

/**
 * The bullet component to which all Bullet entities owe their existence.
 * @author William
 * @created Jul 23, 2013
 */
public class Bullet implements Component {
	
	/**
	 * The damage which the bullet deals to a receiving party.
	 */
	private float damage = 0;
	
	/**
	 * The firer of the bullet.
	 */
	private Entity firer;
	
	/**
	 * Initializes a bullet with a firer and a damage amount.
	 * @param fierer The firer of the bullet.
	 * @param damage The damage of the bullet.
	 */
	public Bullet(Entity firer, float damage){
		this.damage = damage;
		this.firer = firer;
	}
	
	
	//GETTER/SETTER
	/**
	 * Gets the damage of the bullet.
	 * @return The float value of the bullet's damage.
	 */
	public float getDamage(){
		return damage;
	}
	
	/**
	 * Gets the firer of the bullet.
	 * @return The bullets firer as an Entity.
	 */
	public Entity getFirer(){
		return firer;
	}
	
	

	//COMPONENT
	/** {@inheritDoc}
	 * @see com.punchline.javalib.entities.Component#onAdd(com.punchline.javalib.entities.ComponentManager)
	 */
	@Override
	public void onAdd(ComponentManager container) {
	}

	/** {@inheritDoc}
	 * @see com.punchline.javalib.entities.Component#onRemove(com.punchline.javalib.entities.ComponentManager)
	 */
	@Override
	public void onRemove(ComponentManager container) {
	}

}
