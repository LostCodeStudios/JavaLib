package com.lostcode.javalib.entities.systems.physical;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.components.physical.Collidable;
import com.lostcode.javalib.entities.components.physical.Particle;
import com.lostcode.javalib.entities.events.EventCallback;
import com.lostcode.javalib.entities.events.EventHandler;
import com.lostcode.javalib.entities.systems.ComponentSystem;
import com.lostcode.javalib.utils.LogManager;

/**
 * The particle system which updates the position of particles
 * 
 * @author MadcowD
 * @created Jul 23, 2013
 */
public class ParticleSystem extends ComponentSystem {

	/**
	 * Initializes the particle system for particle components.
	 */
	@SuppressWarnings("unchecked")
	public ParticleSystem() {
		super(Particle.class);
	}

	@Override
	public void dispose() {
	}

	@Override
	protected void process(final Entity e) {
		Particle p = (Particle) e.getComponent(Particle.class);

		// Move the particle
		Vector2 pos = p.getPosition().cpy();
		Vector2 deltaX = new Vector2(p.getLinearVelocity().x * deltaSeconds(),
				p.getLinearVelocity().y * deltaSeconds());

		// DO RAY CASTING FOR COLLIDABLE CHECK
		
		LogManager.debug("Physics", "Started raycasting for particle");
		if (e.hasComponent(Collidable.class)) {
			if (p.getLinearVelocity().len() == 0) {
				throw new GdxRuntimeException("Cannot raycast for particle with speed of 0.");
			}
			
			World c = world.getBox2DWorld();

			// Perform the raycast
			c.rayCast(new RayCastCallback() {
				@Override
				public float reportRayFixture(Fixture fixture, Vector2 point,
						Vector2 normal, float fraction) {
					// If collision occurs
					final Collidable col = (Collidable) e
							.getComponent(Collidable.class);

					// Get the victim

					if (fixture.isSensor())
						return 0;

					final Entity victim = (Entity) fixture.getBody()
							.getUserData();

					float continueCol = col.continueCollision(e, victim);

					if (continueCol == 0)
						// Call the on collide event for the entity and
						// terminate if appropriate.
						rayCastCollisions.addCallback(new Object(),
								new EventCallback() {
									@Override
									public void invoke(Entity es,
											Object... args) {
										col.onBeginContact(e, victim);
									}
								});

					// Return whether or not the ray cast should be terminated.
					return col.continueCollision(e, victim);
				}
			}, pos, pos.cpy().add(deltaX));
		}
		LogManager.debug("Physics", "Finished raycasting for particle");

		// Move and set the final position of the entity.
		pos.add(deltaX);
		p.setPosition(pos);
		float angularVelocity = p.getAngularVelocity() * deltaSeconds();
		p.setRotation(p.getRotation() + angularVelocity);
	}

	@Override
	public void processEntities() {
		super.processEntities();
		rayCastCollisions.invoke(null);
		rayCastCollisions.clear();
	}

	/**
	 * Used for raycast collisions so that they are called outside of the world
	 * step.
	 */
	private EventHandler rayCastCollisions = new EventHandler();

}
