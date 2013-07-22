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
	 * @return The rotation of this Transform.
	 */
	public abstract float getRotation();
	
}
