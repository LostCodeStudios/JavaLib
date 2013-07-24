/**
 * 
 */
package com.punchline.javalib.entities.components.physical;

import com.punchline.javalib.entities.Component;
import com.punchline.javalib.entities.Entity;

/**
 * The collidable interface for particles :)
 * @author William
 * @created Jul 24, 2013
 */
public interface Collidable extends Component {
	/**
	 * Called when a collision occurs with a victim
	 * @param container The entity who contains the particle/Collidable components
	 * @param victim The unfortunate victim of the collision.
	 * @return -1 to filter, 0 to terminate, fraction to clip the ray for closest hit, 1 to continue
	 */
	public float onCollide(Entity container, Entity victim);
}
