package com.lostcode.javalib.entities.templates;

import com.badlogic.gdx.utils.Disposable;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;

/**
 * Base class for entity templates.
 * 
 * @author Natman64
 * 
 */
public interface EntityTemplate extends Disposable {

	/**
	 * Creates an entity.
	 * 
	 * @param e
	 *            The entity to be built
	 * @param physicsWorld
	 *            An EntityWorld.
	 * @param args
	 *            Arguments.
	 * @return An {@link Entity} of this template.
	 */
	public Entity buildEntity(final Entity e, EntityWorld world, Object... args);

}
