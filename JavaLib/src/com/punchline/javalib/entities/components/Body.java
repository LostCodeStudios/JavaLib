package com.punchline.javalib.entities.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.punchline.javalib.entities.EntityWorld;

/**
 * Component wrapper for a Box2D {@link com.badlogic.gdx.physics.box2d.Body Body}.
 * @author Nathaniel
 *
 */
public class Body extends BaseTransform {

	com.badlogic.gdx.physics.box2d.Body body;
	
	/**
	 * Constructs a body component.
	 * @param world The EntityWorld.
	 * @param def The definition of the body to be created.
	 */
	public Body(EntityWorld world, BodyDef def) {
		body = world.getPhysicsWorld().createBody(def);
	}
	
	@Override
	public Vector2 getPosition() {
		return body.getPosition();
	}

	@Override
	public float getRotation() {
		return body.getAngle();
	}

}
