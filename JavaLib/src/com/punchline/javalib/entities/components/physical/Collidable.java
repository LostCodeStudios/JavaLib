package com.punchline.javalib.entities.components.physical;

import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.components.Component;

/**
 * Interface for any physical component type that can collide with other Collidable components.
 * @author MadcowD
 * @created Jul 24, 2013
 */
public interface Collidable extends Component {
	
	/**
	 * Called when a collision occurs with a victim
	 * @param container The entity who contains the particle/Collidable components
	 * @param victim The unfortunate victim of the collision.
	 */
	public void onCollide(Entity container, Entity victim);
	
	/**
	 * Called when a collision occurs with a victim and checks if the collision should continue.
	 * @param container The entity who contains the particle/Collidable components
	 * @param victim The unfortunate victim of the collision.
	 * @return -1 to filter, 0 to terminate, fraction to clip the ray for closest hit, 1 to continue
	 */
	public float continueCollision(Entity container, Entity victim);
}
