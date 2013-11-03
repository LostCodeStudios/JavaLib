package com.lostcode.javalib.entities;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.lostcode.javalib.entities.components.physical.Collidable;
import com.lostcode.javalib.entities.components.physical.Sensor;
import com.lostcode.javalib.entities.events.EventCallback;
import com.lostcode.javalib.entities.events.EventHandler;
import com.lostcode.javalib.utils.LogManager;

/**
 * Listens for all collisions in the Box2D world, and handles them.
 * 
 * @author Natman64
 * @created Jul 25, 2013
 */
public final class ContactManager extends EventHandler implements
		ContactListener {

	private final EntityWorld world;

	/**
	 * Constructs the ContactManager.
	 * 
	 * @param world
	 *            The Box2D world that is being managed.
	 */
	public ContactManager(EntityWorld world) {
		this.world = world;
		world.getBox2DWorld().setContactListener(this);
	}

	/**
	 * Processes all collisions and sensor detections, queuing callbacks to be
	 * called later.
	 */
	@Override
	public void beginContact(Contact contact) {
		Fixture f1 = contact.getFixtureA();
		Fixture f2 = contact.getFixtureB();

		final Entity e1 = (Entity) f1.getBody().getUserData();
		final Entity e2 = (Entity) f2.getBody().getUserData();

		if (e1 == null || e2 == null)
			return; // If the collision is invalid

		// SENSOR CODE
		if ((f1.isSensor() || f2.isSensor()) 
				&& !(f2.isSensor() && f2.isSensor())) { //one of them is a sensor, the other is not

			final Sensor sensor;
			final Entity detectee;

			if (f1.isSensor()) { // e1 saw e2
				sensor = (Sensor) e1.getComponent(Sensor.class);
				detectee = e2;

			} else { // e2 saw e1
				sensor = (Sensor) e2.getComponent(Sensor.class);
				detectee = e1;
			}

			if (sensor != null) {

				this.addCallback(new Object(), new EventCallback() {
					@Override
					public void invoke(Entity e, Object... args) {
						sensor.onDetected(detectee, world);
					}
				});
				
				LogManager.debug("Physics", "Sensor collision ocurred.");

			}
		}

		// PHYSICAL CODE
		else { // They are both physical

			if (e1.hasComponent(Collidable.class)
					&& e2.hasComponent(Collidable.class)) {
				final Collidable c1 = (Collidable) e1
						.getComponent(Collidable.class);
				final Collidable c2 = (Collidable) e2
						.getComponent(Collidable.class);

				float collide1 = c1.continueCollision(e1, e2);
				float collide2 = c2.continueCollision(e2, e1);

				if (collide1 == 0f || collide2 == 0f) {
					return;
				} else {
					// Add collision events
					this.addCallback(new Object(), new EventCallback() {
						@Override
						public void invoke(Entity e, Object... args) {
							c1.onBeginContact(e1, e2);
						}
					});

					// Add collision events
					this.addCallback(new Object(), new EventCallback() {
						@Override
						public void invoke(Entity e, Object... args) {
							c2.onBeginContact(e2, e1);
						}
					});
					
					LogManager.debug("Physics", "Collision ocurred");
				}
			}

		}
	}

	/**
	 * Processes all ended contacts, calling Sensor.onEscape callbacks as
	 * necessary.
	 */
	@Override
	public void endContact(Contact contact) {
		final Fixture f1 = contact.getFixtureA();
		final Fixture f2 = contact.getFixtureB();

		if (f1 == null || f2 == null || f1.getBody() == null
				|| f2.getBody() == null)
			return;

		final Entity e1 = (Entity) f1.getBody().getUserData();
		final Entity e2 = (Entity) f2.getBody().getUserData();

		if (e1 == null || e2 == null)
			return;

		// SENSOR CODE
		if ((f1.isSensor() || f2.isSensor())
				&& !(f2.isSensor() && f2.isSensor())) {

			final Entity escapee;
			final Sensor sensor;

			// LOGIC
			if (f1.isSensor()) { // e2 escaped e1
				sensor = (Sensor) e1.getComponent(Sensor.class);
				escapee = e2;
			} else { // e1 escaped e2
				sensor = (Sensor) e2.getComponent(Sensor.class);
				escapee = e1;
			}

			if (sensor != null) {

				this.addCallback(new Object(), new EventCallback() {
					@Override
					public void invoke(Entity e, Object... args) {
						sensor.onEscaped(escapee, world);
					}
				});

			}

		} else { // They are both physical

			if (e1.hasComponent(Collidable.class)
					&& e2.hasComponent(Collidable.class)) {
				final Collidable c1 = (Collidable) e1
						.getComponent(Collidable.class);
				final Collidable c2 = (Collidable) e2
						.getComponent(Collidable.class);

				// Add collision events
				this.addCallback(new Object(), new EventCallback() {
					@Override
					public void invoke(Entity e, Object... args) {
						c1.onEndContact(e1, e2);
					}
				});

				// Add collision events
				this.addCallback(new Object(), new EventCallback() {
					@Override
					public void invoke(Entity e, Object... args) {
						c2.onEndContact(e2, e1);
					}
				});
			}

		}
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		Fixture f1 = contact.getFixtureA();
		Fixture f2 = contact.getFixtureB();

		final Entity e1 = (Entity) f1.getBody().getUserData();
		final Entity e2 = (Entity) f2.getBody().getUserData();

		if (e1 == null || e2 == null)
			return; // If the collision is invalid

		// PHYSICAL CODE
		if (!f1.isSensor() && !f2.isSensor()) { // They are both physical

			if (e1.hasComponent(Collidable.class) && e2.hasComponent(Collidable.class)) {
				
				final Collidable c1 = (Collidable) e1
						.getComponent(Collidable.class);
				final Collidable c2 = (Collidable) e2
						.getComponent(Collidable.class);

				float collide1 = c1.continueCollision(e1, e2);
				float collide2 = c2.continueCollision(e2, e1);

				if (collide1 == 0f || collide2 == 0f) {
					contact.setEnabled(false);
				}
			}

		}
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {

	}

	/**
	 * Processes all of the queued callbacks.
	 */
	public void process() {
		this.invoke(null);
		this.clear();
	}
}
