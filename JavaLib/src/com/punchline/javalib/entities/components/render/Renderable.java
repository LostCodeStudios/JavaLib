package com.punchline.javalib.entities.components.render;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.punchline.javalib.entities.Component;

/**
 * Base class for all components that can be rendered.
 * @author Nathaniel
 *
 */
public abstract class Renderable extends Component {
	
	/**
	 * Sets the position where this component should be rendered.
	 * @param position
	 */
	public abstract void setPosition(Vector2 position);
	
	/**
	 * Sets the rotation at which the component should be rendered.
	 * @param degrees
	 */
	public abstract void setRotation(float degrees);
	
	/**
	 * Sets the scale that should be applied when this component is drawn.
	 * @param scale
	 */
	public abstract void setScale(float scale);
	
	/**
	 * Sets the origin that should be used when rotating this component, in relation to its bottom left corner.
	 * @param origin The origin.
	 */
	public abstract void setOrigin(Vector2 origin);
	
	/**
	 * Draws this component.
	 * @param spriteBatch The SpriteBatch which will be used.
	 * @param deltaSeconds The amount of seconds since the last time this component was drawn.
	 */
	public abstract void draw(SpriteBatch spriteBatch, float deltaSeconds);
	
}
