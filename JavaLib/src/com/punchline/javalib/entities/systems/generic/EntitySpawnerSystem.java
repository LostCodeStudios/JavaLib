/**
 * 
 */
package com.punchline.javalib.entities.systems.generic;

import com.punchline.javalib.entities.Component;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.components.generic.*;
import com.punchline.javalib.entities.systems.ComponentSystem;

/**
 * The entity spawner system for spawning entities with the EntitySpawner component.
 * @author William
 * @created Jul 27, 2013
 */
public class EntitySpawnerSystem extends ComponentSystem {

	/**
	 * Creates an entity spawner system.
	 */
	public EntitySpawnerSystem() {
		super(EntitySpawner.class);
		// TODO Auto-generated constructor stub
	}

	/** {@inheritDoc}
	 * @see com.badlogic.gdx.utils.Disposable#dispose()
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	/** {@inheritDoc}
	 * @see com.punchline.javalib.entities.EntitySystem#process(com.punchline.javalib.entities.Entity)
	 */
	@Override
	protected void process(Entity e) {
		EntitySpawner es = e.getComponent();
		
		//if spawn delay has passed.
		if(es.spawn(deltaSeconds()))
		{
			if(es.isGroupTemplate()) //If the spawner spawns a group template.
				world.createEntityGroup(es.getSpawnTemplate(), es.getArgs().toArray());
			else //If the spawner spawns a single entity.
				world.createEntity(es.getSpawnTemplate(), es.getArgs().toArray());	
		}
	}

}
