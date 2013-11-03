package com.lostcode.javalib.entities.components.physical;

import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.components.MultiComponent;

/**
 * MultiComponent implementation for Box2D body components.
 * 
 * @author Natman64
 * 
 */
public class MultiBody extends MultiComponent<Body> implements Transform,
		Velocity {

	// region Initialization

	/**
	 * Constructs a MultiBody.
	 * 
	 * @param base
	 *            The main body.
	 * @param children
	 *            The rest of the bodies.
	 */
	public MultiBody(Body base, Body... children) {
		super(base, children);
	}

	// endregion

	// region Velocity Implementation

	@Override
	public Vector2 getLinearVelocity() {
		return base.getLinearVelocity();
	}

	@Override
	public void setLinearVelocity(Vector2 linearVelocity) {
		for (Body child : children) {
			child.setLinearVelocity(linearVelocity);
		}
	}

	@Override
	public float getAngularVelocity() {
		return base.getAngularVelocity();
	}

	@Override
	public void setAngularVelocity(float angularVelocity) {
		for (Body child : children) {
			child.setAngularVelocity(angularVelocity);
		}
	}

	// endregion

	// region Transform Implementation

	@Override
	public Vector2 getPosition() {
		return base.getPosition();
	}

	@Override
	public void setPosition(Vector2 position) {
		Vector2 offset = position.cpy().sub(base.getPosition());

		for (Body child : children) {
			child.setPosition(child.getPosition().add(offset));
		}
	}

	@Override
	public float getRotation() {
		return base.getRotation();
	}

	@Override
	public void setRotation(float rotation) {
		for (Body child : children) {
			child.setRotation(rotation);
		}
	}

	@Override
	public Vector2 getOrigin() {
		return base.getOrigin();
	}

	// endregion

}
