package com.punchline.javalib.entities.components;

import com.badlogic.gdx.math.Vector2;
import com.punchline.javalib.entities.Component;

/**
 * Base class for Transform components.
 * @author Nathaniel
 *
 */
public abstract class BaseTransform extends Component {

	/**
	 * @return The position of this Transform.
	 */
	public abstract Vector2 getPosition();
	
	/**
	 * Sets the position of this Transform.
	 * @param position The new position.
	 */
	public abstract void setPosition(Vector2 position);
	
	/**
	 * @return The rotation of this Transform.
	 */
	public abstract float getRotation();
	
	/**
	 * Sets the angular velocity of this Transform.
	 * @param velocity The new velocity.
	 */
	public abstract void setAngularVelocity(float velocity);
	
}
