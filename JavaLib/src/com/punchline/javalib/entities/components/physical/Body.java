package com.punchline.javalib.entities.components.physical;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.punchline.javalib.entities.ComponentManager;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;

/**
 * Component wrapper for a Box2D {@link com.badlogic.gdx.physics.box2d.Body Body}.
 * @author Nathaniel
 *
 */
public class Body implements Transform, Velocity {

	private EntityWorld entityWorld;
	private com.badlogic.gdx.physics.box2d.Body body;
	
	/**
	 * Constructs a body component.
	 * @param world The EntityWorld.
	 * @param e The entity that will own this body.
	 * @param bodyDef The definition of the body to be created.
	 * @param fixtureDef The definition of the body's fixture.
	 */
	public Body(EntityWorld world, Entity e, BodyDef bodyDef, FixtureDef fixtureDef) {
		this(world,e,bodyDef);
		body.createFixture(fixtureDef);
	}
	
	/**
	 * Constructs a body component without a fixture definition.
	 * @param world The EntityWorld.
	 * @param e The entity that will own this body.
	 * @param bodyDef The definition of the body to be created.
	 */
	public Body(EntityWorld world, Entity e, BodyDef bd){
		entityWorld = world;
		body = world.getPhysicsWorld().createBody(bd);
		body.setUserData(e);
	}
	
	/**
	 * @return The Box2D body.
	 */
	public com.badlogic.gdx.physics.box2d.Body getBody(){
		return body;
	}
	
	//TRANSFORM
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vector2 getPosition() {
		return body.getPosition();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vector2 setPosition(Vector2 position) {
		body.setTransform(position, body.getAngle());
		return body.getPosition();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public float getRotation() {
		return body.getAngle();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public float setRotation(float rotation){
		body.setTransform(getPosition(), rotation);
		return getRotation();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vector2 getOrigin() {
			return body.getWorldCenter();
	}
	
	//VELOCITY
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vector2 getLinearVelocity() {
		return body.getLinearVelocity();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLinearVelocity(Vector2 linearVelocity) {
		body.setLinearVelocity(linearVelocity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public float getAngularVelocity() {
		return body.getAngularVelocity();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAngularVelocity(float angularVelocity) {
		body.setAngularVelocity(angularVelocity);
	}

	//COMPONENT
	@Override
	public void onAdd(ComponentManager container) {

	}

	@Override
	public void onRemove(ComponentManager container) {
	    entityWorld.safelyRemoveBody(body);
	}
}
