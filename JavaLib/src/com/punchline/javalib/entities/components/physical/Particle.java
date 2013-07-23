/**
 * 
 */
package com.punchline.javalib.entities.components.physical;

import com.badlogic.gdx.math.Vector2;
import com.punchline.javalib.entities.ComponentManager;

/**
 * @author William
 * @created Jul 23, 2013
 */
public class Particle implements Velocity, Transform {

	/**
	 * {@inheritDoc}
	 * @see com.punchline.javalib.entities.Component#onAdd(com.punchline.javalib.entities.ComponentManager)
	 */
	@Override
	public void onAdd(ComponentManager container) {
		// TODO Auto-generated method stub

	}

	/** 
	 * {@inheritDoc}
	 * @see com.punchline.javalib.entities.Component#onRemove(com.punchline.javalib.entities.ComponentManager)
	 */
	@Override
	public void onRemove(ComponentManager container) {
		// TODO Auto-generated method stub

	}

	/** 
	 * {@inheritDoc}
	 * @see com.punchline.javalib.entities.components.physical.Transform#getPosition()
	 */
	@Override
	public Vector2 getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	/** 
	 * {@inheritDoc}
	 * @see com.punchline.javalib.entities.components.physical.Transform#setPosition(com.badlogic.gdx.math.Vector2)
	 */
	@Override
	public Vector2 setPosition(Vector2 position) {
		// TODO Auto-generated method stub
		return null;
	}

	/** 
	 * {@inheritDoc}
	 * @see com.punchline.javalib.entities.components.physical.Transform#getRotation()
	 */
	@Override
	public float getRotation() {
		// TODO Auto-generated method stub
		return 0;
	}

	/** 
	 * {@inheritDoc}
	 * @see com.punchline.javalib.entities.components.physical.Transform#setRotation(float)
	 */
	@Override
	public float setRotation(float rotation) {
		// TODO Auto-generated method stub
		return 0;
	}

	/** 
	 * {@inheritDoc}
	 * @see com.punchline.javalib.entities.components.physical.Transform#getOrigin()
	 */
	@Override
	public Vector2 getOrigin() {
		// TODO Auto-generated method stub
		return null;
	}

}
