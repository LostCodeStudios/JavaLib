package com.punchline.javalib.entities.components.physical;

import com.badlogic.gdx.math.Vector2;
import com.punchline.javalib.entities.components.Component;

/**
 * @author MadcowD
 * @created Jul 23, 2013
 */
public interface Transform extends Component{
	
	/**
	 * @return The position of this Transform.
	 */
	public Vector2 getPosition();
	
	/**
	 * Sets the position of this Transform.
	 * @param position The new position.
	 * @return Returns The new position for chaining.
	 */
	public void setPosition(Vector2 position);
	
	/**
	 * Gets the rotation of this Transform.
	 * @return The rotation of this Transform.
	 */
	public float getRotation();
	
	/**
	 * Sets the rotation  of a transform.
	 * @param rotation The new rotation.
	 * @return The new rotation.
	 */
	public void setRotation(float rotation);
	
	/**
	 * @return The center of this Transform.
	 */
	public Vector2 getOrigin();
	
}
