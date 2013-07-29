package com.punchline.javalib.entities.templates;

import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;

/**
 * Base class for entity templates.
 * @author Nathaniel
 *
 */
public interface EntityTemplate {
	
	/**
	 * Creates an entity.
	 * @param e The entity to be built
	 * @param physicsWorld An EntityWorld.
	 * @param args Arguments.
	 * @return An {@link Entity} of this template.
	 */
	public Entity buildEntity(Entity e, EntityWorld world, Object... args);
	
}
