package com.punchline.javalib.entities.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.punchline.javalib.entities.Entity;
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
	 * @param e The entity that will own this body.
	 * @param bodyDef The definition of the body to be created.
	 * @param fixtureDef The definition of the body's fixture.
	 */
	public Body(EntityWorld world, Entity e, BodyDef bodyDef, FixtureDef fixtureDef) {
		body = world.getPhysicsWorld().createBody(bodyDef);
		body.setUserData(e);
		body.createFixture(fixtureDef);
	}
	
	@Override
	public Vector2 getPosition() {
		return body.getPosition();
	}
	
	@Override
	public Vector2 getOrigin() {
		return body.getWorldCenter();
	}
	
	@Override
	public void setPosition(Vector2 position) {
		body.getPosition().set(position);
	}

	@Override
	public float getRotation() {
		return body.getAngle();
	}

	@Override
	public void setAngularVelocity(float velocity) {
		body.setAngularVelocity(velocity);
	}
	
}
