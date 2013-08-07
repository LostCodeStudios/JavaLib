package com.punchline.javalib.entities.components.physical;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.ComponentManager;

/**
 * Component wrapper for a Box2D {@link com.badlogic.gdx.physics.box2d.Body Body}.
 * @author Natman64
 *
 */
public class Body implements Transform, Velocity {

	//region Fields/Initialization
	
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
		this(world, e, bodyDef);
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
	
	//endregion
	
	//region Accessors
	
	/**
	 * @return The Box2D body.
	 */
	public com.badlogic.gdx.physics.box2d.Body getBody(){
		return body;
	}
	
	//endregion
	
	//region Transform Implementation
	
	@Override
	public Vector2 getPosition() {
		return body.getPosition();
	}
	
	@Override
	public void setPosition(Vector2 position) {
		body.setTransform(position, body.getAngle());
	}

	@Override
	public float getRotation() {
		return body.getAngle();
	}
	
	@Override
	public void setRotation(float rotation){
		body.setTransform(getPosition(), rotation);
	}
	
	@Override
	public Vector2 getOrigin() {
		return body.getWorldCenter();
	}
	
	//endregion
	
	//region Velocity Implementation
	
	@Override
	public Vector2 getLinearVelocity() {
		return body.getLinearVelocity();
	}

	@Override
	public void setLinearVelocity(Vector2 linearVelocity) {
		body.setLinearVelocity(linearVelocity);
	}

	@Override
	public float getAngularVelocity() {
		return body.getAngularVelocity();
	}

	@Override
	public void setAngularVelocity(float angularVelocity) {
		body.setAngularVelocity(angularVelocity);
	}

	//endregion
	
	//region Events
	
	@Override
	public void onAdd(ComponentManager container) {

	}

	@Override
	public void onRemove(ComponentManager container) {
	    entityWorld.safelyRemoveBody(body);
	}
	
	//endregion
	
}
