package com.punchline.javalib.entities;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.punchline.javalib.entities.components.physical.Collidable;
import com.punchline.javalib.entities.components.physical.Sensor;

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
	
	/**
	 * Processes all collisions and sensor detections, calling callbacks as necessary.
	 */
	@Override
	public void beginContact(Contact contact) {
		Fixture f1 = contact.getFixtureA();
		Fixture f2 = contact.getFixtureB();
		
		Entity e1 = (Entity)f1.getBody().getUserData();
		Entity e2 = (Entity)f2.getBody().getUserData();
						
		if (f1.isSensor() && !f2.isSensor()) { //e1 saw e2
			
			Sensor sensor = (Sensor) e1.getComponent(Sensor.class);
			
			if (sensor.onDetection != null)
				sensor.onDetection.invoke(e2);
			
		} else if (f2.isSensor() && !f1.isSensor()) { //e2 saw e1
			
			Sensor sensor = (Sensor) e2.getComponent(Sensor.class);
			
			if (sensor.onDetection != null)
				sensor.onDetection.invoke(e1);
			
		} else { //They are both physical
			
			if (e1.hasComponent(Collidable.class) && e2.hasComponent(Collidable.class)) {
				Collidable c1 = (Collidable)e1.getComponent(Collidable.class);
				Collidable c2 = (Collidable)e2.getComponent(Collidable.class);
				
				float collide1 =  c1.onCollide(e1, e2);
				float collide2 = c2.onCollide(e2, e1);
				
				if (collide1 == 0f || collide2 == 0f)
					contact.setEnabled(false);
			}
			
		}
	}

	/**
	 * Processes all ended contacts, calling Sensor.onEscape callbacks as necessary.
	 */
	@Override
	public void endContact(Contact contact) { 
		Fixture f1 = contact.getFixtureA();
		Fixture f2 = contact.getFixtureB();
		
		if (f1.getBody() == null || f2.getBody() == null)
			return;
		
		Entity e1 = (Entity)f1.getBody().getUserData();
		Entity e2 = (Entity)f2.getBody().getUserData();
		
		if (f1.isSensor() && !f2.isSensor()) { //e2 escaped e1
			
			Sensor sensor = (Sensor) e1.getComponent(Sensor.class);
			
			if (sensor.onEscape != null)
				sensor.onEscape.invoke(e2);
			
		} else if (f2.isSensor() && !f1.isSensor()) { //e1 escaped e2
			
			Sensor sensor = (Sensor) e2.getComponent(Sensor.class);
			
			if (sensor.onEscape != null)
				sensor.onEscape.invoke(e1);
			
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) { }

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) { }
	
}
