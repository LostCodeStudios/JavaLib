package com.punchline.javalib.entities.components.render;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.punchline.javalib.entities.components.Component;

/**
 * Base class for all components that can be rendered.
 * @author Natman64
 *
 */
public interface Renderable extends Component {
	
	//region Accessors
	
	/**
	 * @return The width of this Renderable component.
	 */
	public float getWidth();
	
	/**
	 * @return The height of this Renderable component.
	 */
	public float getHeight();
	
	/**
	 * @return A copy of this Renderable component's position.
	 */
	public Vector2 getPosition();
	
	/**
	 * @return This Renderable's rotation, in radians.
	 */
	public float getRotation();
	
	/**
	 * @return This Renderable's drawing layer. When drawn, this Renderable will appear below every Renderable
	 * with a greater layer value.
	 */
	public int getLayer();
	
	//endregion
	
	//region Mutators
	
	/**
	 * Sets the position where this component should be rendered.
	 * @param position
	 */
	public void setPosition(Vector2 position);
	
	/**
	 * Sets the rotation at which the component should be rendered.
	 * @param degrees Rotation in degrees.
	 */
	public void setRotation(float degrees);
	
	/**
	 * Sets the scale that should be applied when this component is drawn.
	 * @param scaleX The horizontal scale.
	 * @param scaleY the vertical scale.
	 */
	public void setScale(float scaleX, float scaleY);
	
	/**
	 * Sets the origin that should be used when rotating this component, in relation to its bottom left corner.
	 * @param origin The origin.
	 */
	public void setOrigin(Vector2 origin);
	
	/**
	 * Sets this Renderable's drawing layer.
	 * @param layer The layer this Renderable should be drawn at.
	 */
	public void setLayer(int layer);
	
	//endregion
	
	//region Rendering
	
	/**
	 * Draws this component.
	 * @param spriteBatch The SpriteBatch which will be used.
	 * @param deltaSeconds The amount of seconds since the last time this component was drawn.
	 */
	public void draw(SpriteBatch spriteBatch, float deltaSeconds);
	
	//endregion
	
}
