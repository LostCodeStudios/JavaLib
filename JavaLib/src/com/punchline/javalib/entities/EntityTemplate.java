package com.punchline.javalib.entities;

/**
 * Base class for entity templates.
 * @author Nathaniel
 *
 */
public interface EntityTemplate {
	
	/**
	 * Creates an entity.
	 * @param world An EntityWorld.
	 * @param args Arguments.
	 * @return An {@link Entity} of this template.
	 */
	public Entity buildEntity(EntityWorld world, Object... args);
	
}
