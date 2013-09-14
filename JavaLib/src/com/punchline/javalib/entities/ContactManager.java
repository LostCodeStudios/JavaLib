package com.punchline.javalib.entities;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.punchline.javalib.entities.components.physical.Collidable;
import com.punchline.javalib.entities.components.physical.Sensor;
import com.punchline.javalib.entities.events.EventCallback;
import com.punchline.javalib.entities.events.EventHandler;

/**
 * Listens for all collisions in the Box2D world, and handles them.
 * @author Natman64
 * @created Jul 25, 2013
 */
public final class ContactManager extends EventHandler implements ContactListener {

	private final EntityWorld world;
	
	/**
	 * Constructs the ContactManager.
	 * @param world The Box2D world that is being managed.
	 */
	public ContactManager(EntityWorld world) {
		this.world = world;
		world.getBox2DWorld().setContactListener(this);
	}
	
	/**
	 * Processes all collisions and sensor detections, queuing callbacks to be called later.
	 */
	@Override
	public void beginContact(Contact contact) {
		Fixture f1 = contact.getFixtureA();
		Fixture f2 = contact.getFixtureB();
		
		final Entity e1 = (Entity)f1.getBody().getUserData();
		final Entity e2 = (Entity)f2.getBody().getUserData();
		
		if (e1 == null || e2 == null) return; //If the collision is invalid
		
		//SENSOR CODE
		if((f1.isSensor() || f2.isSensor()) && !(f2.isSensor() && f2.isSensor())){
			
			final Sensor sensor;
			final Entity detectee;
			
			if (f1.isSensor() ) { //e1 saw e2
				sensor = (Sensor) e1.getComponent(Sensor.class);
				detectee = e2;
				
			} else { //e2 saw e1
				sensor = (Sensor) e2.getComponent(Sensor.class);
				detectee = e1;
			}
			
			this.addCallback(new Object(), new EventCallback(){
				@Override
				public void invoke(Entity e, Object... args) {
					// TODO Auto-generated method stub
					sensor.onDetected(detectee, world);
				}	
			});
		}
		
		
		//PHYSICAL CODE
		else { //They are both physical
			
			if (e1.hasComponent(Collidable.class) && e2.hasComponent(Collidable.class)) {
				final Collidable c1 = (Collidable)e1.getComponent(Collidable.class);
				final Collidable c2 = (Collidable)e2.getComponent(Collidable.class);
				
				float collide1 =  c1.continueCollision(e1, e2);
				float collide2 = c2.continueCollision(e2, e1);
				
				if (collide1 == 0f || collide2 == 0f)
					contact.setEnabled(false);
				else
				{
					//Add collision events
					this.addCallback(new Object(), new EventCallback(){
						@Override
						public void invoke(Entity e, Object... args) {
							// TODO Auto-generated method stub
							c1.onCollide(e1, e2);
						}	
					});
					
					//Add collision events
					this.addCallback(new Object(), new EventCallback(){
						@Override
						public void invoke(Entity e, Object... args) {
							// TODO Auto-generated method stub
							c2.onCollide(e2, e1);
						}	
					});
				}
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
		
		if (f1 == null || f2 == null || f1.getBody() == null || f2.getBody() == null)
			return;
		
		Entity e1 = (Entity)f1.getBody().getUserData();
		Entity e2 = (Entity)f2.getBody().getUserData();
		
		if (e1 == null || e2 == null)
			return;
		
		if((f1.isSensor() || f2.isSensor()) && !(f2.isSensor() && f2.isSensor())){
			
			final Entity escapee;
			final Sensor sensor;
			
			//LOGIC
			if (f1.isSensor()) { //e2 escaped e1
				sensor = (Sensor) e1.getComponent(Sensor.class);
				escapee = e2;
				
			}
			else
			{ //e1 escaped e2
				sensor = (Sensor) e2.getComponent(Sensor.class);
				escapee= e1;
			}
			
			this.addCallback(new Object(), new EventCallback(){
				@Override
				public void invoke(Entity e, Object... args) {
					// TODO Auto-generated method stub
					sensor.onEscaped(escapee, world);
				}	
			});
			
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) { }

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) { }
	
	/**
	 * Processes all of the queued callbacks.
	 */
	public void process(){
		this.invoke(null);
		this.clear();
	}
}
