package com.punchline.javalib.entities.systems.generic;

import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.components.generic.EntitySpawner;
import com.punchline.javalib.entities.systems.ComponentSystem;

/**
 * The entity spawner system for spawning entities with the EntitySpawner
 * component.
 * 
 * @author MadcowD
 * @created Jul 27, 2013
 */
public class EntitySpawnerSystem extends ComponentSystem {

	/**
	 * Creates an entity spawner system.
	 */
	@SuppressWarnings("unchecked")
	public EntitySpawnerSystem() {
		super(EntitySpawner.class);
	}

	@Override
	public void dispose() {
	}

	@Override
	protected void process(Entity e) {
		EntitySpawner es = (EntitySpawner) e.getComponent(EntitySpawner.class);

		// if spawn delay has passed.
		if (es.spawn(deltaSeconds())) {
			world.create(es.getCreationArgs());
		}
	}

}
