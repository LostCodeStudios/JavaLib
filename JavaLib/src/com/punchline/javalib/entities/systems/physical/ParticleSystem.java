package com.punchline.javalib.entities.systems.physical;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.components.physical.Collidable;
import com.punchline.javalib.entities.components.physical.Particle;
import com.punchline.javalib.entities.systems.ComponentSystem;

/**
 * The particle system which updates the position of particles
 * @author MadcowD
 * @created Jul 23, 2013
 */
public class ParticleSystem extends ComponentSystem {

	/**
	 * Initializes the particle system for particle components.
	 */
	@SuppressWarnings("unchecked")
	public ParticleSystem(){
		super(Particle.class);
	}
	
	@Override
	public void dispose() { }

	@Override
	protected void process(Entity e) {
		Particle p = (Particle)e.getComponent(Particle.class);
		
		//Move the particle
		Vector2 pos = p.getPosition().cpy();
		Vector2 deltaX = new Vector2(p.getLinearVelocity().x * deltaSeconds(), p.getLinearVelocity().y * deltaSeconds());
		
		//DO RAY CASTING FOR COLLIDABLE CHECK
		if(e.hasComponent(Collidable.class)){
			com.badlogic.gdx.physics.box2d.World c = world.getPhysicsWorld();
			
			//Perform the raycast
			c.rayCast(new RayCastCallback(){
				Entity e;
				public RayCastCallback init(Entity e){
					this.e = e;
					return this;
				}
				
				
				@Override
				public float reportRayFixture(Fixture fixture, Vector2 point,
						Vector2 normal, float fraction) {
					//If collision occurs
					Collidable col = (Collidable)e.getComponent(Collidable.class);
					
					//Get the victim
					Entity victim = (Entity)fixture.getBody().getUserData();
					
					//Call the on collide event for the entity and terminate if appropriate.
					return col.onCollide(e, victim);
				}
			}.init(e),
			pos, pos.cpy().add(deltaX));
		}
		
		//Move and set the final position of the entity.
		pos.add(deltaX);
		p.setPosition(pos);
		float angularVelocity = p.getAngularVelocity() * deltaSeconds();
		p.setRotation(p.getRotation() + angularVelocity);
	}

}
