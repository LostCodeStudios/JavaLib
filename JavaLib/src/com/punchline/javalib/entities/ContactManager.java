package com.punchline.javalib.entities;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.punchline.javalib.entities.components.physical.Collidable;

/**
 * Listens for all collisions in the Box2D world, and handles them.
 * @author Nathaniel
 * @created Jul 25, 2013
 */
public class ContactManager implements ContactListener {

	/**
	 * Constructs the ContactManager.
	 * @param world The Box2D world that is being managed.
	 */
	public ContactManager(World world) {
		world.setContactListener(this);
	}
	
	@Override
	public void beginContact(Contact contact) {
		Fixture f1 = contact.getFixtureA();
		Fixture f2 = contact.getFixtureB();
		
		Entity e1 = (Entity)f1.getUserData();
		Entity e2 = (Entity)f2.getUserData();
		
		if (e1.hasComponent(Collidable.class) && e2.hasComponent(Collidable.class)) {
			Collidable c1 = e1.getComponent();
			Collidable c2 = e2.getComponent();
			
			c1.onCollide(e1, e2);
			c2.onCollide(e2, e1);
		}
	}

	@Override
	public void endContact(Contact contact) { }

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) { }

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) { }
	
}
