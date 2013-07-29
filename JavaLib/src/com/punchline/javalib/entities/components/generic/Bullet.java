package com.punchline.javalib.entities.components.generic;

import com.punchline.javalib.entities.Component;
import com.punchline.javalib.entities.ComponentManager;
import com.punchline.javalib.entities.Entity;

/**
 * Generic component for bullet Entities.
 * @author William
 * @created Jul 23, 2013
 */
public class Bullet implements Component {
	
	//region Fields/Initialization
	
	private float damage = 0;
	private Entity firer;
	
	/**
	 * Initializes a bullet with a firer and a damage amount.
	 * @param firer The firer of the bullet.
	 * @param damage The damage that the bullet will inflict on enemies.
	 */
	public Bullet(Entity firer, float damage){
		this.damage = damage;
		this.firer = firer;
	}
	
	//endregion
	
	//region Accessors
	
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
	
	//endregion

	//region Events
	
	@Override
	public void onAdd(ComponentManager container) { }

	@Override
	public void onRemove(ComponentManager container) { }

	//endregion
	
}
